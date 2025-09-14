@echo off
echo ========================================
echo Downloading and Installing Maven
echo ========================================
echo.

REM Create Maven directory
if not exist "C:\maven" mkdir C:\maven

echo Step 1: Downloading Maven 3.9.6...
echo This may take a few minutes depending on your internet speed...
echo.

REM Download Maven
curl -L -o "C:\maven\maven.zip" "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"

if exist "C:\maven\maven.zip" (
    echo.
    echo Step 2: Download completed successfully!
    echo.
    echo Step 3: Extracting Maven...
    
    REM Extract Maven
    powershell -Command "Expand-Archive -Path 'C:\maven\maven.zip' -DestinationPath 'C:\maven\' -Force"
    
    REM Clean up
    del "C:\maven\maven.zip"
    
    echo.
    echo Step 4: Maven extracted successfully!
    echo Location: C:\maven\apache-maven-3.9.6
    echo.
    echo Step 5: Setting up environment variables...
    
    REM Set MAVEN_HOME
    setx MAVEN_HOME "C:\maven\apache-maven-3.9.6" /M
    
    REM Add Maven to PATH
    for /f "tokens=2*" %%A in ('reg query "HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment" /v PATH') do set "CURRENT_PATH=%%B"
    setx PATH "%CURRENT_PATH%;C:\maven\apache-maven-3.9.6\bin" /M
    
    echo.
    echo ========================================
    echo ✅ Maven Installation Complete!
    echo ========================================
    echo.
    echo Maven has been installed to: C:\maven\apache-maven-3.9.6
    echo Environment variables have been set.
    echo.
    echo ⚠️  IMPORTANT: You need to restart your Command Prompt
    echo    for the environment variables to take effect.
    echo.
    echo After restart, you can run:
    echo   mvn -version
    echo   mvn spring-boot:run
    echo.
    echo ========================================
    echo 🚀 Ready to run ZidioConnect!
    echo ========================================
    echo.
    
) else (
    echo.
    echo ❌ Download failed!
    echo.
    echo Please try one of these alternatives:
    echo.
    echo 1. Manual Download:
    echo    - Go to: https://maven.apache.org/download.cgi
    echo    - Download: apache-maven-3.9.6-bin.zip
    echo    - Extract to: C:\maven\
    echo    - Add C:\maven\apache-maven-3.9.6\bin to PATH
    echo.
    echo 2. Use Chocolatey (if installed):
    echo    choco install maven
    echo.
    echo 3. Use an IDE:
    echo    - IntelliJ IDEA
    echo    - Eclipse
    echo    - VS Code with Java Extension Pack
    echo.
)

echo.
pause
