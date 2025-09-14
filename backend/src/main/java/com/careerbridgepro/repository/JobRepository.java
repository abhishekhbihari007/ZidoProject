package com.careerbridgepro.repository;

import com.careerbridgepro.entity.Job;
import com.careerbridgepro.entity.JobMode;
import com.careerbridgepro.entity.JobType;
import com.careerbridgepro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    List<Job> findByActiveTrue();
    
    List<Job> findByPostedBy(User postedBy);
    
    List<Job> findByType(JobType type);
    
    List<Job> findByMode(JobMode mode);
    
    List<Job> findByCompanyContainingIgnoreCase(String company);
    
    List<Job> findByTitleContainingIgnoreCase(String title);
    
    List<Job> findByLocationContainingIgnoreCase(String location);
    
    @Query("SELECT j FROM Job j WHERE j.active = true AND " +
           "(LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Job> searchJobs(@Param("keyword") String keyword);
}
