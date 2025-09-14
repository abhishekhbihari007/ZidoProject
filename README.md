# ZIDIOConnect 🚀

<div align="center">
  <img src="https://img.shields.io/badge/React-19.1.0-blue.svg" alt="React Version" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.0-green.svg" alt="Spring Boot Version" />
  <img src="https://img.shields.io/badge/Java-17-orange.svg" alt="Java Version" />
  <img src="https://img.shields.io/badge/MySQL-8.0-blue.svg" alt="MySQL Version" />
  <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License" />
</div>

<br>

**ZIDIOConnect** is a comprehensive job portal application that streamlines internship and job application management. It provides role-based dashboards for Students, Recruiters, and Administrators, making the hiring process efficient and user-friendly.

## 🆕 Recent Updates

- ✅ **Fixed Login Validation**: Updated authentication to use username instead of email
- ✅ **Enhanced Registration**: Improved form validation and user experience
- ✅ **Environment Management**: Added comprehensive environment variable system
- ✅ **Vercel Deployment**: Ready for frontend deployment on Vercel
- ✅ **Production Builds**: Frontend and backend builds optimized for production
- ✅ **Security Improvements**: Enhanced JWT configuration and CORS settings

## 🌟 Features

### 👨‍🎓 Student Dashboard
- **User Authentication**: Secure login and registration with JWT tokens
- **Profile Management**: Basic profile creation and editing functionality
- **Resume Upload**: File upload system for PDF/DOC resume documents
- **Job Discovery**: Browse and search available job/internship listings
- **Application System**: Apply for positions and track application status
- **Dashboard Overview**: Personal statistics and quick action cards

### 🧑‍💼 Recruiter Dashboard
- **Job Posting**: Create and manage job/internship listings
- **Excel Upload**: Bulk upload job listings via Excel files with template support
- **Application Management**: View and manage candidate applications
- **Job Management**: Edit, update, and delete posted job listings
- **File Processing**: Advanced Excel processing with error reporting and validation
- **Dashboard Analytics**: Basic statistics for posted jobs and applications

### 🛡️ Admin Panel
- **User Management**: View, approve, and block user accounts
- **System Statistics**: Overview of total users, jobs, and applications
- **User Status Control**: Enable/disable user accounts
- **Platform Monitoring**: Basic system health and usage metrics
- **Data Management**: Access to all platform data and user information

### 🔧 Technical Features
- **JWT Authentication**: Secure token-based authentication system
- **Role-based Access Control**: Granular permissions for different user types
- **File Upload System**: Secure file handling for resumes and Excel documents
- **Excel Processing**: Automated job listing import from Excel files with validation
- **Responsive Design**: Mobile-friendly interface with Tailwind CSS
- **Real-time Notifications**: Toast notifications for user actions
- **RESTful APIs**: Comprehensive backend API with proper error handling

## 🛠️ Tech Stack

### Frontend
- **React 19.1.0** - Modern UI library
- **Vite** - Fast build tool and development server
- **Tailwind CSS 4.1.10** - Utility-first CSS framework
- **React Router DOM 7.6.2** - Client-side routing
- **Axios** - HTTP client for API communication
- **React Toastify** - Notification system
- **Heroicons & Lucide React** - Icon libraries

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence layer
- **MySQL 8.0** - Primary database
- **JWT (jjwt 0.11.5)** - Token-based authentication
- **Apache POI 5.2.4** - Excel file processing
- **Maven** - Dependency management

### Development Tools
- **Git** - Version control
- **Postman** - API testing
- **ESLint** - Code linting
- **Spring Boot DevTools** - Development utilities

## 📁 Project Structure

