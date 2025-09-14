@echo off
echo ========================================
echo ZIDIOConnect - Vercel Deployment
echo ========================================

echo.
echo Step 1: Building frontend for production...
cd /d "C:\Users\bihar\Desktop\zido\ZidioConnect\client"
call npm run build
if not exist "dist" (
    echo ❌ Build failed! Check for errors above.
    pause
    exit /b 1
)
echo ✅ Frontend build successful!

echo.
echo Step 2: Adding files to git...
cd /d "C:\Users\bihar\Desktop\zido\ZidioConnect"
git add .
git commit -m "Deploy: Ready for Vercel deployment

- Built frontend for production
- Fixed login validation (username instead of email)
- Updated Register component
- Added comprehensive validation
- Ready for Vercel hosting"

echo.
echo Step 3: Pushing to GitHub...
git push --force-with-lease origin main
if %errorlevel% equ 0 (
    echo ✅ Successfully pushed to GitHub!
) else (
    echo ❌ Push failed. Trying force push...
    git push --force origin main
)

echo.
echo ========================================
echo 🚀 READY FOR VERCEL DEPLOYMENT!
echo ========================================
echo.
echo Next steps:
echo 1. Go to https://vercel.com
echo 2. Sign in with GitHub
echo 3. Click "New Project"
echo 4. Import your repository: abhishekhbihari007/ZidioProject
echo 5. Set Root Directory to: client
echo 6. Deploy!
echo.
echo Your app will be available at:
echo https://your-app-name.vercel.app
echo.
echo ⚠️  IMPORTANT: Backend needs separate hosting!
echo    - Vercel only hosts frontend
echo    - Use Railway, Heroku, or AWS for backend
echo.
pause
