package com.careerbridgepro.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @Value("${file.resume-dir}")
    private String resumeDir;
    
    @Value("${file.excel-dir}")
    private String excelDir;
    
    public String uploadFile(MultipartFile file, String subDirectory) throws IOException {
        // Create directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir, subDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Generate unique filename
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? 
            originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
        
        // Save file
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        // Return relative path
        return subDirectory + "/" + uniqueFilename;
    }
    
    public String uploadResume(MultipartFile file) throws IOException {
        return uploadFile(file, "resumes");
    }
    
    public String uploadExcel(MultipartFile file) throws IOException {
        return uploadFile(file, "excel");
    }
    
    public boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(uploadDir, filePath);
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            return false;
        }
    }
    
    public boolean isValidFileType(MultipartFile file, String[] allowedTypes) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return false;
        }
        
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        
        for (String allowedType : allowedTypes) {
            if (allowedType.toLowerCase().equals(fileExtension)) {
                return true;
            }
        }
        
        return false;
    }
}
