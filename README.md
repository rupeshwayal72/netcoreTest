# NetcoreTest
Test Assignment

Tools
--
Eclipse Java EE(oxygen recommended)
Java 7+
WebDriver 3.14
Apache Maven
TestNG

Framework:
---
Hybrid Framework in Selenium is a concept where we are using the advantage of both Keyword driven framework 
as well as Data driven framework. It is an easy to use framework which allows manual testers to create test cases by
just looking at the keywords, test data and object repository without coding in the framework.
Hybrid Test Automation Framework which can be used across different web based applications.
In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly used for
another web application without spending any extra effort. With this framework in place, whenever we need to automate a web based application,
we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application
specific components for the specific needs.


Installation & Execution Steps
---
For step by step Selenium setup,Please go through
https://www.guru99.com/installing-selenium-webdriver.html

For step by step TestNG setup,Please go through
https://www.toolsqa.com/testng/install-testng/#:~:text=The%20following%20installation%20process%20uses,click%20the%20%E2%80%9CAdd%E2%80%9D%20button.

For importing project from git follow below steps:
1. Open eclipse and click on file(top left corner menu)
2. Click on import and search for git
3. Select project from git and click on next button
4. Click on clone URI and click on next
5. Now open this git repo in browser and click on code and copy it's https url
6. Now paste it into eclipse URI field and enter your git credentials.
7. click on finish.

8. Now after importing project into eclipse, Right click on project and click on build path-->configure build path
and click on add liabrary(liabraries tab) add jre system liabrary,maven dependencies,testng(only if any one of them is missing).

9. We used Apache maven for dependency management.
Just right click on the project and
go to maven-->download sources,
Then maven-->update project that's it.
 
10. After Successful TestNG insatllation,Right click on TestNG.xml file of this project and select execute as TestNG suite option.
For HTML Test report,check  Netcore_Automation_Test_Report.html
For Test Failure screenshots,check  screenshots folder.
For Overall Test logs,check logs folder-->main.log.


Assignment Details:
---
Please Note: Original java code for all assignments are inside src-->main-->java folder
Where
-
1. Package com.netcore.qa.base-->Testbase.java contains code for all execution and environment related operations

2. Package com.netcore.qa.config-->config.properties contains environment related  data.

3. Package com.netcore.qa.ExtenetReportLisner-->ExtenetReportLisnerNG.java contains code for HTML report generation of executed test.

4. Package com.netcore.qa.pages-->SearchPage.java contains actual logic/code for Above Assignment.

5. Package com.netcore.qa.testdata-->test.csv is a file where all retrieved data is stored via test script in ascending order.

6. Package com.netcore.qa.util-->TestUtil.java contains Generic logic/code used accross project for perfroming some utilities like capture screenshots on failure..

7. Package com.netcore.qa.util-->log.java contains code for logs generation.

8. Package com.netcore.qa.util-->WebEventListner.java contains generic code for logs generation on Particular Web Events.

9. extent-config.xml file contains xml code for skin generation for extentReport.

 10. log4j.properties contains code for logs configuration

 11. POM.xml is used for maven dependency management.


Please Note: Original Tescases for all assignments are inside src-->test-->java folder
-

 12. Package com.netcore.qa.testcases contains all executable testNG testcases.




