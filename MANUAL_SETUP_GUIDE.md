# 📋 **ZidioConnect Manual Setup & Demo Guide**

## 🎯 **Quick Status Check**

Before starting, check if applications are already running:

### Check Backend (Port 8080)
```cmd
netstat -an | findstr :8080
```
If you see output, backend is running.

### Check Frontend (Port 5173)
```cmd
netstat -an | findstr :5173
```
If you see output, frontend is running.

---

## 🔧 **Prerequisites**

### **1. Java 17+ Installation**
```cmd
java -version
```
Should show Java 17 or higher.

### **2. Node.js 16+ Installation**
```cmd
node -v
npm -v
```

### **3. Maven Installation**
Your project has Maven pre-installed at:
```
C:\maven\apache-maven-3.9.6\bin\mvn.cmd
```

---

## 🚀 **Step-by-Step Startup Process**

### **Step 1: Start the Backend (Spring Boot)**

1. **Open Command Prompt**
2. **Navigate to backend directory:**
   ```cmd
   cd C:\Users\bihar\Desktop\zido\ZidioConnect\backend
   ```

3. **Option A: Use Maven with full path (Recommended)**
   ```cmd
   C:\maven\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run
   ```

4. **Option B: Use provided batch file**
   ```cmd
   run.bat
   ```

5. **Wait for startup** - Look for this message:
   ```
   Started ZidioConnectApplication in X.XXX seconds
   Tomcat started on port 8080 (http) with context path '/api'
   ```

6. **Test Backend** (in a new terminal):
   ```cmd
   curl http://localhost:8080/api/jobs
   ```

### **Step 2: Start the Frontend (React + Vite)**

1. **Open a NEW Command Prompt**
2. **Navigate to frontend directory:**
   ```cmd
   cd C:\Users\bihar\Desktop\zido\ZidioConnect\client
   ```

3. **Install dependencies (first time only):**
   ```cmd
   npm install
   ```

4. **Start the development server:**
   ```cmd
   npm run dev
   ```

5. **Wait for startup** - Look for this message:
   ```
   VITE v6.3.5 ready in XXX ms
   ➜  Local:   http://localhost:5173/
   ```

---

## 🌐 **Access URLs**

### **Frontend (Main Application)**
- **URL**: http://localhost:5173/
- **Description**: Complete React application interface

### **Backend API**
- **URL**: http://localhost:8080/api/
- **Test Endpoint**: http://localhost:8080/api/jobs

### **H2 Database Console** (Optional)
- **URL**: http://localhost:8080/api/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

---

## 🎭 **Demo Features to Showcase**

### **1. User Registration & Login**
- Register as Student: http://localhost:5173/register
- Register as Recruiter: http://localhost:5173/register
- Login: http://localhost:5173/login

### **2. Student Features**
- Dashboard: View job statistics and quick actions
- Job Search: Browse and filter available positions
- Application Management: Track application status
- Resume Upload: Upload and manage resumes
- Profile Management: Update personal information

### **3. Recruiter Features**
- Dashboard: Manage posted jobs and applications
- Post Jobs: Create new job listings
- Application Review: Review and manage candidates
- Company Profile: Update company information

### **4. Professional UI Features**
- Modern gradient design
- Responsive mobile layout
- Smooth animations and transitions
- Interactive cards and buttons
- Professional color scheme

---

## 🛠️ **Troubleshooting**

### **Backend Issues**

#### **Port 8080 Already in Use**
```cmd
netstat -ano | findstr :8080
taskkill /PID [PID_NUMBER] /F
```

#### **Maven Command Not Found**
Use full path:
```cmd
C:\maven\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run
```

#### **Database Connection Issues**
The app uses H2 in-memory database - no external database needed.

### **Frontend Issues**

#### **Port 5173 Already in Use**
```cmd
netstat -ano | findstr :5173
taskkill /PID [PID_NUMBER] /F
```

#### **Node Modules Issues**
```cmd
cd C:\Users\bihar\Desktop\zido\ZidioConnect\client
rm -rf node_modules
npm install
```

#### **Build Issues**
```cmd
npm run build
```

---

## 📊 **Demo Script for Teachers**

### **1. Introduction (2 minutes)**
- "This is ZidioConnect, a full-stack job portal application"
- "Built with Spring Boot backend and React frontend"
- "Features user authentication, job management, and application tracking"

### **2. Registration Demo (3 minutes)**
- Show student registration
- Show recruiter registration
- Highlight form validation and professional UI

### **3. Student Workflow (5 minutes)**
- Login as student
- Browse jobs on dashboard
- Search and filter jobs
- Apply to a position
- Upload resume
- Check application status

### **4. Recruiter Workflow (5 minutes)**
- Login as recruiter
- Post a new job
- Review applications
- Manage job listings
- Update company profile

### **5. Technical Features (3 minutes)**
- Show responsive design (resize browser)
- Demonstrate real-time updates
- Show API endpoints working
- Highlight security features (JWT tokens)

### **6. Architecture Overview (2 minutes)**
- Spring Boot REST API
- React SPA frontend
- H2 in-memory database
- JWT authentication
- Professional grade styling

---

## 🔒 **Security Features**

- JWT-based authentication
- Password encryption (BCrypt)
- Role-based access control (Student/Recruiter/Admin)
- CORS configuration
- Input validation and sanitization

---

## 📱 **Mobile Responsiveness**

The application is fully responsive and works on:
- Desktop computers
- Tablets
- Mobile phones
- Different screen orientations

---

## 🎨 **Professional Design Elements**

- Modern gradient backgrounds
- Professional color scheme (Blues, Purples, Greens)
- Interactive hover effects
- Smooth transitions and animations
- Card-based layouts
- Professional typography (Inter font)
- Consistent spacing and alignment

---

## ⚡ **Quick Commands Reference**

### **Start Everything**
```cmd
# Terminal 1 - Backend
cd C:\Users\bihar\Desktop\zido\ZidioConnect\backend
C:\maven\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run

# Terminal 2 - Frontend
cd C:\Users\bihar\Desktop\zido\ZidioConnect\client
npm run dev
```

### **Stop Everything**
- Press `Ctrl+C` in both terminal windows

### **Check Status**
```cmd
netstat -an | findstr ":8080 :5173"
```

---

## 🎓 **College Project Highlights**

### **Technical Skills Demonstrated**
- Full-stack web development
- RESTful API design
- Database design and relationships
- Frontend state management
- User authentication and authorization
- Responsive web design
- Professional UI/UX design

### **Technologies Used**
- **Backend**: Spring Boot, Spring Security, JPA/Hibernate, H2 Database
- **Frontend**: React, Vite, Tailwind CSS, React Router
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tools**: Maven, npm
- **Development**: Hot reload, Live reload

### **Best Practices Implemented**
- MVC architecture pattern
- RESTful API conventions
- Component-based frontend architecture
- Secure password handling
- Professional error handling
- Clean code structure
- Responsive design principles

---

## 📞 **Support**

If you encounter any issues during the demo:

1. **Check both terminals are running**
2. **Verify URLs are accessible**
3. **Check for error messages in terminal**
4. **Restart applications if needed**

**Good luck with your presentation! 🎉**
