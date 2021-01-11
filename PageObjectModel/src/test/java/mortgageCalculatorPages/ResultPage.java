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
	
	public String getMonthlyPayment() {
		String monthPaymentTxt = monthPayAmntElement.getAttribute("value");
		logger.info("Monthly Payment: " + monthPaymentTxt);
		return monthPaymentTxt;
	}
	
	public String getTotalInterest() {
		WebElement totalInterestElement = driver.findElement(By.id("summaryInterest"));
		selLibrary.highlightElement(totalInterestElement);
		String totalInterestTxt = totalInterestElement.getAttribute("value");
		logger.info("Total Interest is : " + totalInterestTxt);
		return totalInterestTxt;
	}
	
	public String getTotalPayment() {
		try {
			WebElement totalSummaryPayment = driver.findElement(By.id("summaryTotal"));
			selLibrary.highlightElement(totalSummaryPayment);
			String totalSummaryTxt = totalSummaryPayment.getAttribute("value");
			selLibrary.waitForElementVisibility(By.id("summaryTotal"));
			logger.info("Total Summary Payment is: " + totalSummaryTxt);
			selLibrary.captureScreenshot("totalSummary","target/ScreenShots/");	
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);
		}
		
		return totalSummaryTxt;
	}
}

