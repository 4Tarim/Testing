package mortgageCalculatorPages;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilityLibraries.Base;

public class ResultPage extends Base {
	final static Logger logger = Logger.getLogger(ResultPage.class);
	private WebElement monthPayAmntElement = null;

	String totalSummaryTxt;
	String monthPaymentTxt;
	String totalInterestTxt;

	/***
	 * waiting calculation finsh and get the final result of Monthly Payments
	 */
	public ResultPage() {
		try {
			logger.info("Step 3: Page Synchronization -------------> to ResultPage");
			monthPayAmntElement = selLibrary.waitForElementVisibility(By.id("summaryMonthly"));
			selLibrary.highlightElement(monthPayAmntElement);
			assertNotNull(monthPayAmntElement);
		} catch (Exception e) {
			logger.error("Error: ", e);
			logger.info("Step 4: Monthly payment and total payments results in present");
			assertTrue(false);
		}
	}

	/****
	 * use getAttribute method to get Monthly Paymet amount value from monthly
	 * Payment Box
	 * 
	 * @return
	 */
	public String getMonthlyPayment() {
		String monthPaymentTxt = monthPayAmntElement.getAttribute("value");
		return monthPaymentTxt;
	}

	/***
	 * Locate the Total Interest result box and highlight, get the value, print
	 * using Apache Log4J
	 * 
	 * @return
	 */
	public String getTotalInterest() {
		WebElement totalInterestElement = driver.findElement(By.id("summaryInterest"));
		selLibrary.highlightElement(totalInterestElement);
		String totalInterestTxt = totalInterestElement.getAttribute("value");
		return totalInterestTxt;
	}

	/***
	 * Locate the total payment section and highlight, get the value using
	 * getAttribute value method
	 * 
	 * @return
	 */
	public String getTotalPayment() {
		try {
			WebElement totalSummaryPayment = driver.findElement(By.id("summaryTotal"));
			selLibrary.highlightElement(totalSummaryPayment);
			String totalSummaryTxt = totalSummaryPayment.getAttribute("value");
			selLibrary.waitForElementVisibility(By.id("summaryTotal"));
			logger.info("Total Summary Payment is: " + totalSummaryTxt);
			Thread.sleep(2 * 1000);
			selLibrary.captureScreenshot("totalSummary", "target/ScreenShots/");
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);
		}
		return totalSummaryTxt;
	}

	/***
	 * Scroll down to MortgaePayment Table and take screenshot for report
	 */
	public void captureScreenshotMortgagePaymentTable() {
		try {
			WebElement mortgaePaymentTable = driver.findElement(By.cssSelector(
					"#mortgage > div.calcHolder.flexContainer.mortgage > div.mortageTableWrapper > table"));
			selLibrary.highlightElement(mortgaePaymentTable);
			Thread.sleep(3 * 1000);
			selLibrary.captureScreenshot("Mortgage Payment Table", "target/ScreenShots/");
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);
		}
	}
}
