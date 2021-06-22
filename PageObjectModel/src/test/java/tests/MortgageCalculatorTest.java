package tests;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import mortgageCalculatorPages.HomePage;
import mortgageCalculatorPages.ResultPage;
import utilityLibraries.Base;

public class MortgageCalculatorTest extends Base {

	final static Logger logger = Logger.getLogger(ResultPage.class);
	
	@Test
	public void buySinglehouse() {
		
		//goto HomePage	
		HomePage myHomePage = new HomePage();
		myHomePage.gotoMortgageCalculatorWesite();
		myHomePage.selectCurrencyType("$");
		myHomePage.enterAmount("500000");
		myHomePage.enterAmortizationYear("29");
		myHomePage.enterAmortizationMonth("12");
		myHomePage.enterInterestTermYear("29");
		myHomePage.enterInterestTermMonth("12");
		myHomePage.selectInterestType("Fixed");
		myHomePage.enterInterestRate("3.2");
		myHomePage.enterStartMonth("12");
		myHomePage.selectStartYear("2021");
		myHomePage.selectPaymentPeriod("Monthly");
		myHomePage.clickCalculateButton();	
		
		
		// ResultPage--- get MonthlyPayment, Total Interest, Total Payment 
		ResultPage resultPage = new ResultPage();
		String monthlyPayment = resultPage.getMonthlyPayment();
		logger.info("Monthly Payment: " + monthlyPayment);
		String getTotalInterest = resultPage.getTotalInterest();
		logger.info("Total Interest is:  " + getTotalInterest);
		String TotalSummaryPayment = resultPage.getTotalPayment();
		logger.info("Total SUmmary Payment: " + TotalSummaryPayment);
		
	}
}
