package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mortgageCalculatorPages.HomePage;
import mortgageCalculatorPages.ResultPage;
import utilityLibraries.Base;
import utilityLibraries.ExcelManager;

public class MortgageCalculatorDataDrivenTest extends Base {
	final static Logger logger = Logger.getLogger(MortgageCalculatorDataDrivenTest.class);
	private int testCounter = 0;
	private String excelFile = "src/test/resources/testData/"
			+ "CalculaterTestData2.xls";
	// Read the Excel data file
	@DataProvider(name = "MortgageTestDataSet1")
	private Object[][] calculatorData(){
		Object[][] data = null;
		ExcelManager excelUtil = new ExcelManager(excelFile, 0);
		data = excelUtil.getExcelAllData();
		return data;
	}
	// We will pass the Excel data to Mortgage Calculator test below
	@Test(dataProvider = "MortgageTestDataSet1" )
	public void dataDrivenTests(String amount, String Myear, String Mmonth,
			String intYear, String intMonth, String intType, String intRate,
			String startMonth, String startYear, String paymentPeriod,
			String expectedResult) {
		
		testCounter++;
		try {
		HomePage calHomePage = new HomePage();
		calHomePage.gotoMortgageCalculatorWesite()
		//.selectCurrencyType("€")
		.selectCurrencyType("$")
		.enterAmount(amount)
		.enterAmortizationYear(Myear)
		//.enterAmortizationYear("30")
		.enterAmortizationMonth(Mmonth)
		.enterInterestTermYear(intYear)
		.enterInterestTermMonth(intMonth)
		.selectInterestType(intType)
		.enterInterestRate(intRate)
		.enterStartMonth(startMonth)
		.selectStartYear(startYear)
		.selectPaymentPeriod(paymentPeriod)
		//.selectPaymentPeriod("Monthly")
		.clickCalculateButton();
		
		ResultPage calResultPage = new ResultPage();
		String monthlyPayment = calResultPage.getMonthlyPayment();
		logger.info("Test Scenario: "+ testCounter + ", Monthly payment amount is:"
				+ monthlyPayment + ", Expected: [" + expectedResult + "]");
		
		assertEquals(monthlyPayment, expectedResult);
		
		//String weeklyPayment = calResultPage.getTotalPayment();
		//assertEquals(weeklyPayment, "$400.48");	
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}
}
