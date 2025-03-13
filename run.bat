@echo off

REM Set console code page to UTF-8
chcp 65001 >nul

REM Ensure the font supports emojis (optional, depends on your console settings)
REM You may need to manually set the font to a font that supports emojis, such as "Consolas" or "Segoe UI Emoji"

where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo [1;91mMaven is not installed[00;0m
    exit /b
)

if not exist "target" (
    mvn install
)

for /f "tokens=2 delims=: " %%a in ('mode con ^| findstr "Lines"') do set LINES=%%a
for /f "tokens=2 delims=: " %%a in ('mode con ^| findstr "Columns"') do set COLUMNS=%%a

cls
mvn exec:java -q