```
ZidioConnect/
├── client/                     # React Frontend
│   ├── src/
│   │   ├── components/         # Reusable UI components
│   │   ├── pages/             # Page components
│   │   │   ├── admin/         # Admin dashboard pages
│   │   │   ├── recruiter/     # Recruiter dashboard pages
│   │   │   └── student/       # Student dashboard pages
│   │   ├── services/          # API service layer
│   │   ├── context/           # React context providers
│   │   └── hooks/             # Custom React hooks
│   ├── public/                # Static assets
│   ├── dist/                  # Production build
│   ├── vercel.json            # Vercel deployment config
│   └── package.json           # Frontend dependencies
├── backend/                   # Spring Boot Backend
│   ├── src/main/java/com/careerbridgepro/
│   │   ├── controller/        # REST API controllers
│   │   ├── entity/           # JPA entities
│   │   ├── repository/       # Data access layer
│   │   ├── service/          # Business logic layer
│   │   ├── security/         # Security configuration
│   │   ├── dto/              # Data transfer objects
│   │   └── config/           # Application configuration
│   ├── src/main/resources/
│   │   ├── application.yml    # Application configuration
│   │   └── application-env.yml # Environment-based config
│   ├── env.example            # Environment template
│   ├── env.development        # Development environment
│   ├── env.production         # Production environment
│   ├── load-env.bat           # Environment loader script
│   └── target/               # Compiled classes and JAR
├── vercel.json                # Root Vercel configuration
├── vercel.js                  # Advanced Vercel configuration
└── README.md                  # Project documentation
```

## 🚀 Quick Start

### Prerequisites

- **Java 17** or higher
- **Node.js 18** or higher
- **MySQL 8.0** or higher
- **Maven 3.6+**
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/abhishekhbihari007/ZidioProject.git
   cd ZidioConnect
   ```

2. **Backend Setup**
   ```bash
   cd backend
   
   # Set up environment variables
   copy env.example .env
   # Edit .env file with your configuration
   
   # For development (uses H2 database)
   copy env.development .env
   
   # Install dependencies and run
   mvn clean install
   mvn spring-boot:run
   ```
   Backend will be available at `http://localhost:8080/api`

3. **Frontend Setup**
   ```bash
   cd client
   
   # Install dependencies
   npm install
   
   # Start development server
   npm run dev
   ```
   Frontend will be available at `http://localhost:3000`

4. **Environment Configuration**
   - **Development**: Uses H2 in-memory database (no setup required)
   - **Production**: Configure MySQL database in environment variables
   - See `backend/ENVIRONMENT_SETUP_GUIDE.md` for detailed configuration

### Default Login Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Recruiter | recruiter | recruiter123 |
| Student | student | student123 |

## 📚 API Documentation

### Authentication Endpoints
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/forgot-password` - Password reset request
- `POST /api/auth/reset-password` - Password reset confirmation

### Job Management
- `GET /api/jobs` - Get all active jobs
- `GET /api/jobs/{id}` - Get job by ID
- `GET /api/jobs/search?keyword=...` - Search jobs
- `POST /api/jobs` - Create new job (Recruiter only)
- `PUT /api/jobs/{id}` - Update job
- `DELETE /api/jobs/{id}` - Delete job

### Application Management
- `GET /api/applications` - Get my applications (Student)
- `GET /api/applications/job/{jobId}` - Get job applications (Recruiter)
- `POST /api/applications` - Apply for job (Student)
- `PUT /api/applications/{id}/status` - Update application status (Recruiter)

### File Upload
- `POST /api/upload/resume` - Upload resume file
- `POST /api/upload/excel` - Upload Excel job listings

### Admin Operations
- `GET /api/admin/stats` - Get system statistics
- `GET /api/admin/users` - Get all users
- `PUT /api/admin/users/{id}/status` - Update user status
- `DELETE /api/admin/users/{id}` - Delete user

## 🔧 Configuration

### Backend Configuration (`application.yml`)
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zidioconnect?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: your-secret-key-here
  expiration: 86400000

file:
  upload-dir: ./uploads/

# Additional configuration options can be added here
```

### Frontend Configuration
Update API base URL in `src/services/axios.js`:
```javascript
const API_BASE_URL = 'http://localhost:8080/api';
```

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing
```bash
cd client
npm run lint
npm run build
```

## 🚀 Deployment

