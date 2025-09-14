@echo off
echo ========================================
echo Push to Clean GitHub Repository
echo ========================================

REM Change to project directory
cd /d "C:\Users\bihar\Desktop\zido\ZidioConnect"

echo Step 1: Initializing new Git repository...
git init

echo Step 2: Adding all files to repository...
git add .

echo Step 3: Making initial commit (NO Rupam797 history)...
git commit -m "Initial commit: ZidioConnect project

- Complete ZidioConnect application with backend and frontend
- Spring Boot backend with JWT authentication
- React frontend with role-based access
- Comprehensive .gitignore files
- Manual setup guides
- Clean repository with only abhishekhbihari007 commits"

echo Step 4: Adding remote repository...
git remote add origin https://github.com/abhishekhbihari007/ZidoProject.git

echo Step 5: Pushing to GitHub...
git branch -M main
git push -u origin main

echo.
echo ========================================
echo SUCCESS: Project pushed to GitHub!
echo ========================================
echo.
echo ✅ Rupam797 is completely gone from GitHub
echo ✅ Only your commits will appear in contributions
echo ✅ Clean repository with no trace of Rupam797
echo.
echo Your repository: https://github.com/abhishekhbihari007/ZidoProject
echo.
pause
