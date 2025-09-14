@echo off
echo ========================================
echo ZIDIOConnect Backend Build Check
echo ========================================

cd /d "C:\Users\bihar\Desktop\zido\ZidioConnect\backend"

echo.
echo 1. Checking current build status...
if exist "target\careerbridgepro-backend-1.0.0.jar" (
    echo ✅ JAR file exists: careerbridgepro-backend-1.0.0.jar
    dir target\*.jar
) else (
    echo ❌ JAR file not found
    echo.
    echo 2. Building backend...
    echo Running: mvn clean package -DskipTests
    mvn clean package -DskipTests
    echo.
    if exist "target\careerbridgepro-backend-1.0.0.jar" (
        echo ✅ Build successful! JAR file created.
        dir target\*.jar
    ) else (
        echo ❌ Build failed! Check for errors above.
    )
)

echo.
echo 3. Checking compiled classes...
if exist "target\classes\com\careerbridgepro" (
    echo ✅ Compiled classes exist
    echo Found classes in: target\classes\com\careerbridgepro\
) else (
    echo ❌ No compiled classes found
)

echo.
echo 4. Backend Configuration:
echo - Port: 8080
echo - Context Path: /api
echo - Database: H2 (in-memory)
echo - JWT: Configured
echo.
echo ========================================
echo Backend Build Check Complete
echo ========================================
pause
