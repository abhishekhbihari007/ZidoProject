@echo off
echo ========================================
echo ZidioConnect - Maven Setup Script
echo ========================================
echo.

REM Create Maven directory
if not exist "C:\maven" mkdir C:\maven
cd C:\maven

echo Downloading Maven 3.9.6...
echo This may take a few minutes...
echo.

REM Download Maven using PowerShell
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip' -OutFile 'maven.zip'}"

if exist "maven.zip" (
    echo Maven downloaded successfully!
    echo.
    echo Extracting Maven...
    
    REM Extract Maven
    powershell -Command "Expand-Archive -Path 'maven.zip' -DestinationPath '.' -Force"
    
    REM Clean up
    del maven.zip
    
    echo.
    echo Maven extracted to C:\maven\apache-maven-3.9.6
    echo.
    echo ========================================
    echo Setting up Environment Variables
    echo ========================================
    echo.
    
    REM Set MAVEN_HOME
    setx MAVEN_HOME "C:\maven\apache-maven-3.9.6" /M
    
    REM Add Maven to PATH
    for /f "tokens=2*" %%A in ('reg query "HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment" /v PATH') do set "CURRENT_PATH=%%B"
    setx PATH "%CURRENT_PATH%;C:\maven\apache-maven-3.9.6\bin" /M
    
    echo Environment variables set successfully!
    echo.
    echo ========================================
    echo IMPORTANT: Restart Required
    echo ========================================
    echo.
    echo Please restart your Command Prompt or computer
    echo for the environment variables to take effect.
    echo.
    echo After restart, you can run:
    echo   mvn -version
    echo   mvn spring-boot:run
    echo.
    
) else (
    echo Failed to download Maven.
    echo Please check your internet connection and try again.
    echo.
    echo Manual download:
    echo 1. Go to: https://maven.apache.org/download.cgi
    echo 2. Download: apache-maven-3.9.6-bin.zip
    echo 3. Extract to: C:\maven\
    echo 4. Add C:\maven\apache-maven-3.9.6\bin to PATH
)

echo.
pause
