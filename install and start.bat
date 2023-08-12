@echo off

cd web

call npm install

echo Installation complete. Press any key to continue...
pause > nul

echo Starting server
call npm start 

exit
