package com.careerbridgepro.controller;

import com.careerbridgepro.entity.Application;
import com.careerbridgepro.entity.ApplicationStatus;
import com.careerbridgepro.entity.Job;
import com.careerbridgepro.entity.User;
import com.careerbridgepro.repository.ApplicationRepository;
import com.careerbridgepro.repository.JobRepository;
import com.careerbridgepro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ApplicationController {
    
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity<List<Application>> getMyApplications(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        
        List<Application> applications = applicationRepository.findByApplicant(user);
        return ResponseEntity.ok(applications);
    }
    
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getJobApplications(@PathVariable Long jobId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user == null || !user.isRecruiter()) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Job job = jobOptional.get();
        if (!job.getPostedBy().getId().equals(user.getId()) && !user.isAdmin()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<Application> applications = applicationRepository.findByJob(job);
        return ResponseEntity.ok(applications);
    }
    
    @PostMapping
    public ResponseEntity<?> applyForJob(@Valid @RequestBody Application application, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            if (user == null || !user.isStudent()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only students can apply for jobs"));
            }
            
            Optional<Job> jobOptional = jobRepository.findById(application.getJob().getId());
            if (jobOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Job not found"));
            }
            
            Job job = jobOptional.get();
            
            if (applicationRepository.existsByJobAndApplicant(job, user)) {
                return ResponseEntity.badRequest().body(Map.of("error", "You have already applied for this job"));
            }
            
            application.setJob(job);
            application.setApplicant(user);
            application.setStatus(ApplicationStatus.PENDING);
            
            Application savedApplication = applicationRepository.save(application);
            return ResponseEntity.ok(savedApplication);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to apply: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateApplicationStatus(@PathVariable Long id, @RequestParam ApplicationStatus status, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            if (user == null || !user.isRecruiter()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only recruiters can update application status"));
            }
            
            Optional<Application> applicationOptional = applicationRepository.findById(id);
            if (applicationOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Application application = applicationOptional.get();
            Job job = application.getJob();
            
            if (!job.getPostedBy().getId().equals(user.getId()) && !user.isAdmin()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Not authorized to update this application"));
            }
            
            application.setStatus(status);
            Application updatedApplication = applicationRepository.save(application);
            
            return ResponseEntity.ok(updatedApplication);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to update status: " + e.getMessage()));
        }
    }
}
