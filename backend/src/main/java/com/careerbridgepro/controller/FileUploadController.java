package com.careerbridgepro.controller;

import com.careerbridgepro.entity.Resume;
import com.careerbridgepro.entity.User;
import com.careerbridgepro.repository.ResumeRepository;
import com.careerbridgepro.repository.UserRepository;
import com.careerbridgepro.service.ExcelProcessingService;
import com.careerbridgepro.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class FileUploadController {
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private ExcelProcessingService excelProcessingService;
    
    @Autowired
    private ResumeRepository resumeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/resume")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            if (user == null || !user.isStudent()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only students can upload resumes"));
            }
            
            // Validate file type
            if (!fileUploadService.isValidFileType(file, new String[]{"pdf", "doc", "docx"})) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only PDF, DOC, and DOCX files are allowed"));
            }
            
            // Validate file size (10MB max)
            if (file.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.badRequest().body(Map.of("error", "File size must be less than 10MB"));
            }
            
            // Upload file
            String filePath = fileUploadService.uploadResume(file);
            
            // Save resume record
            Resume resume = new Resume();
            resume.setUser(user);
            resume.setFileName(file.getOriginalFilename());
            resume.setFilePath(filePath);
            resume.setFileSize(file.getSize());
            resume.setIsPrimary(true); // Set as primary resume
            
            // Set other resumes as non-primary
            resumeRepository.findByUser(user).forEach(r -> r.setIsPrimary(false));
            resumeRepository.saveAll(resumeRepository.findByUser(user));
            
            resumeRepository.save(resume);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Resume uploaded successfully");
            response.put("fileName", file.getOriginalFilename());
            response.put("filePath", filePath);
            response.put("fileSize", file.getSize());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to upload resume: " + e.getMessage()));
        }
    }
    
    @PostMapping("/excel/jobs")
    public ResponseEntity<?> uploadJobExcel(@RequestParam("file") MultipartFile file, Authentication authentication) {
        try {
            String username = authentication.getName();
            
            // Validate file type
            if (!fileUploadService.isValidFileType(file, new String[]{"xlsx", "xls"})) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only Excel files (.xlsx, .xls) are allowed"));
            }
            
            // Validate file size (5MB max)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest().body(Map.of("error", "File size must be less than 5MB"));
            }
            
            // Process Excel file
            Map<String, Object> result = excelProcessingService.processJobExcel(file, username);
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to process Excel file: " + e.getMessage()));
        }
    }
    
    @GetMapping("/excel/template")
    public ResponseEntity<Resource> downloadJobTemplate() {
        try {
            byte[] templateData = excelProcessingService.generateJobTemplate();
            ByteArrayResource resource = new ByteArrayResource(templateData);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=job_template.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(templateData.length)
                    .body(resource);
                    
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/resumes")
    public ResponseEntity<?> getMyResumes(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
            }
            
            return ResponseEntity.ok(resumeRepository.findByUserOrderByUploadedAtDesc(user));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to fetch resumes: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/resumes/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable Long id, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
            }
            
            Resume resume = resumeRepository.findById(id).orElse(null);
            if (resume == null) {
                return ResponseEntity.notFound().build();
            }
            
            if (!resume.getUser().getId().equals(user.getId())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Not authorized to delete this resume"));
            }
            
            // Delete file from filesystem
            fileUploadService.deleteFile(resume.getFilePath());
            
            // Delete from database
            resumeRepository.delete(resume);
            
            return ResponseEntity.ok(Map.of("message", "Resume deleted successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to delete resume: " + e.getMessage()));
        }
    }
}
