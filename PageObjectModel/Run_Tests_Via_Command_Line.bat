REM This is windows batch execution file, writing MS-DOS commands 
cd\
cls
cd "C:\Users\ThinkPad_T540p\git\Testing\PageObjectModel"
REM mvn test -Dtest=Tests.MortgageCalculatorTest
mvn test -Dtest=Tests.MortgageCalculatorDataDrivenTest
pause