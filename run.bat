@echo off

where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo [1;91mMaven is not installed[00;0m
    exit /b
)

for /f "tokens=2 delims=: " %%a in ('mode con ^| findstr "Lines"') do set LINES=%%a
for /f "tokens=2 delims=: " %%a in ('mode con ^| findstr "Columns"') do set COLUMNS=%%a

cls
mvn exec:java -q
