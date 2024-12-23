@echo off
rem ==========================
rem Java Project Build Script (Root Directory Setup)
rem ==========================

echo Cleaning old build files...
echo  Clean previous build directories if they exist
if exist bin rmdir /s /q bin
if exist dist rmdir /s /q dist
if exist output rmdir /s /q output

rem Create necessary directories
mkdir bin
mkdir dist
mkdir output

echo Compiling Java source files...
echo 1. Compile all .java files in the root directory into .class files
javac -d bin *.java
if %ERRORLEVEL% neq 0 (
    echo Compilation failed. Please check your source files for errors.
    pause
    exit /b %ERRORLEVEL%
)

echo Creating JAR file...
echo 2. Create the JAR file with the main class specified
jar --create --file dist\Game2048.jar --main-class Game2048 -C bin .
if %ERRORLEVEL% neq 0 (
    echo Failed to create the JAR file. Exiting.
    pause
    exit /b %ERRORLEVEL%
)

echo Packaging to EXE using jpackage...
echo 3. Use jpackage to create the executable file
jpackage ^
    --type exe ^
    --name Game2048 ^
    --input dist ^
    --main-jar Game2048.jar ^
    --main-class Game2048 ^
    --win-console
if %ERRORLEVEL% neq 0 (
    echo Failed to create the EXE. Exiting.
    pause
    exit /b %ERRORLEVEL%
)

echo Build successful! EXE is located in the 'output' directory.
pause