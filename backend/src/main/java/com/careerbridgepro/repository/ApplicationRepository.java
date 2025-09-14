package com.careerbridgepro.repository;

import com.careerbridgepro.entity.Application;
import com.careerbridgepro.entity.ApplicationStatus;
import com.careerbridgepro.entity.Job;
import com.careerbridgepro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    List<Application> findByApplicant(User applicant);
    
    List<Application> findByJob(Job job);
    
    List<Application> findByStatus(ApplicationStatus status);
    
    Optional<Application> findByJobAndApplicant(Job job, User applicant);
    
    boolean existsByJobAndApplicant(Job job, User applicant);
    
    List<Application> findByJobPostedBy(User recruiter);
}
