package com.netcore.qa.util;

import java.io.IOException;

/*************************************** PURPOSE **********************************
- This class implements the WebDriverEventListener, which is included under events.
The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
which would be displayed/logged as the application under test is being run.
Do not call any of these methods, instead these methods will be invoked automatically
as an when the action done (click, findBy etc). 
*/

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGListener;

import com.netcore.qa.base.TestBase;




/**
 * The listener interface for receiving webEvent events.
 * The class that is interested in processing a webEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addWebEventListener<code> method. When
 * the webEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see WebEventEvent
 */
public class WebEventListener extends TestBase implements WebDriverEventListener,ITestNGListener
 {

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#beforeNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
			*/
		
	public void beforeNavigateTo(String url, WebDriver d) {
		log.info("Before navigating to: '" + url + "'");
		log.info("Before navigating to: '" + url + "'");
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#afterNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
			*/
		
	public void afterNavigateTo(String url, WebDriver d) {
		log.info("Navigated to:'" + url + "'");
		log.info("Navigated to:'" + url + "'");
	}

	/**
	 * Before change value of.
	 *
	 * @param element the element
	 * @param driver the driver
	 */
	public void beforeChangeValueOf(WebElement element) {
		log.info("Value of the:" + element.toString() + " before any changes made");
		log.info("Value of the:" + element.toString() + " before any changes made");
	}

	/**
	 * After change value of.
	 *
	 * @param element the element
	 * @param driver the driver
	 */
	public void afterChangeValueOf(WebElement element) {
		log.info("Element value changed to: " + element.toString());
		log.info("Element value changed to: " + element.toString());
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#beforeClickOn(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
			*/
		
	public void beforeClickOn(WebElement element, WebDriver d) {
		log.info("Trying to click on: " + element.toString());
		log.info("Trying to click on: " + element.toString());
	}

	
	public static void clickOn(WebDriver d, WebElement locator, int timeout) {
		new WebDriverWait(d, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	
	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#afterClickOn(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
			*/
		
	public void afterClickOn(WebElement element, WebDriver d) {
		log.info("Clicked on: " + element.toString());
		log.info("Clicked on: " + element.toString());
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#beforeNavigateBack(org.openqa.selenium.WebDriver)
			*/
		
	public void beforeNavigateBack(WebDriver d) {
		log.info("Navigating back to previous page");
		log.info("Navigating back to previous page");
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#afterNavigateBack(org.openqa.selenium.WebDriver)
			*/
		
	public void afterNavigateBack(WebDriver d) {
		log.info("Navigated back to previous page");
		log.info("Navigated back to previous page");
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#beforeNavigateForward(org.openqa.selenium.WebDriver)
			*/
		
	public void beforeNavigateForward(WebDriver d) {
		log.info("Navigating forward to next page");
		log.info("Navigating forward to next page");
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#afterNavigateForward(org.openqa.selenium.WebDriver)
			*/
		
	public void afterNavigateForward(WebDriver d) {
		log.info("Navigated forward to next page");
		log.info("Navigated forward to next page");
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#onException(java.lang.Throwable, org.openqa.selenium.WebDriver)
			*/
		
	public void onException(Throwable error, WebDriver d) {
		try {
			log.info("Taking Screenshot");
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {

log.error(e);
}
		log.info("Exception occured: " + error);
		log.info("Exception occured: " + error);
		
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#beforeFindBy(org.openqa.selenium.By, org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
			*/
		
	public void beforeFindBy(By by, WebElement element, WebDriver d) {
		log.info("Trying to find Element By : " + by.toString());
	}

	/* (non-Javadoc)
			* @see org.openqa.selenium.support.events.WebDriverEventListener#afterFindBy(org.openqa.selenium.By, org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
			*/
		
	public void afterFindBy(By by, WebElement element, WebDriver d) {
		log.info("Found Element By : " + by.toString());
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {

		
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {

		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {

		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {

		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {

		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {

		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {

		
	}

	@Override
	public void afterScript(String script, WebDriver driver) {

		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {

		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {

		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {

		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {

		
	}
	public boolean retryingFindClick(By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 4) {
	        try {
	            d.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	

	}