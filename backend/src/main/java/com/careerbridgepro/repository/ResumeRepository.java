package com.careerbridgepro.repository;

import com.careerbridgepro.entity.Resume;
import com.careerbridgepro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    
    List<Resume> findByUser(User user);
    
    Optional<Resume> findByUserAndIsPrimaryTrue(User user);
    
    List<Resume> findByUserOrderByUploadedAtDesc(User user);
}
