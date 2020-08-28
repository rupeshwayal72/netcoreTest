package com.netcore.qa.testcases;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import com.netcore.qa.pages.*;
import com.netcore.qa.util.TestUtil;

public class searchPageTest extends TestUtil {

	SearchPage searchPage;

	public searchPageTest() {
		super();// Calls the TestBase class
		log.info("************************** Netcore Flipkart search Page TEST  *****************************");
	}

	@BeforeTest
	public void setUp() throws MalformedURLException, InterruptedException {
		try {
			log.info("Initalizing the flipkart search page with all fields");
			intialization();
			searchPage = new SearchPage(d);

		} catch (NullPointerException e) {
			log.error("Unable to load searchPage" + e);

		}
	}

	@Test
	public void searchProductTest() throws InterruptedException, IOException {
		try {
			String filePath = System.getProperty("user.dir") + "/src/main/java/com/netcore/qa/testdata/test.csv";
			log.info("Close Login Window Test Scenario");
			searchPage.loginWindow();

			log.info("Search Iphone Test Scenario");
			searchPage.searchProduct("Iphone");

			log.info("setting price filter test");
			searchPage.setPriceFilter("Min", "50000");

			log.info("setting price category Low To High Test");
			searchPage.setPriceCategory();

			log.info("Executing Verify netcore menu options Test Scenario");
			searchPage.getProductDetails(filePath, 40000);

		} catch (Exception e) {
			log.error("search Product Test Failed");

		}
	}

	@AfterClass
	public void Quit_Browser_Test() {
		try {

			log.info("Closing the current browser session");
			d.quit();
			log.info("******************END**********************");
			log.info("browser closed");
		} catch (Exception e) {
			log.error("browser not closed");
		}
	}
}
