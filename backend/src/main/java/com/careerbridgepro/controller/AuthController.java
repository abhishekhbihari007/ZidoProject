package com.careerbridgepro.controller;

import com.careerbridgepro.dto.LoginRequest;
import com.careerbridgepro.dto.RegisterRequest;
import com.careerbridgepro.dto.AuthResponse;
import com.careerbridgepro.entity.Role;
import com.careerbridgepro.entity.User;
import com.careerbridgepro.repository.UserRepository;
import com.careerbridgepro.security.JwtUtil;
import com.careerbridgepro.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            
            User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
            
            AuthResponse response = new AuthResponse();
            response.setToken(token);
            response.setRole(user.getRole().name());
            response.setName(user.getFullName());
            response.setEmail(user.getEmail());
            response.setAvatar(user.getAvatar());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            if (userRepository.existsByUsername(registerRequest.getUsername())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Username already exists");
                return ResponseEntity.badRequest().body(error);
            }
            
            if (userRepository.existsByEmail(registerRequest.getEmail())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Email already exists");
                return ResponseEntity.badRequest().body(error);
            }
            
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setRole(Role.valueOf(registerRequest.getRole().toUpperCase()));
            user.setEnabled(true);
            user.setVerified(false);
            
            userRepository.save(user);
            
            Map<String, String> success = new HashMap<>();
            success.put("message", "User registered successfully");
            return ResponseEntity.ok(success);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
