package com.careerbridgepro.controller;

import com.careerbridgepro.entity.Job;
import com.careerbridgepro.entity.JobMode;
import com.careerbridgepro.entity.JobType;
import com.careerbridgepro.entity.User;
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
@RequestMapping("/jobs")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class JobController {
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobRepository.findByActiveTrue();
        return ResponseEntity.ok(jobs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> job = jobRepository.findById(id);
        return job.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String keyword) {
        List<Job> jobs = jobRepository.searchJobs(keyword);
        return ResponseEntity.ok(jobs);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Job>> getJobsByType(@PathVariable JobType type) {
        List<Job> jobs = jobRepository.findByType(type);
        return ResponseEntity.ok(jobs);
    }
    
    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<Job>> getJobsByMode(@PathVariable JobMode mode) {
        List<Job> jobs = jobRepository.findByMode(mode);
        return ResponseEntity.ok(jobs);
    }
    
    @PostMapping
    public ResponseEntity<?> createJob(@Valid @RequestBody Job job, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            if (user == null || !user.isRecruiter()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only recruiters can post jobs"));
            }
            
            job.setPostedBy(user);
            job.setActive(true);
            Job savedJob = jobRepository.save(job);
            
            return ResponseEntity.ok(savedJob);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to create job: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @Valid @RequestBody Job jobDetails, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Job job = jobOptional.get();
            
            if (!job.getPostedBy().getId().equals(user.getId()) && !user.isAdmin()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Not authorized to update this job"));
            }
            
            job.setTitle(jobDetails.getTitle());
            job.setCompany(jobDetails.getCompany());
            job.setLocation(jobDetails.getLocation());
            job.setType(jobDetails.getType());
            job.setMode(jobDetails.getMode());
            job.setSalary(jobDetails.getSalary());
            job.setDescription(jobDetails.getDescription());
            job.setRequirements(jobDetails.getRequirements());
            job.setBenefits(jobDetails.getBenefits());
            
            Job updatedJob = jobRepository.save(job);
            return ResponseEntity.ok(updatedJob);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to update job: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            
            Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Job job = jobOptional.get();
            
            if (!job.getPostedBy().getId().equals(user.getId()) && !user.isAdmin()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Not authorized to delete this job"));
            }
            
            job.setActive(false);
            jobRepository.save(job);
            
            return ResponseEntity.ok(Map.of("message", "Job deleted successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to delete job: " + e.getMessage()));
        }
    }
}
