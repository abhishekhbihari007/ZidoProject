@echo off
echo ========================================
echo ZIDIOConnect Backend - Environment Loader
echo ========================================

echo.
echo Available environments:
echo 1. Development (env.development)
echo 2. Production (env.production)
echo 3. Custom (.env file)
echo.

set /p choice="Select environment (1-3): "

if "%choice%"=="1" (
    echo Loading development environment...
    set ENV_FILE=env.development
) else if "%choice%"=="2" (
    echo Loading production environment...
    set ENV_FILE=env.production
) else if "%choice%"=="3" (
    echo Loading custom environment...
    set ENV_FILE=.env
) else (
    echo Invalid choice. Using development environment.
    set ENV_FILE=env.development
)

echo.
echo Loading environment variables from %ENV_FILE%...

if not exist "%ENV_FILE%" (
    echo ❌ Environment file %ENV_FILE% not found!
    echo Please create the environment file first.
    pause
    exit /b 1
)

echo ✅ Environment file found: %ENV_FILE%
echo.
echo Setting environment variables...

for /f "usebackq tokens=1,2 delims==" %%a in ("%ENV_FILE%") do (
    if not "%%a"=="" if not "%%a:~0,1%"=="#" (
        set "%%a=%%b"
        echo Set %%a=%%b
    )
)

echo.
echo ========================================
echo Environment variables loaded successfully!
echo ========================================
echo.
echo Starting Spring Boot application...
echo.

mvn spring-boot:run -Dspring-boot.run.profiles=env

pause
