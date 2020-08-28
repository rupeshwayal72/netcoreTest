package com.netcore.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.netcore.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * The Class TestBase.
 *
 * @author RWayal
 */
public class TestBase {

	/** The driver. */
	protected static WebDriver d;

	/** The prop. */
	protected static final Properties prop = new Properties();

	/** The db prop. */
	protected static final Properties dbprop = new Properties();
	/** The log. */
	protected static final Logger log = Logger.getLogger(TestBase.class);

	/** The e driver. */
	protected static EventFiringWebDriver e_driver;

	/** The event listener. */
	protected static WebEventListener eventListener;

	String defaultpath = System.getProperty("user.dir");

	/**
	 * Instantiates a new test base.
	 */
	public TestBase() {
		PropertyConfigurator.configure("log4j.properties");

		log.info("****************************** NetCore Test  *****************************************");
		try {
			log.info("Nimbus:Loading the config.properties file");
			FileInputStream ip = new FileInputStream(

					defaultpath + "/src/main/java/com/netcore/qa/config/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Nimbus: Exception: Error in loading the config.properties file" + e);
		} catch (IOException f) {
			log.error("Nimbus: Exception: Error in loading the config.properties file" + f);

		}

	}

	/**
	 * Intialization.
	 * 
	 * @return
	 *
	 * @throws MalformedURLException the malformed URL exception
	 */
	public void intialization() throws MalformedURLException {
		PropertyConfigurator.configure("log4j.properties");

		log.info(
				"****************************** Starting the intialization for Test Base Class  *****************************************");
		String browserName = prop.getProperty("browser");
		log.info("Running the Tests on the browser" + browserName);
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");

			log.info("launching chrome broswer and setting up the capabilities");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.WINDOWS);

			// Chrome Options
			ChromeOptions options = new ChromeOptions();
			options.merge(capabilities);
			if ("true".equals(prop.getProperty("grid"))) {
				log.info("GRID URL" + prop.getProperty("gridurl"));
				d = new RemoteWebDriver(new URL(prop.getProperty("gridurl")), options);
				System.setProperty("webdriver.chrome.silentOutput", "true");
				d.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

				d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			} else {
				WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.silentOutput", "true");

				d = new ChromeDriver();
				log.info("URL" + prop.getProperty("url"));
				log.info("chrome  found");
				d.get(prop.getProperty("url"));
				d.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

				d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}

		}

		else if (browserName.equals("FF")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.WINDOWS);
			WebDriverManager.firefoxdriver().setup();
			log.info("Nimbus:launching firefox broswer");
			if ("true".equals(prop.getProperty("grid"))) {
				d = new RemoteWebDriver(new URL(prop.getProperty("gridurl")), capabilities);
				d.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

				d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			} else { // Running on Local browser

				d = new FirefoxDriver();
				d.get(prop.getProperty("url"));
				System.setProperty("webdriver.chrome.silentOutput", "true");

				d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				d.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			}

		}

		else if (browserName.equals("ie")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("IE");
			capabilities.setPlatform(Platform.WINDOWS);
			WebDriverManager.iedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");

			log.info("Nimbus:launching firefox broswer");
			if ("true".equals(prop.getProperty("grid"))) {
				d = new RemoteWebDriver(new URL(prop.getProperty("gridurl")), capabilities);

			} else { // Running on Local browser

				d = new InternetExplorerDriver();
				d.get(prop.getProperty("url"));

			}
			d.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
			d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		}

		log.info(prop.getProperty("url"));
		// EventFiringWebdriver
		try {
			e_driver = new EventFiringWebDriver(d);
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			d = e_driver;
			log.info("Nimbus: Maximizing window, clearing cookies and setting up timeouts");

		} catch (NullPointerException e) {
			log.info("Nimbus failed to: Maximizing window, clearing cookies and setting up timeouts");

		}
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		d.get(prop.getProperty("url"));
		log.info("Nimbus: entering application URL");

	}

	/**
	 * Gets the page title.
	 * 
	 * @return the page title
	 */
	public String getPageTitle() {

		log.info("Nimbus: getPageTitle " + d.getCurrentUrl());
		return d.getCurrentUrl();
	}

}
