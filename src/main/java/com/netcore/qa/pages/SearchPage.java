package com.netcore.qa.pages;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.netcore.qa.base.TestBase;
import com.opencsv.CSVWriter;

public class SearchPage extends TestBase {
	WebDriverWait wait = new WebDriverWait(d, 30);
	Actions actions = new Actions(d);
	Keyboard keyboard = ((HasInputDevices) d).getKeyboard();
	JavascriptExecutor executor = (JavascriptExecutor) d;

	// login window close button
	@CacheLookup
	@FindBy(xpath = "//*[@class='_2AkmmA _29YdH8']")
	WebElement closeLoginWindow;

	// Search textBox
	@CacheLookup
	@FindBy(xpath = "//*[@title='Search for products, brands and more']")
	WebElement searchBox;

	// Search button
	@CacheLookup
	@FindBy(xpath = "//*[@type='submit']")
	WebElement searchButton;

	// Select min-max price range filter
	@CacheLookup
	@FindBy(xpath = "//*[@class='fPjUPw']")
	List<WebElement> priceFilter;

	// Select price category low to high sorting filter
	@CacheLookup
	@FindBy(xpath = "//*[@class='_1xHtJz']")
	List<WebElement> priceCategory;

	// Displayed product names in search result
	@CacheLookup
	@FindBy(xpath = "//*[@class='_3wU53n']")
	List<WebElement> productNames;

	// Displayed product Price in search result
	@CacheLookup
	@FindBy(xpath = "//*[@class='_1vC4OE _2rQ-NK']")
	List<WebElement> productPrice;

	// Displayed product ratings in search result
	@CacheLookup
	@FindBy(xpath = "//*[@class='_38sUEc']")
	List<WebElement> productRatings;

	// Storing all product details in Arraylist
	ArrayList<String> productDetails = new ArrayList<>();

	public SearchPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public void loginWindow() {
		try {
			log.info("Closing login window");
			wait.until(ExpectedConditions.elementToBeClickable(closeLoginWindow));
			closeLoginWindow.click();
		} catch (Exception e) {
			Assert.fail("Close Login Window Test Failed!!!!");
		}
	}

	public void searchProduct(String searchData) {
		try {
			log.info("Enter Product name in search field");
			wait.until(ExpectedConditions.elementToBeClickable(searchBox));
			searchBox.sendKeys(searchData);

			log.info("Clicking on search Button");
			wait.until(ExpectedConditions.elementToBeClickable(searchButton));
			searchButton.click();
		} catch (Exception e) {
			Assert.fail("Search Product Test Failed!!!!");
		}
	}

	public void setPriceFilter(String min, String max) {
		try {
			log.info("Setting minimum price filter");
			wait.until(ExpectedConditions.elementToBeClickable(priceFilter.get(0)));
			Select minPriceFilter = new Select(priceFilter.get(0));
			log.info("Setting Minimum price filter: " + min);
			minPriceFilter.selectByValue(min);

			log.info("Setting Maximum price filter");
			wait.until(ExpectedConditions.elementToBeClickable(priceFilter.get(1)));
			Select maxPriceFilter = new Select(priceFilter.get(1));
			log.info("Setting Maximum price filter: " + max);
			maxPriceFilter.selectByValue(max);

		} catch (Exception e) {
			Assert.fail("Setting Min-Max Price Filter Test Failed");
		}
	}

	public void setPriceCategory() {
		try {
			log.info("Setting Price Category As Low To High");
			wait.until(ExpectedConditions.elementToBeClickable(priceCategory.get(1)));
			log.info("Clicking On Low To High Price Category Option");
			priceCategory.get(1).click();

			// adding wait till loading
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

		} catch (Exception e) {
			Assert.fail("Setting Price Category As Low To High Test Failed");
		}
	}

	public void getProductDetails(String filePath, int budget) {
		try {

			CSVWriter writer = new CSVWriter(new FileWriter(filePath));

			// Create CSV Headers
			String[] headers = "Device Details, Price, Ratings".split(",");

			// Writing CSV file headers.
			writer.writeNext(headers);

			log.info("Retrieving Product Details:");

			for (int i = 0; i <= productNames.size(); i++) {

				// Fetching actual price from UI without ₹ symbol.
				String actualPrice = productPrice.get(i).getText().split("₹")[1];

				// removing , Regex from fetched price for price comparison purpose
				String result = actualPrice.replaceAll(",", "");

				// Fetching product price and converting into integer for price comparison
				if (Integer.parseInt(result) <= budget) {

					// Fetching Product Names
					wait.until(ExpectedConditions.visibilityOf(productNames.get(i)));
					String nameDetails = productNames.get(i).getText();
					log.info("Product Name Is: " + nameDetails);
					productDetails.add(nameDetails);

					// Fetching Product Price
					wait.until(ExpectedConditions.visibilityOf(productPrice.get(i)));
					String price = productPrice.get(i).getText();
					log.info("product price is: " + price);
					productDetails.add(price);

					// Fetching Product Ratings
					wait.until(ExpectedConditions.visibilityOf(productRatings.get(i)));
					String ratingsData = productRatings.get(i).getText().split(" Ratings &")[0];
					log.info("Product Ratings: " + ratingsData);
					productDetails.add(ratingsData);

					// Writing data into the csv file
					String[] product = new String[productDetails.size()];
					product = productDetails.toArray(product);
					writer.writeNext(product);

					// Clearing array list
					productDetails.clear();

					// adding wait till loading
					executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");

				}

				else {
					log.info("product price is more than 40000 INR < " + result);
					break;

				}
			}

			// Closing CSVWriter
			writer.close();

		} catch (Exception e) {
			Assert.fail("Retrieving and sorting Product Details in CSV Test Failed");
		}

	}

}