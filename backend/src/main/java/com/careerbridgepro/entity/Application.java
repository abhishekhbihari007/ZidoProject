package com.careerbridgepro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_applications")
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private User applicant;
    
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;
    
    @Column(columnDefinition = "TEXT")
    private String coverLetter;
    
    @Column(name = "resume_path")
    private String resumePath;
    
    private Integer aiMatchScore; // AI-generated compatibility score
    
    private String applicationNotes; // Internal notes from recruiters
    
    private Boolean aiRecommended = false; // AI recommendation flag
    
    private String interviewScheduled; // Interview scheduling info
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Application() {}
    
    public Application(Job job, User applicant) {
        this.job = job;
        this.applicant = applicant;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    
    public User getApplicant() { return applicant; }
    public void setApplicant(User applicant) { this.applicant = applicant; }
    
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    
    public String getResumePath() { return resumePath; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Integer getAiMatchScore() { return aiMatchScore; }
    public void setAiMatchScore(Integer aiMatchScore) { this.aiMatchScore = aiMatchScore; }
    
    public String getApplicationNotes() { return applicationNotes; }
    public void setApplicationNotes(String applicationNotes) { this.applicationNotes = applicationNotes; }
    
    public Boolean getAiRecommended() { return aiRecommended; }
    public void setAiRecommended(Boolean aiRecommended) { this.aiRecommended = aiRecommended; }
    
    public String getInterviewScheduled() { return interviewScheduled; }
    public void setInterviewScheduled(String interviewScheduled) { this.interviewScheduled = interviewScheduled; }
}
