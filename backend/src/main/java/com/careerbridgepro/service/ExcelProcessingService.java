package com.careerbridgepro.service;

import com.careerbridgepro.entity.Job;
import com.careerbridgepro.entity.JobMode;
import com.careerbridgepro.entity.JobType;
import com.careerbridgepro.entity.User;
import com.careerbridgepro.repository.JobRepository;
import com.careerbridgepro.repository.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelProcessingService {
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    public Map<String, Object> processJobExcel(MultipartFile file, String username) throws IOException {
        List<Job> jobs = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int errorCount = 0;
        
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !user.isRecruiter()) {
            errors.add("User not found or not authorized to upload jobs");
            return Map.of("success", false, "errors", errors);
        }
        
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            
            // Skip header row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                try {
                    Job job = createJobFromRow(row, user);
                    if (job != null) {
                        jobs.add(job);
                        successCount++;
                    } else {
                        errorCount++;
                        errors.add("Row " + (i + 1) + ": Invalid job data");
                    }
                } catch (Exception e) {
                    errorCount++;
                    errors.add("Row " + (i + 1) + ": " + e.getMessage());
                }
            }
            
            // Save all valid jobs
            if (!jobs.isEmpty()) {
                jobRepository.saveAll(jobs);
            }
            
        } catch (Exception e) {
            errors.add("Error processing Excel file: " + e.getMessage());
        }
        
        return Map.of(
            "success", successCount > 0,
            "totalRows", successCount + errorCount,
            "successCount", successCount,
            "errorCount", errorCount,
            "errors", errors,
            "jobs", jobs
        );
    }
    
    private Job createJobFromRow(Row row, User user) {
        try {
            Job job = new Job();
            
            // Title (Column A)
            Cell titleCell = row.getCell(0);
            if (titleCell == null || getCellValueAsString(titleCell).trim().isEmpty()) {
                throw new IllegalArgumentException("Title is required");
            }
            job.setTitle(getCellValueAsString(titleCell));
            
            // Company (Column B)
            Cell companyCell = row.getCell(1);
            if (companyCell == null || getCellValueAsString(companyCell).trim().isEmpty()) {
                throw new IllegalArgumentException("Company is required");
            }
            job.setCompany(getCellValueAsString(companyCell));
            
            // Location (Column C)
            Cell locationCell = row.getCell(2);
            if (locationCell == null || getCellValueAsString(locationCell).trim().isEmpty()) {
                throw new IllegalArgumentException("Location is required");
            }
            job.setLocation(getCellValueAsString(locationCell));
            
            // Type (Column D)
            Cell typeCell = row.getCell(3);
            if (typeCell != null) {
                String typeStr = getCellValueAsString(typeCell).toUpperCase();
                try {
                    job.setType(JobType.valueOf(typeStr));
                } catch (IllegalArgumentException e) {
                    job.setType(JobType.FULL_TIME); // Default
                }
            } else {
                job.setType(JobType.FULL_TIME);
            }
            
            // Mode (Column E)
            Cell modeCell = row.getCell(4);
            if (modeCell != null) {
                String modeStr = getCellValueAsString(modeCell).toUpperCase();
                try {
                    job.setMode(JobMode.valueOf(modeStr));
                } catch (IllegalArgumentException e) {
                    job.setMode(JobMode.ONSITE); // Default
                }
            } else {
                job.setMode(JobMode.ONSITE);
            }
            
            // Salary (Column F)
            Cell salaryCell = row.getCell(5);
            if (salaryCell != null) {
                job.setSalary(getCellValueAsString(salaryCell));
            }
            
            // Description (Column G)
            Cell descriptionCell = row.getCell(6);
            if (descriptionCell == null || getCellValueAsString(descriptionCell).trim().isEmpty()) {
                throw new IllegalArgumentException("Description is required");
            }
            job.setDescription(getCellValueAsString(descriptionCell));
            
            // Requirements (Column H)
            Cell requirementsCell = row.getCell(7);
            if (requirementsCell != null) {
                job.setRequirements(getCellValueAsString(requirementsCell));
            }
            
            // Benefits (Column I)
            Cell benefitsCell = row.getCell(8);
            if (benefitsCell != null) {
                job.setBenefits(getCellValueAsString(benefitsCell));
            }
            
            // Set other properties
            job.setPostedBy(user);
            job.setActive(true);
            
            return job;
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating job: " + e.getMessage());
        }
    }
    
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    public byte[] generateJobTemplate() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Jobs Template");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Title", "Company", "Location", "Type", "Mode", 
                "Salary", "Description", "Requirements", "Benefits"
            };
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                
                // Style the header
                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);
                cell.setCellStyle(headerStyle);
            }
            
            // Add sample data
            Row sampleRow = sheet.createRow(1);
            String[] sampleData = {
                "Software Engineer", "Tech Corp", "New York", "FULL_TIME", "ONSITE",
                "$80,000 - $100,000", "Develop software applications", "Java, Spring Boot", "Health insurance, 401k"
            };
            
            for (int i = 0; i < sampleData.length; i++) {
                Cell cell = sampleRow.createCell(i);
                cell.setCellValue(sampleData[i]);
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Convert to byte array
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