### Frontend Deployment (Vercel)
```bash
cd client
npm run build

# Deploy to Vercel
# 1. Go to https://vercel.com
# 2. Import your GitHub repository
# 3. Set Root Directory to 'client'
# 4. Deploy!
```

### Backend Deployment
```bash
cd backend

# Build JAR file
mvn clean package

# Deploy to Railway/Heroku/AWS
# Set environment variables in your hosting platform:
# - JWT_SECRET
# - DB_URL, DB_USERNAME, DB_PASSWORD
# - MAIL_USERNAME, MAIL_PASSWORD
# - CORS_ALLOWED_ORIGINS (your frontend URL)
```

### Environment Variables for Production
- **JWT_SECRET**: Strong secret key for token signing
- **DB_URL**: Production database connection string
- **MAIL_USERNAME/MAIL_PASSWORD**: Email service credentials
- **CORS_ALLOWED_ORIGINS**: Your frontend domain

See `backend/ENVIRONMENT_SETUP_GUIDE.md` for complete configuration details.

## 🎯 Key Features Overview

### 🏠 Landing Page
- Modern, responsive design with hero section
- Key statistics and platform highlights
- Clear call-to-action for user registration
- Mobile-friendly interface

### ✨ Features Showcase
- Elite Student Hub for career development
- Recruiter Excellence tools for talent acquisition
- Smart Analytics for data-driven decisions
- Comprehensive job matching system

### 🌟 Success Stories
- Testimonials from successful students and recruiters
- Platform success metrics and achievements
- User experience highlights

### 🔐 Authentication System
- Secure login interface with modern design
- User registration with role selection
- JWT-based authentication
- Password reset functionality

### 📊 Dashboard Views
- **Student Dashboard**: Job listings, applications, and profile management
- **Recruiter Dashboard**: Job posting, application management, and analytics
- **Admin Panel**: User management, system statistics, and platform monitoring

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes**
4. **Add tests** for new functionality
5. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```
6. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```
7. **Open a Pull Request**

### Development Guidelines

- Follow the existing code style
- Write meaningful commit messages
- Add comments for complex logic
- Update documentation for new features
- Ensure all tests pass

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL is running
   - Verify database credentials in `application.yml`
   - Check if database `zidioconnect` exists

2. **Port Conflicts**
   - Backend: Change port in `application.yml` (default: 8080)
   - Frontend: Change port in `vite.config.js` (default: 5173)

3. **CORS Issues**
   - Update CORS configuration in `SecurityConfig.java`
   - Ensure frontend URL is allowed

4. **JWT Token Issues**
   - Verify JWT secret is set in `application.yml`
   - Check token expiration settings

5. **File Upload Issues**
   - Ensure upload directories exist
   - Check file size limits
   - Verify file permissions

## 📈 Future Enhancements

- [ ] **Real-time Chat System** - Direct communication between recruiters and candidates
- [ ] **Advanced Analytics** - Detailed reporting and insights
- [ ] **Email Notifications** - Automated email alerts
- [ ] **Resume Builder** - Built-in resume creation tool
- [ ] **Video Interviews** - Integrated video calling
- [ ] **Mobile App** - React Native mobile application
- [ ] **AI-Powered Matching** - Smart job-candidate matching
- [ ] **Multi-language Support** - Internationalization

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Developer

**ZIDIOConnect Project**
- 🌐 GitHub: [abhishekhbihari007/ZidioProject](https://github.com/abhishekhbihari007/ZidioProject)
- 📧 Contact: Available through GitHub issues
- 🚀 Live Demo: Deploy to Vercel for instant access

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation and enterprise-grade features
- React team for the revolutionary frontend framework
- Tailwind CSS for the utility-first approach and design system
- OpenAI and ML community for AI/ML integration inspiration
- All contributors, testers, and early adopters of ZIDIOConnect

---

<div align="center">
  <p>Made with ❤️ by the ZIDIOConnect Team</p>
  <p>⭐ Star this repository if you found it helpful!</p>
  <p>🚀 Join the future of talent acquisition!</p>
  <p>&copy; 2025 ZIDIOConnect. All rights reserved.</p>
</div>
"# ZidoProject" 
