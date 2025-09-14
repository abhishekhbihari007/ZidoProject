# ZidioConnect Backend

Spring Boot REST API for the ZidioConnect Job Portal.

## Features

- **Authentication**: JWT-based authentication with role-based access control
- **User Management**: Student, Recruiter, and Admin user types
- **Job Management**: Create, read, update, delete job postings
- **Application Management**: Apply for jobs and manage applications
- **File Upload**: Resume upload functionality
- **Admin Panel**: User management and system analytics

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security**
- **Spring Data JPA**
- **MySQL 8.0**
- **JWT Authentication**
- **Maven**

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

### Database Setup

1. Install MySQL and create a database:
```sql
CREATE DATABASE zidioconnect;
```

2. Update `application.yml` with your database credentials:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zidioconnect?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: your_username
    password: your_password
```

### Running the Application

1. Clone the repository and navigate to the backend directory:
```bash
cd backend
```

2. Install dependencies:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api`

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Jobs
- `GET /api/jobs` - Get all active jobs
- `GET /api/jobs/{id}` - Get job by ID
- `GET /api/jobs/search?keyword=...` - Search jobs
- `POST /api/jobs` - Create new job (Recruiter only)
- `PUT /api/jobs/{id}` - Update job
- `DELETE /api/jobs/{id}` - Delete job

### Applications
- `GET /api/applications` - Get my applications (Student)
- `GET /api/applications/job/{jobId}` - Get job applications (Recruiter)
- `POST /api/applications` - Apply for job (Student)
- `PUT /api/applications/{id}/status` - Update application status (Recruiter)

### Admin
- `GET /api/admin/stats` - Get system statistics
- `GET /api/admin/users` - Get all users
- `PUT /api/admin/users/{id}/status` - Update user status
- `DELETE /api/admin/users/{id}` - Delete user

## Sample Users

The application comes with sample users:

- **Admin**: username: `admin`, password: `admin123`
- **Recruiter**: username: `recruiter`, password: `recruiter123`
- **Student**: username: `student`, password: `student123`

## Configuration

Key configuration options in `application.yml`:

- **JWT Secret**: Change `jwt.secret` for production
- **Database**: Update connection details
- **CORS**: Configure allowed origins
- **File Upload**: Set upload directories

## Security

- JWT-based authentication
- Role-based access control (STUDENT, RECRUITER, ADMIN)
- Password encryption with BCrypt
- CORS configuration for frontend integration

## Development

### Project Structure
```
src/main/java/com/zidioconnect/
├── controller/     # REST controllers
├── entity/         # JPA entities
├── repository/     # Data repositories
├── service/        # Business logic
├── security/       # Security configuration
└── dto/           # Data transfer objects
```

### Adding New Features

1. Create entity classes in `entity/` package
2. Create repository interfaces in `repository/` package
3. Create service classes in `service/` package
4. Create REST controllers in `controller/` package
5. Add security rules in `SecurityConfig.java`

## Testing

Run tests with:
```bash
mvn test
```

## Deployment

1. Build the application:
```bash
mvn clean package
```

2. Run the JAR file:
```bash
java -jar target/zidioconnect-backend-1.0.0.jar
```

## Troubleshooting

### Common Issues

1. **Database Connection**: Ensure MySQL is running and credentials are correct
2. **Port Conflicts**: Change server port in `application.yml` if 8080 is occupied
3. **CORS Issues**: Update CORS configuration for your frontend URL
4. **JWT Issues**: Ensure JWT secret is set and consistent

### Logs

Check application logs for detailed error information. Logs are configured to show DEBUG level for the application package.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is licensed under the MIT License.
