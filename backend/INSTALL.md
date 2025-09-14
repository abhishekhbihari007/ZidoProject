# ZidioConnect Backend - Installation Guide

## Prerequisites

Before running the ZidioConnect backend, you need to install the following:

### 1. Java 17 or Higher
- Download from: https://adoptium.net/
- Or install via package manager:
  - **Windows (Chocolatey)**: `choco install openjdk17`
  - **Windows (Winget)**: `winget install EclipseAdoptium.Temurin.17.JDK`

### 2. Maven 3.6+
- Download from: https://maven.apache.org/download.cgi
- Or install via package manager:
  - **Windows (Chocolatey)**: `choco install maven`
  - **Windows (Winget)**: `winget install Apache.Maven`

### 3. MySQL 8.0+
- Download from: https://dev.mysql.com/downloads/mysql/
- Or install via package manager:
  - **Windows (Chocolatey)**: `choco install mysql`
  - **Windows (Winget)**: `winget install Oracle.MySQL`

## Quick Start

### Option 1: Using the Run Script (Recommended)
1. Double-click `run.bat` in the backend directory
2. The script will check prerequisites and start the application

### Option 2: Manual Commands
1. Open Command Prompt in the backend directory
2. Run the following commands:

```bash
# Clean and compile
mvn clean compile

# Start the application
mvn spring-boot:run
```

## Database Setup

### 1. Install MySQL
- Install MySQL 8.0+ on your system
- Start the MySQL service

### 2. Create Database
```sql
CREATE DATABASE zidioconnect;
```

### 3. Update Configuration
Edit `src/main/resources/application.yml` and update the database connection:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zidioconnect?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: your_username
    password: your_password
```

### 4. Run Database Script
Execute the SQL script in `database.sql` to create tables and sample data.

## Application Access

Once started, the application will be available at:
- **API Base URL**: http://localhost:8080/api
- **Health Check**: http://localhost:8080/api/actuator/health

## Sample Users

The application comes with sample users:

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ADMIN |
| recruiter | recruiter123 | RECRUITER |
| student | student123 | STUDENT |

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Jobs
- `GET /api/jobs` - Get all jobs
- `POST /api/jobs` - Create job (Recruiter)
- `GET /api/jobs/{id}` - Get job by ID

### Applications
- `GET /api/applications` - Get my applications
- `POST /api/applications` - Apply for job

### File Upload
- `POST /api/upload/excel/jobs` - Upload jobs from Excel
- `GET /api/upload/excel/template` - Download Excel template
- `POST /api/upload/resume` - Upload resume

### Admin
- `GET /api/admin/stats` - Get system statistics
- `GET /api/admin/users` - Get all users

## Troubleshooting

### Common Issues

1. **Maven not found**
   - Install Maven and add it to your PATH
   - Restart Command Prompt after installation

2. **Java not found**
   - Install Java 17+ and add it to your PATH
   - Verify with `java -version`

3. **Database connection failed**
   - Ensure MySQL is running
   - Check database credentials in application.yml
   - Verify database exists

4. **Port 8080 already in use**
   - Change port in application.yml:
     ```yaml
     server:
       port: 8081
     ```

5. **Compilation errors**
   - Check Java version (must be 17+)
   - Clean and recompile: `mvn clean compile`

### Logs
Application logs are displayed in the console. For more detailed logging, check the console output.

## Development

### Project Structure
```
backend/
├── src/main/java/com/zidioconnect/
│   ├── controller/     # REST controllers
│   ├── entity/         # JPA entities
│   ├── repository/     # Data repositories
│   ├── service/        # Business logic
│   ├── security/       # Security configuration
│   └── dto/           # Data transfer objects
├── src/main/resources/
│   └── application.yml # Configuration
├── uploads/           # File uploads
└── database.sql       # Database schema
```

### Adding New Features
1. Create entity classes in `entity/` package
2. Create repository interfaces in `repository/` package
3. Create service classes in `service/` package
4. Create REST controllers in `controller/` package
5. Add security rules in `SecurityConfig.java`

## Support

If you encounter any issues:
1. Check the troubleshooting section above
2. Verify all prerequisites are installed
3. Check the console logs for error messages
4. Ensure database is properly configured
