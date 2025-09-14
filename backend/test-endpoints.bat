@echo off
echo ========================================
echo ZidioConnect Backend - API Test Demo
echo ========================================
echo.

echo This script demonstrates what the API endpoints would return
echo when the ZidioConnect backend is running.
echo.

echo ========================================
echo 1. Health Check Endpoint
echo ========================================
echo.
echo GET http://localhost:8080/api/actuator/health
echo.
echo Expected Response:
echo {
echo   "status": "UP",
echo   "components": {
echo     "db": {
echo       "status": "UP"
echo     },
echo     "diskSpace": {
echo       "status": "UP"
echo     }
echo   }
echo }
echo.

echo ========================================
echo 2. Login Endpoint
echo ========================================
echo.
echo POST http://localhost:8080/api/auth/login
echo Content-Type: application/json
echo.
echo Request Body:
echo {
echo   "username": "admin",
echo   "password": "admin123"
echo }
echo.
echo Expected Response:
echo {
echo   "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
echo   "role": "ADMIN",
echo   "name": "Admin User",
echo   "email": "admin@zidioconnect.com",
echo   "avatar": null
echo }
echo.

echo ========================================
echo 3. Get All Jobs
echo ========================================
echo.
echo GET http://localhost:8080/api/jobs
echo Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
echo.
echo Expected Response:
echo [
echo   {
echo     "id": 1,
echo     "title": "Software Engineer",
echo     "company": "Tech Corp",
echo     "location": "New York",
echo     "type": "FULL_TIME",
echo     "mode": "ONSITE",
echo     "salary": "$80,000 - $100,000",
echo     "description": "Develop software applications",
echo     "requirements": "Java, Spring Boot",
echo     "benefits": "Health insurance, 401k",
echo     "active": true,
echo     "createdAt": "2025-01-14T01:30:20",
echo     "updatedAt": "2025-01-14T01:30:20"
echo   }
echo ]
echo.

echo ========================================
echo 4. Excel Upload Endpoint
echo ========================================
echo.
echo POST http://localhost:8080/api/upload/excel/jobs
echo Content-Type: multipart/form-data
echo Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
echo.
echo Request Body: file (Excel file)
echo.
echo Expected Response:
echo {
echo   "success": true,
echo   "totalRows": 5,
echo   "successCount": 4,
echo   "errorCount": 1,
echo   "errors": ["Row 3: Title is required"],
echo   "jobs": [...]
echo }
echo.

echo ========================================
echo 5. Admin Statistics
echo ========================================
echo.
echo GET http://localhost:8080/api/admin/stats
echo Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
echo.
echo Expected Response:
echo {
echo   "totalUsers": 150,
echo   "totalJobs": 45,
echo   "totalApplications": 320
echo }
echo.

echo ========================================
echo 6. File Upload (Resume)
echo ========================================
echo.
echo POST http://localhost:8080/api/upload/resume
echo Content-Type: multipart/form-data
echo Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
echo.
echo Request Body: file (PDF/DOC/DOCX)
echo.
echo Expected Response:
echo {
echo   "message": "Resume uploaded successfully",
echo   "fileName": "resume.pdf",
echo   "filePath": "resumes/uuid-filename.pdf",
echo   "fileSize": 1024000
echo }
echo.

echo ========================================
echo 🎯 Summary of Features
echo ========================================
echo.
echo ✅ Authentication & Authorization (JWT)
echo ✅ User Management (Student, Recruiter, Admin)
echo ✅ Job CRUD Operations
echo ✅ Application Management
echo ✅ Excel Data Upload & Processing
echo ✅ File Upload System (Resumes)
echo ✅ Role-based Security
echo ✅ MySQL Database Integration
echo ✅ CORS Configuration
echo ✅ Error Handling & Validation
echo.

echo ========================================
echo 📊 Database Schema
echo ========================================
echo.
echo Tables Created:
echo • users - User accounts and profiles
echo • jobs - Job postings with full details
echo • applications - Job applications with status
echo • resumes - Resume file records
echo.

echo ========================================
echo 🚀 To Run the Actual Application:
echo ========================================
echo.
echo 1. Install Maven: https://maven.apache.org/download.cgi
echo 2. Install MySQL: https://dev.mysql.com/downloads/mysql/
echo 3. Create database: CREATE DATABASE zidioconnect;
echo 4. Update application.yml with database credentials
echo 5. Run: mvn spring-boot:run
echo 6. Access: http://localhost:8080/api
echo.

pause
