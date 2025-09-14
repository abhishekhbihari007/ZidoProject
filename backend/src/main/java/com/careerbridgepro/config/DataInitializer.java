package com.careerbridgepro.config;

import com.careerbridgepro.entity.User;
import com.careerbridgepro.entity.Job;
import com.careerbridgepro.entity.Role;
import com.careerbridgepro.entity.JobType;
import com.careerbridgepro.entity.JobMode;
import com.careerbridgepro.repository.UserRepository;
import com.careerbridgepro.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (userRepository.count() == 0) {
            initializeTestData();
        }
    }

    private void initializeTestData() {
        // Create Admin User
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@zidioconnect.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setRole(Role.ADMIN);
        admin.setEnabled(true);
        admin.setVerified(true);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        userRepository.save(admin);

        // Create Student User
        User student = new User();
        student.setUsername("student");
        student.setEmail("student@test.com");
        student.setPassword(passwordEncoder.encode("student123"));
        student.setFirstName("John");
        student.setLastName("Student");
        student.setRole(Role.STUDENT);
        student.setEnabled(true);
        student.setVerified(true);
        student.setBio("Computer Science student looking for internships");
        student.setLocation("New York, NY");
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        userRepository.save(student);

        // Create Recruiter User
        User recruiter = new User();
        recruiter.setUsername("recruiter");
        recruiter.setEmail("recruiter@company.com");
        recruiter.setPassword(passwordEncoder.encode("recruiter123"));
        recruiter.setFirstName("Jane");
        recruiter.setLastName("Recruiter");
        recruiter.setRole(Role.RECRUITER);
        recruiter.setEnabled(true);
        recruiter.setVerified(true);
        recruiter.setCompany("Tech Corp");
        recruiter.setBio("Senior Technical Recruiter at Tech Corp");
        recruiter.setLocation("San Francisco, CA");
        recruiter.setWebsite("https://techcorp.com");
        recruiter.setCreatedAt(LocalDateTime.now());
        recruiter.setUpdatedAt(LocalDateTime.now());
        userRepository.save(recruiter);

        // Create Sample Jobs
        createSampleJobs(recruiter);

        System.out.println("✅ Test data initialized successfully!");
        System.out.println("===========================================");
        System.out.println("🔑 Test Login Credentials:");
        System.out.println("===========================================");
        System.out.println("👨‍💼 Admin Login:");
        System.out.println("   Username: admin");
        System.out.println("   Password: admin123");
        System.out.println("");
        System.out.println("🎓 Student Login:");
        System.out.println("   Username: student");
        System.out.println("   Password: student123");
        System.out.println("");
        System.out.println("👩‍💼 Recruiter Login:");
        System.out.println("   Username: recruiter");
        System.out.println("   Password: recruiter123");
        System.out.println("===========================================");
    }

    private void createSampleJobs(User recruiter) {
        // Job 1: Frontend Developer
        Job job1 = new Job();
        job1.setTitle("Frontend Developer Intern");
        job1.setCompany("Tech Corp");
        job1.setDescription("We are looking for a passionate Frontend Developer intern to join our team. You will work on modern React applications and gain hands-on experience with cutting-edge technologies.");
        job1.setRequirements("• Proficiency in React.js and JavaScript\n• Knowledge of HTML5, CSS3, and responsive design\n• Familiarity with Git and version control\n• Strong problem-solving skills\n• Currently pursuing CS degree or equivalent");
        job1.setBenefits("• Competitive internship stipend\n• Mentorship from senior developers\n• Flexible working hours\n• Modern office environment\n• Career development opportunities");
        job1.setType(JobType.INTERNSHIP);
        job1.setMode(JobMode.HYBRID);
        job1.setLocation("San Francisco, CA");
        job1.setSalary("$25-30/hour");
        job1.setPostedBy(recruiter);
        job1.setActive(true);
        job1.setCreatedAt(LocalDateTime.now());
        job1.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(job1);

        // Job 2: Backend Developer
        Job job2 = new Job();
        job2.setTitle("Backend Developer");
        job2.setCompany("Tech Corp");
        job2.setDescription("Join our backend team to build scalable microservices and APIs. You'll work with Spring Boot, PostgreSQL, and cloud technologies.");
        job2.setRequirements("• 2+ years experience with Java/Spring Boot\n• Knowledge of RESTful APIs and microservices\n• Experience with SQL databases\n• Familiarity with cloud platforms (AWS/Azure)\n• Understanding of Docker and containerization");
        job2.setBenefits("• Competitive salary $80k-120k\n• Health insurance and 401k\n• Remote work flexibility\n• Professional development budget\n• Stock options");
        job2.setType(JobType.FULL_TIME);
        job2.setMode(JobMode.REMOTE);
        job2.setLocation("Remote");
        job2.setSalary("$80,000 - $120,000");
        job2.setPostedBy(recruiter);
        job2.setActive(true);
        job2.setCreatedAt(LocalDateTime.now());
        job2.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(job2);

        // Job 3: Data Analyst
        Job job3 = new Job();
        job3.setTitle("Data Analyst Intern");
        job3.setCompany("Tech Corp");
        job3.setDescription("Analyze large datasets to extract meaningful insights and support business decisions. Perfect opportunity for students interested in data science.");
        job3.setRequirements("• Proficiency in Python and SQL\n• Experience with data visualization tools\n• Knowledge of statistics and data analysis\n• Familiar with Pandas, NumPy, Matplotlib\n• Currently pursuing degree in related field");
        job3.setBenefits("• Learning-focused environment\n• Access to real-world datasets\n• Mentorship from data scientists\n• Potential for full-time offer\n• Flexible schedule");
        job3.setType(JobType.INTERNSHIP);
        job3.setMode(JobMode.ONSITE);
        job3.setLocation("New York, NY");
        job3.setSalary("$20-25/hour");
        job3.setPostedBy(recruiter);
        job3.setActive(true);
        job3.setCreatedAt(LocalDateTime.now());
        job3.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(job3);
    }
}
