@echo off
echo Starting ZidioConnect Backend...
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo Maven is not installed. Please install Maven first.
    echo.
    echo You can download Maven from: https://maven.apache.org/download.cgi
    echo Or install it using a package manager like Chocolatey:
    echo   choco install maven
    echo.
    pause
    exit /b 1
)

REM Check if Java is installed
java -version >nul 2>nul
if %errorlevel% neq 0 (
    echo Java is not installed. Please install Java 17 or higher.
    echo.
    echo You can download Java from: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo Maven and Java are available. Starting the application...
echo.

REM Clean and compile
echo Cleaning and compiling...
call mvn clean compile
if %errorlevel% neq 0 (
    echo Compilation failed. Please check the errors above.
    pause
    exit /b 1
)

echo.
echo Starting Spring Boot application...
echo The application will be available at: http://localhost:8080/api
echo.
echo Press Ctrl+C to stop the application.
echo.

REM Run the application
call mvn spring-boot:run

pause
