-- Create database
CREATE DATABASE IF NOT EXISTS zidioconnect;
USE zidioconnect;

-- Create tables (Spring Boot JPA will handle this automatically, but here's the schema for reference)

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role ENUM('STUDENT', 'RECRUITER', 'ADMIN') NOT NULL,
    is_enabled BOOLEAN DEFAULT TRUE,
    is_verified BOOLEAN DEFAULT FALSE,
    phone VARCHAR(15),
    bio TEXT,
    avatar VARCHAR(200),
    location VARCHAR(100),
    company VARCHAR(100),
    website VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Jobs table
CREATE TABLE IF NOT EXISTS jobs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    company VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    type ENUM('FULL_TIME', 'PART_TIME', 'INTERNSHIP', 'CONTRACT') NOT NULL,
    mode ENUM('ONSITE', 'REMOTE', 'HYBRID') NOT NULL,
    salary VARCHAR(100),
    description TEXT NOT NULL,
    requirements TEXT,
    benefits TEXT,
    posted_by BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (posted_by) REFERENCES users(id)
);

-- Applications table
CREATE TABLE IF NOT EXISTS applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT NOT NULL,
    applicant_id BIGINT NOT NULL,
    status ENUM('PENDING', 'REVIEWED', 'SHORTLISTED', 'REJECTED', 'ACCEPTED') DEFAULT 'PENDING',
    cover_letter TEXT,
    resume_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (applicant_id) REFERENCES users(id),
    UNIQUE KEY unique_application (job_id, applicant_id)
);

-- Resumes table
CREATE TABLE IF NOT EXISTS resumes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT,
    is_primary BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert sample admin user (password: admin123)
INSERT INTO users (username, email, password, first_name, last_name, role, is_enabled, is_verified) 
VALUES ('admin', 'admin@zidioconnect.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iYqiSfF1g3/5Kj8K8K8K8K8K8K8K', 'Admin', 'User', 'ADMIN', TRUE, TRUE)
ON DUPLICATE KEY UPDATE username=username;

-- Insert sample recruiter user (password: recruiter123)
INSERT INTO users (username, email, password, first_name, last_name, role, is_enabled, is_verified, company) 
VALUES ('recruiter', 'recruiter@zidioconnect.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iYqiSfF1g3/5Kj8K8K8K8K8K8K8K', 'John', 'Recruiter', 'RECRUITER', TRUE, TRUE, 'Tech Corp')
ON DUPLICATE KEY UPDATE username=username;

-- Insert sample student user (password: student123)
INSERT INTO users (username, email, password, first_name, last_name, role, is_enabled, is_verified) 
VALUES ('student', 'student@zidioconnect.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iYqiSfF1g3/5Kj8K8K8K8K8K8K8K', 'Jane', 'Student', 'STUDENT', TRUE, TRUE)
ON DUPLICATE KEY UPDATE username=username;
