# Ebay Demo task
***
This is an automation script for test scenarios in ebay website

## Framework
***
I am using Page object model as a design pattern and cucumber.

## Technologies
***
A list of technologies used within the project:
* [Java]: Version 11.0.15.1
* [Maven]: Version 3.8.4
* [Selenium]: Version 4.1.0
* [webdrivermanager]: Version 5.0.3
* [TestNG]: Version 7.4.0
* [Cucumber]: Version 6.9.1
* [Cucumber Extent report]: Version 2.11.1
* [WireMock]: Version 2.33.2
* [RestAssured]: Version 5.1.0
* [Chrome]: Version 102.0.5005.61

## Installation
***
Download maven
* Create enviroment variable with "MAVEN_HOME" and put path of maven like "D:\Maven\apache-maven-3.8.4\bin"
* Put MAVEN_HOME in Path variable like "%MAVEN_HOME%"

## Execution
***
* Open Command line 
* cd "Path of the project "
* Type Command (mvn test -Dtestng=testng) to run ebay cases
* Type Command (mvn test -Dtestng=testngAPI) to run api cases
* Click enter and execution will be done
* check result 
* API Report path is "Project path\reports\APIReport.html"
* Ebay Report path is "Project path\reports\htmlReports+date\SparkReport\Spark.html"
* Screenshot path is "Project path\screenshots"

## Dynamic Testng file
* Execute using command : mvn test -Dtestng=File of testng you want to run




