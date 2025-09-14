package com.careerbridgepro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "career_opportunities")
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 200)
    private String title;
    
    @NotBlank
    @Size(max = 100)
    private String company;
    
    @NotBlank
    @Size(max = 100)
    private String location;
    
    @Enumerated(EnumType.STRING)
    private JobType type;
    
    @Enumerated(EnumType.STRING)
    private JobMode mode;
    
    @Size(max = 100)
    private String salary;
    
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String requirements;
    
    @Column(columnDefinition = "TEXT")
    private String benefits;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by")
    private User postedBy;
    
    @Column(name = "is_active")
    private Boolean active = true;
    
    @Size(max = 500)
    private String requiredSkills; // Required skills for the position
    
    @Size(max = 100)
    private String experienceRequired; // Experience level required
    
    @Size(max = 200)
    private String industry; // Industry category
    
    private Integer matchScore; // AI-generated match score
    
    private Boolean aiRecommended = false; // AI recommendation flag
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications;
    
    // Constructors
    public Job() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public JobType getType() { return type; }
    public void setType(JobType type) { this.type = type; }
    
    public JobMode getMode() { return mode; }
    public void setMode(JobMode mode) { this.mode = mode; }
    
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
    
    public String getBenefits() { return benefits; }
    public void setBenefits(String benefits) { this.benefits = benefits; }
    
    public User getPostedBy() { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
    
    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
    
    public String getExperienceRequired() { return experienceRequired; }
    public void setExperienceRequired(String experienceRequired) { this.experienceRequired = experienceRequired; }
    
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    
    public Integer getMatchScore() { return matchScore; }
    public void setMatchScore(Integer matchScore) { this.matchScore = matchScore; }
    
    public Boolean getAiRecommended() { return aiRecommended; }
    public void setAiRecommended(Boolean aiRecommended) { this.aiRecommended = aiRecommended; }
}
