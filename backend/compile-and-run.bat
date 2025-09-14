@echo off
echo ========================================
echo ZidioConnect Backend - Direct Compilation
echo ========================================
echo.

REM Check if Java is available
java -version >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher from: https://adoptium.net/
    pause
    exit /b 1
)

echo Java is available. Checking version...
java -version
echo.

REM Create directories
echo Creating directories...
if not exist "target\classes" mkdir target\classes
if not exist "target\lib" mkdir target\lib

echo.
echo ========================================
echo IMPORTANT: Manual Setup Required
echo ========================================
echo.
echo Since Maven is not installed, you need to manually:
echo.
echo 1. Download Maven from: https://maven.apache.org/download.cgi
echo 2. Extract it to a folder (e.g., C:\apache-maven-3.9.6)
echo 3. Add Maven bin directory to your PATH environment variable
echo 4. Restart Command Prompt
echo 5. Run: mvn spring-boot:run
echo.
echo OR
echo.
echo 1. Install Maven using Chocolatey: choco install maven
echo 2. Restart Command Prompt
echo 3. Run: mvn spring-boot:run
echo.
echo ========================================
echo Alternative: Use an IDE
echo ========================================
echo.
echo You can also run this project using:
echo - IntelliJ IDEA (with Maven plugin)
echo - Eclipse (with Maven plugin)
echo - VS Code (with Java Extension Pack)
echo.
echo Just open the backend folder as a Maven project.
echo.

pause
