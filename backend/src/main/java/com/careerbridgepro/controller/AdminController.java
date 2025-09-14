package com.careerbridgepro.controller;

import com.careerbridgepro.entity.User;
import com.careerbridgepro.repository.UserRepository;
import com.careerbridgepro.repository.JobRepository;
import com.careerbridgepro.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class AdminController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        long totalUsers = userRepository.count();
        long totalJobs = jobRepository.count();
        long totalApplications = applicationRepository.count();
        
        stats.put("totalUsers", totalUsers);
        stats.put("totalJobs", totalJobs);
        stats.put("totalApplications", totalApplications);
        
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/users/{id}/status")
    public ResponseEntity<?> updateUserStatus(@PathVariable Long id, @RequestParam Boolean enabled, Authentication authentication) {
        try {
            String username = authentication.getName();
            User admin = userRepository.findByUsername(username).orElse(null);
            
            if (admin == null || !admin.isAdmin()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Not authorized"));
            }
            
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            User user = userOptional.get();
            user.setEnabled(enabled);
            userRepository.save(user);
            
            return ResponseEntity.ok(Map.of("message", "User status updated successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to update user status: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, Authentication authentication) {
        try {
            String username = authentication.getName();
            User admin = userRepository.findByUsername(username).orElse(null);
            
            if (admin == null || !admin.isAdmin()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Not authorized"));
            }
            
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            User user = userOptional.get();
            if (user.getId().equals(admin.getId())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Cannot delete your own account"));
            }
            
            userRepository.delete(user);
            return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to delete user: " + e.getMessage()));
        }
    }
}
