package com.netcore.qa.ExtentReportListner;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class ExtentReportListenerNG.
 */
public class ExtentReportListenerNG implements IReporter {

	/** The extent. */
ExtentReports extent;
static ExtentTest test;
static String defaultpath = System.getProperty("user.dir");

	
//	public static String getScreenshot(WebDriver d) throws IOException {
//		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		TakesScreenshot ts = (TakesScreenshot) d;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
//		String destination = defaultpath +dateName+".png";
//		File finalDestination = new File(destination);
//		FileUtils.copyFile(source, finalDestination);
//		return destination;
//	}
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(defaultpath+"//Netcore_Automation_Test_Report.html",true);
		extent.loadConfig(new File(defaultpath+"//extent-config.xml"));
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
		
			
			
			
			}
			
			

		}
		extent.flush();
		extent.close();
	}

	/**
	 * Builds the test nodes.
	 *
	 * @param tests the tests
	 * @param status the status
	 */
	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test2;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test2 = extent.startTest(result.getMethod().getMethodName());

				test2.setStartedTime(getTime(result.getStartMillis()));
				test2.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test2.assignCategory(group);

				if (result.getThrowable() != null) {
					test2.log(status, result.getThrowable());
				} else {
					test2.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				extent.endTest(test2);
			}
		}
	}

	/**
	 * Gets the time.
	 *
	 * @param millis the millis
	 * @return the time
	 */
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
