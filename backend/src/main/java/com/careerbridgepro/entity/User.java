package com.careerbridgepro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "career_profiles")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;
    
    @NotBlank
    @Size(max = 100)
    @Email
    @Column(unique = true)
    private String email;
    
    @NotBlank
    @Size(max = 100)
    private String password;
    
    @NotBlank
    @Size(max = 50)
    private String firstName;
    
    @NotBlank
    @Size(max = 50)
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @Column(name = "is_enabled")
    private Boolean enabled = true;
    
    @Column(name = "is_verified")
    private Boolean verified = false;
    
    @Size(max = 15)
    private String phone;
    
    @Size(max = 500)
    private String bio;
    
    @Size(max = 200)
    private String avatar;
    
    @Size(max = 100)
    private String location;
    
    @Size(max = 100)
    private String company; // For recruiters
    
    @Size(max = 200)
    private String website; // For recruiters
    
    @Size(max = 500)
    private String skills; // Professional skills
    
    @Size(max = 100)
    private String experienceLevel; // Entry, Mid, Senior, Executive
    
    @Size(max = 200)
    private String preferredIndustries; // Comma-separated industries
    
    private Boolean aiMatchingEnabled = true; // AI matching preference
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> postedJobs;
    
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resume> resumes;
    
    // Constructors
    public User() {}
    
    public User(String username, String email, String password, String firstName, String lastName, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    
    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<Job> getPostedJobs() { return postedJobs; }
    public void setPostedJobs(List<Job> postedJobs) { this.postedJobs = postedJobs; }
    
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
    
    public List<Resume> getResumes() { return resumes; }
    public void setResumes(List<Resume> resumes) { this.resumes = resumes; }
    
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    
    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }
    
    public String getPreferredIndustries() { return preferredIndustries; }
    public void setPreferredIndustries(String preferredIndustries) { this.preferredIndustries = preferredIndustries; }
    
    public Boolean getAiMatchingEnabled() { return aiMatchingEnabled; }
    public void setAiMatchingEnabled(Boolean aiMatchingEnabled) { this.aiMatchingEnabled = aiMatchingEnabled; }
    
    // Helper methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public boolean isStudent() {
        return role == Role.STUDENT;
    }
    
    public boolean isRecruiter() {
        return role == Role.RECRUITER;
    }
    
    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}
