# 🔧 ZIDIOConnect Backend Environment Setup Guide

## 📁 Environment Files Created

### 1. **`env.example`** - Template File
- **Purpose**: Template for all environment variables
- **Usage**: Copy to `.env` and customize
- **Contains**: All possible configuration options

### 2. **`env.development`** - Development Environment
- **Purpose**: Development-specific configuration
- **Database**: H2 in-memory database
- **Logging**: Verbose (DEBUG level)
- **CORS**: Local development URLs
- **AI/ML**: Disabled for faster development

### 3. **`env.production`** - Production Environment
- **Purpose**: Production-specific configuration
- **Database**: MySQL with secure credentials
- **Logging**: Minimal (WARN/INFO level)
- **CORS**: Production URLs only
- **AI/ML**: Full features enabled
- **Security**: Enhanced security settings

### 4. **`application-env.yml`** - Spring Boot Configuration
- **Purpose**: Spring Boot configuration using environment variables
- **Location**: `src/main/resources/application-env.yml`
- **Features**: All settings configurable via environment variables

### 5. **`load-env.bat`** - Environment Loader Script
- **Purpose**: Interactive script to load environment variables
- **Usage**: Run before starting the application
- **Features**: Choose between development/production/custom

## 🚀 Quick Setup

### **Step 1: Choose Your Environment**

#### **For Development:**
```bash
# Copy development environment
copy env.development .env

# Or use the loader script
load-env.bat
# Select option 1 (Development)
```

#### **For Production:**
```bash
# Copy production environment
copy env.production .env

# Edit the .env file with your actual values
# Update database credentials, JWT secret, etc.
```

### **Step 2: Configure Environment Variables**

#### **Required Variables for Production:**
```bash
# Database
DB_URL=jdbc:mysql://your-db-host:3306/zidioconnect
DB_USERNAME=your_username
DB_PASSWORD=your_secure_password

# JWT Secret (Generate a strong secret)
JWT_SECRET=your_very_strong_jwt_secret_here

# Email Configuration
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# CORS (Update with your frontend URL)
CORS_ALLOWED_ORIGINS=https://your-app.vercel.app
```

### **Step 3: Start the Application**

#### **Using Environment Loader:**
```bash
load-env.bat
```

#### **Using Maven with Profile:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=env
```

#### **Using JAR with Environment:**
```bash
java -jar target/careerbridgepro-backend-1.0.0.jar --spring.profiles.active=env
```

## 🔐 Security Best Practices

### **Environment File Security:**
1. **Never commit `.env` files** to version control
2. **Use strong JWT secrets** (64+ characters)
3. **Use secure database passwords**
4. **Enable SSL in production**
5. **Restrict CORS origins** to your actual domains

### **Production Checklist:**
- [ ] Strong JWT secret generated
- [ ] Database credentials secured
- [ ] Email credentials configured
- [ ] CORS origins restricted
- [ ] SSL/TLS enabled
- [ ] Logging level set to WARN/INFO
- [ ] File upload paths secured

## 📋 Environment Variables Reference

### **Server Configuration:**
| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8080 | Server port |
| `SERVER_CONTEXT_PATH` | /api | API context path |

### **Database Configuration:**
| Variable | Default | Description |
|----------|---------|-------------|
| `DB_URL` | jdbc:h2:mem:testdb | Database URL |
| `DB_USERNAME` | sa | Database username |
| `DB_PASSWORD` | (empty) | Database password |
| `DB_DRIVER` | org.h2.Driver | Database driver |

### **JWT Configuration:**
| Variable | Default | Description |
|----------|---------|-------------|
| `JWT_SECRET` | mySecretKey... | JWT signing secret |
| `JWT_EXPIRATION` | 86400000 | Token expiration (ms) |

### **Email Configuration:**
| Variable | Default | Description |
|----------|---------|-------------|
| `MAIL_HOST` | smtp.gmail.com | SMTP host |
| `MAIL_PORT` | 587 | SMTP port |
| `MAIL_USERNAME` | your-email@gmail.com | Email username |
| `MAIL_PASSWORD` | your-app-password | Email password |

### **CORS Configuration:**
| Variable | Default | Description |
|----------|---------|-------------|
| `CORS_ALLOWED_ORIGINS` | http://localhost:3000,... | Allowed origins |
| `CORS_ALLOWED_METHODS` | GET,POST,PUT,DELETE,OPTIONS | Allowed methods |
| `CORS_ALLOW_CREDENTIALS` | true | Allow credentials |

## 🛠️ Deployment Options

### **Railway.app:**
```bash
# Set environment variables in Railway dashboard
# Upload your JAR file
# Railway will automatically detect Spring Boot
```

### **Heroku:**
```bash
# Create Procfile
echo "web: java -jar target/careerbridgepro-backend-1.0.0.jar" > Procfile

# Set environment variables
heroku config:set JWT_SECRET=your_secret
heroku config:set DB_URL=your_database_url
```

### **Docker:**
```dockerfile
# Use environment variables in Docker
ENV JWT_SECRET=your_secret
ENV DB_URL=your_database_url
```

## 🔄 Environment Switching

### **Development to Production:**
1. Copy `env.production` to `.env`
2. Update database credentials
3. Generate strong JWT secret
4. Update CORS origins
5. Restart application

### **Production to Development:**
1. Copy `env.development` to `.env`
2. Restart application
3. Database will reset (H2 in-memory)

## 🆘 Troubleshooting

### **Common Issues:**
1. **Environment variables not loading**: Check file format (no spaces around `=`)
2. **Database connection failed**: Verify credentials and URL
3. **CORS errors**: Check allowed origins
4. **JWT errors**: Verify secret is set correctly

### **Debug Mode:**
```bash
# Enable debug logging
LOG_LEVEL_APP=DEBUG
LOG_LEVEL_SECURITY=DEBUG
```

---

**Your ZIDIOConnect backend is now fully configured with environment management! 🎉**
