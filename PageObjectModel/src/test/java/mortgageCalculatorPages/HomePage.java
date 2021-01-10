package mortgageCalculatorPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilityLibraries.Base;

public class HomePage extends Base {
	// copy from the base library
	final static Logger logger = Logger.getLogger(HomePage.class);

	public HomePage gotoMortgageCalculatorWesite() {
		try {
			driver.get("http://www.mortgagecalculator.net/");
			String actualWebsiteTitle = driver.getTitle();
			String expctedTitle = "Mortgage Calculator 2021 - FREE Calculator Tool (ZERO Ads)";
			assertEquals(actualWebsiteTitle, expctedTitle);

			logger.info("Step1: goto mortgage calculator website");
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(true);
		}
		return this;
	}

	public HomePage selectCurrencyType(String currencySymbol) {
		WebElement CurrencyTypeDropDown = driver.findElement(By.cssSelector("#currency"));
		selLibrary.highlightElement(CurrencyTypeDropDown);
		selLibrary.selectDropDown(By.cssSelector("#currency"), currencySymbol);
		logger.info("Step2: start to locate the elements and highlight");
		return this;
	}

	public HomePage enterAmount(String amount) {
		WebElement AmountTextBox = driver.findElement(By.cssSelector("#amount"));
		selLibrary.highlightElement(AmountTextBox);
		selLibrary.enterTxt(By.cssSelector("#amount"), amount);
		return this;
	}

	public HomePage enterAmortizationYear(String amortizationYear) {
		WebElement AmortizationYear = driver.findElement(By.id("amortizationYears"));
		selLibrary.highlightElement(AmortizationYear);
		selLibrary.enterTxt(By.id("amortizationYears"), amortizationYear);
		return this;
	}

	public HomePage enterAmortizationMonth(String amortizationMonth) {
		WebElement AmortizationMonth = driver.findElement(By.xpath("//*[@id='amortizationMonths']"));
		selLibrary.highlightElement(AmortizationMonth);
		selLibrary.enterTxt(By.xpath("//*[@id='amortizationMonths']"), amortizationMonth);
		return this;
	}

	public HomePage enterInterestTermYear(String intTermYear) {
		WebElement InterestTermYear = driver.findElement(By.id("interestTermYears"));
		selLibrary.highlightElement(InterestTermYear);
		selLibrary.enterTxt(By.id("interestTermYears"), intTermYear);
		return this;
	}

	public HomePage enterInterestTermMonth(String intTermMonth) {
		WebElement InterestTermMonths = driver.findElement(By.id("interestTermMonths"));
		selLibrary.highlightElement(InterestTermMonths);
		selLibrary.enterTxt(By.id("interestTermMonths"), intTermMonth);
		return this;
	}

	public HomePage selectInterestType(String intType) {
		WebElement InterestType = driver.findElement(By.id("interestType"));
		selLibrary.highlightElement(InterestType);
		selLibrary.selectDropDown(By.id("interestType"), intType);
		return this;
	}

	public HomePage enterInterestRate(String intRate) {
		WebElement InterestRate = driver.findElement(By.cssSelector("#rate"));
		selLibrary.highlightElement(InterestRate);
		selLibrary.enterTxt(By.cssSelector("#rate"), intRate);
		return this;
	}

	public HomePage enterStartMonth(String startMonth) {
		WebElement StartMonth = driver.findElement(By.id("startMonth"));
		selLibrary.highlightElement(StartMonth);
		selLibrary.selectDropDown(By.id("startMonth"), startMonth);
		return this;
	}

	public HomePage selectStartYear(String startYear) {
		WebElement StartYear = driver.findElement(By.id("startYear"));
		selLibrary.highlightElement(StartYear);
		selLibrary.selectDropDown(By.id("startYear"), startYear);
		return this;
	}

	public HomePage selectPaymentPeriod(String paymentPeriod) {
		WebElement PaymentPeriod = driver.findElement(By.id("paymentMode"));
		selLibrary.highlightElement(PaymentPeriod);
		selLibrary.selectDropDown(By.id("paymentMode"), paymentPeriod);
		return this;
	}

	public HomePage clickCalculateButton() {
		WebElement CalculateButton = driver.findElement(By.id("button"));
		selLibrary.highlightElement(CalculateButton);
		selLibrary.waitForElementToBeClickable(By.id("button"));
		selLibrary.clickElement(By.id("button"));
		return this;
	}
}
