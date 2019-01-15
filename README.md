Approach-1 Import project via Intellij.


To run via Chrome
a) Download the chome driver from location : ( click on latest Browser driver)
https://www.seleniumhq.org/download/
b)Place the exe at in location :"C:\SeleniumWebdrivers\chromedriver.exe
c)Relevant test reports are generated in all formats in folder : target/testReports


To run via Firefox
a) Download the gecko driver from location : ( click on latest Browser driver)
https://www.seleniumhq.org/download/
b)Place the exe at in location :"C:\SeleniumWebdrivers\geckodriver.exe
c) Uncomment the line driver = new FirefoxDriver() in CucumberHooks,java
d)Relevant test reports are generated in all formats in folder : target/testReports


Run SGGamingRunnerTest.java

Approach-1 To run via maven:
a) unzip the project
b)Perform Steps To run via Chrome or To run via Firefox
c) Run mvn clean test from command prompt


Some defects were found in application and it is sent in excel.




