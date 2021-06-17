package mortgageCalculatorPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilityLibraries.Base;

public class HomePage extends Base {
	
	// copy from the base library
	final static Logger logger = Logger.getLogger(HomePage.class);

	/***
	 * Navigate to Mortgage calculator HomePage
	 * @return
	 */
	public HomePage gotoMortgageCalculatorWesite() {
		try {
			driver.get("http://www.mortgagecalculator.net/");
			String actualWebsiteTitle = driver.getTitle();
			String expctedTitle = "Mortgage Calculator 2021 - FREE Calculator Tool (ZERO Ads)";
			assertEquals(actualWebsiteTitle, expctedTitle);
			
			// Find all links
			List<WebElement> links = driver.findElements(By.tagName("a"));
			logger.info("Step1: goto mortgage calculator website !  " + " total links:  " + links.size());
			
		} catch (Exception e) {
			
			logger.error("Error", e);
			assertTrue(true);
		}
		return this;
	}
	

	/***
	 * Highlight Locate the currency symbol on the HomePage select $ sign 
	 * @param currencySymbol
	 * @return
	 */
	public HomePage selectCurrencyType(String currencySymbol) {
		WebElement CurrencyTypeDropDown = driver.findElement(By.cssSelector("#currency"));
		selLibrary.highlightElement(CurrencyTypeDropDown);
		selLibrary.selectDropDown(CurrencyTypeDropDown, currencySymbol);
		logger.info("Step2: start to locate the elements and highlight");
		return this;
	}

	/***
	 * Highlight and Locate the EnterAmount text box and send the amount data
	 * @param amount
	 * @return
	 */
	public HomePage enterAmount(String amount) {
		WebElement AmountTextBox = driver.findElement(By.cssSelector("#amount"));
		selLibrary.highlightElement(AmountTextBox);
		selLibrary.enterTxt(By.cssSelector("#amount"), amount);
		return this;
	}

	/***
	 * Highlight and Locate the AmortizationYear text box and send the AmortizationYear data
	 * @param amount
	 * @return
	 */
	public HomePage enterAmortizationYear(String amortizationYear) {
		WebElement AmortizationYear = driver.findElement(By.id("amortizationYears"));
		selLibrary.highlightElement(AmortizationYear);
		selLibrary.enterTxt(AmortizationYear, amortizationYear);
		return this;
	}

	/***
	 * Highlight and Locate the AmortizationMonth text box and send the AmortizationMonths data
	 * @param amount
	 * @return
	 */
	public HomePage enterAmortizationMonth(String amortizationMonth) {
		WebElement AmortizationMonth = driver.findElement(By.xpath("//*[@id='amortizationMonths']"));
		selLibrary.highlightElement(AmortizationMonth);
		selLibrary.enterTxt(AmortizationMonth, amortizationMonth);
		return this;
	}
	
	/***
	 * Highlight and Locate the InterestTermYear text box and send the InterestTermYear data
	 * @param amount
	 * @return
	 */
	public HomePage enterInterestTermYear(String intTermYear) {
		WebElement InterestTermYear = driver.findElement(By.id("interestTermYears"));
		selLibrary.highlightElement(InterestTermYear);
		selLibrary.enterTxt(InterestTermYear, intTermYear);
		return this;
	}

	/***
	 * Highlight and Locate the InterestTermMonths text box and send the Interest Months data
	 * @param amount
	 * @return
	 */
	public HomePage enterInterestTermMonth(String intTermMonth) {
		WebElement InterestTermMonths = driver.findElement(By.id("interestTermMonths"));
		selLibrary.highlightElement(InterestTermMonths);
		selLibrary.enterTxt(InterestTermMonths, intTermMonth);
		return this;
	}

	/***
	 * Highlight and Locate the InterestType DropDown and select the interest type data
	 * @param amount
	 * @return
	 */
	public HomePage selectInterestType(String intType) {
		WebElement InterestType = driver.findElement(By.id("interestType"));
		selLibrary.highlightElement(InterestType);
		selLibrary.selectDropDown(InterestType, intType);
		return this;
	}

	/***
	 * Highlight and Locate the InterestType TextBox and select the interest rate data
	 * @param amount
	 * @return
	 */
	public HomePage enterInterestRate(String intRate) {
		WebElement InterestRate = driver.findElement(By.cssSelector("#rate"));
		selLibrary.highlightElement(InterestRate);
		selLibrary.enterTxt(InterestRate, intRate);
		return this;
	}

	/***
	 * Highlight and Locate the enterStartMont DropDown and select the interest rate data
	 * @param amount
	 * @return
	 */
	public HomePage enterStartMonth(String startMonth) {
		WebElement StartMonth = driver.findElement(By.id("startMonth"));
		selLibrary.highlightElement(StartMonth);
		selLibrary.selectDropDown(StartMonth, startMonth);
		return this;
	}

	/***
	 * Highlight and Locate the StartYear DropDown and select the years by the value data
	 * @param amount
	 * @return
	 */
	public HomePage selectStartYear(String startYear) {
		WebElement StartYear = driver.findElement(By.id("startYear"));
		selLibrary.highlightElement(StartYear);
		selLibrary.selectDropDown(StartYear, startYear);
		return this;
	}

	/***
	 * Highlight and Locate the PaymentPeriod DropDown and select the payment period
	 * @param amount
	 * @return
	 */
	public HomePage selectPaymentPeriod(String paymentPeriod) {
		WebElement PaymentPeriod = driver.findElement(By.id("paymentMode"));
		selLibrary.highlightElement(PaymentPeriod);
		selLibrary.selectDropDown(PaymentPeriod, paymentPeriod);
		return this;
	}

	/***
	 * Highlight and Locate the Calculate Button and goto result page
	 * @param amount
	 * @return
	 */
	public HomePage clickCalculateButton() {
		WebElement CalculateButton = driver.findElement(By.id("button"));
		selLibrary.scrollToElement(CalculateButton);
		selLibrary.highlightElement(CalculateButton);
		selLibrary.waitForElementToBeClickable(By.id("button"));
		selLibrary.clickElement(By.id("button"));
		return this;
	}
}
