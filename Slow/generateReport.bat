java -jar F:\Java\CucumberReport3.7.0\3.7.0\cucumber-sandwich-3.7.0-jar-with-dependencies.jar -n -f Report/ -o Report/
cd Report

for /F "tokens=2" %%i in ('date /t') do set mydate=%%i
set mytime=%time%

set mydate1=%mydate:/=% 
set mytime1=%mytime:.=% 
set mytime1=%mytime::=% 
set datetime=%mydate1%%mytime1%
set datetime1=%datetime: =%

rename cucumber-html-reports cucumber-html-report_%date:~-4,4%%date:~-10,2%%date:~-7,2%_%hr%%time:~3,2%%time:~6,2%
exit