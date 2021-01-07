package Tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import MortgageCalculatorPages.HomePage;
import MortgageCalculatorPages.ResultPage;
import UtilityLibraries.Base;

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
		
		// ResultPage
		ResultPage resultPage = new ResultPage();
		String monthlyPayment = resultPage.getMonthlyPayment();
		logger.info("Monthly Payment: " + monthlyPayment);
		String TotalSummaryPayment = resultPage.getTotalPayment();
		logger.info("Total SUmmary Payment: " + TotalSummaryPayment);	
		
	}
}
