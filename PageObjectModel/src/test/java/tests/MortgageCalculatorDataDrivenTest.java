package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.Assert;
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
			+ "CalculaterTestData.xls";

	@DataProvider(name = "MortgageTestDataSet1")
	private Object[][] calculatorData(){
		Object[][] data = null;
		ExcelManager excelUtil = new ExcelManager(excelFile, 0);
		data = excelUtil.getExcelAllData();
		return data;
	}

	@Test(dataProvider = "MortgageTestDataSet1" )
	public void dataDrivenTests(String amount, String Myear, String Mmonth,
			String intYear, String intMonth, String intType, String intRate,
			String startMonth, String startYear, String paymentPeriod,
			String expectedResult) {
		
		testCounter++;
		try {
		HomePage calHomePage = new HomePage();
		calHomePage.gotoMortgageCalculatorWesite()
		.selectCurrencyType("$")
		.enterAmount(amount)
		.enterAmortizationYear(Myear)
		.enterAmortizationMonth(Mmonth)
		.enterInterestTermYear(intYear)
		.enterInterestTermMonth(intMonth)
		.selectInterestType(intType)
		.enterInterestRate(intRate)
		.enterStartMonth(startMonth)
		.selectStartYear(startYear)
		.selectPaymentPeriod(paymentPeriod)
		.clickCalculateButton();
		
		
		ResultPage calResultPage = new ResultPage();
		String monthlyPayment = calResultPage.getMonthlyPayment();
		logger.info("Test Scenario: "+ testCounter + ", Monthly payment amount is:"
				+ monthlyPayment + ", Expected: [" + expectedResult + "]");
		AssertJUnit.assertEquals(monthlyPayment, expectedResult);	

		} catch (Exception e) {
			logger.error("Error: ", e);
			AssertJUnit.assertTrue(false);
		}
	}
}
