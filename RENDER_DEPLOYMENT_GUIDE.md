# Render Deployment Guide

This guide will help you deploy ZidioConnect to Render.

## 🚨 Quick Fix: "package.json not found" Error

If you see: `Couldn't find a package.json file in "/opt/render/project/src"`

**Solution:** In Render Dashboard → Your Service → Settings → Build & Deploy:
- Set **Root Directory** to `client`
- Set **Build Command** to `npm install && npm run build`
- Set **Start Command** to `npx vite preview --host 0.0.0.0 --port $PORT`
- Click **Save Changes**

## Prerequisites

1. A Render account (sign up at https://render.com)
2. Your GitHub repository connected to Render
3. Environment variables configured

## Deployment Steps

### Option 1: Using render.yaml (Recommended)

The `render.yaml` file in the root directory automatically configures both frontend and backend services.

1. **Push render.yaml to your repository**
   ```bash
   git add render.yaml
   git commit -m "Add Render configuration"
   git push origin main
   ```

2. **In Render Dashboard:**
   - Go to your Render dashboard
   - Click "New" → "Blueprint"
   - Connect your GitHub repository
   - Render will automatically detect and deploy services from `render.yaml`

### Option 2: Manual Deployment

#### Frontend Deployment

1. **Create a new Static Site:**
   - Go to Render Dashboard
   - Click "New" → "Static Site"
   - Connect your GitHub repository

2. **Configure the Static Site:**
   - **Name:** zidioconnect-frontend
   - **Root Directory:** `client` (⚠️ **IMPORTANT: Set this to `client`**)
   - **Build Command:** `npm install && npm run build`
   - **Publish Directory:** `dist`
   - **Environment:** Node
   - **Node Version:** 18 or higher

3. **Environment Variables:**
   - `NODE_ENV=production`

#### Backend Deployment

1. **Create a new Web Service:**
   - Go to Render Dashboard
   - Click "New" → "Web Service"
   - Connect your GitHub repository

2. **Configure the Web Service:**
   - **Name:** zidioconnect-backend
   - **Root Directory:** `backend` (⚠️ **IMPORTANT: Set this to `backend`**)
   - **Environment:** Java
   - **Build Command:** `mvn clean package -DskipTests`
   - **Start Command:** `java -jar target/careerbridgepro-backend-1.0.0.jar`
   - **Java Version:** 17

3. **Environment Variables:**
   - `SPRING_PROFILES_ACTIVE=production`
   - `JAVA_VERSION=17`
   - `PORT=8080`
   - `JWT_SECRET=your-secret-key-here`
   - `DB_URL=jdbc:mysql://your-database-url`
   - `DB_USERNAME=your-database-username`
   - `DB_PASSWORD=your-database-password`
   - `MAIL_USERNAME=your-email@gmail.com`
   - `MAIL_PASSWORD=your-app-password`
   - `CORS_ALLOWED_ORIGINS=https://your-frontend-url.onrender.com`

### Database Setup

1. **Create a PostgreSQL Database:**
   - Go to Render Dashboard
   - Click "New" → "PostgreSQL"
   - Note the database credentials

2. **Update Backend Environment Variables:**
   - `DB_URL=jdbc:postgresql://your-postgres-host:5432/your-database-name`
   - `DB_USERNAME=your-postgres-username`
   - `DB_PASSWORD=your-postgres-password`

### Fixing the "package.json not found" Error

If you see the error: `Couldn't find a package.json file in "/opt/render/project/src"`

**Solution:**
1. Make sure the **Root Directory** is set correctly in Render dashboard:
   - For Frontend: Set to `client`
   - For Backend: Set to `backend`

2. If using render.yaml, ensure `rootDir` is specified:
   ```yaml
   rootDir: client  # for frontend
   rootDir: backend  # for backend
   ```

3. Verify the directory structure in your repository:
   ```
   ZidioConnect/
   ├── client/
   │   └── package.json  ✅
   ├── backend/
   │   └── pom.xml  ✅
   └── render.yaml  ✅
   ```

### Updating Frontend API URL

After deploying the backend, update the frontend to use the backend URL:

1. Update `client/src/services/axios.js`:
   ```javascript
   const API_BASE_URL = process.env.VITE_API_URL || 'https://your-backend-service.onrender.com/api';
   ```

2. Add environment variable in Render:
   - `VITE_API_URL=https://your-backend-service.onrender.com/api`

### Troubleshooting

#### Build Fails
- Check the build logs in Render dashboard
- Verify Node.js version (should be 18+)
- Ensure all dependencies are in package.json

#### Backend Won't Start
- Check Java version (should be 17)
- Verify environment variables are set
- Check database connection string
- Review application logs in Render dashboard

#### CORS Errors
- Update `CORS_ALLOWED_ORIGINS` in backend environment variables
- Include your frontend URL (e.g., `https://zidioconnect-frontend.onrender.com`)

#### Database Connection Issues
- Verify database credentials
- Check if database is accessible from Render
- Ensure database is in the same region as your backend service

### Custom Domains

1. In Render dashboard, go to your service
2. Click "Settings" → "Custom Domains"
3. Add your custom domain
4. Update DNS records as instructed by Render

### Monitoring

- Check service logs in Render dashboard
- Set up health checks
- Monitor service uptime
- Set up alerts for service failures

## Support

For Render-specific issues, check:
- [Render Documentation](https://render.com/docs)
- [Render Community](https://community.render.com)

For project-specific issues, check:
- `backend/README.md`
- `backend/ENVIRONMENT_SETUP_GUIDE.md`
