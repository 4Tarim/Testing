package com.selenium.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.selenium.mortgageCalculatorPages.HomePage;

import utilityLibraries.Base;

public class SpinnerPage extends Base {
	final static Logger logger = Logger.getLogger(HomePage.class);

	@Test
	
	public void clickTimeSpinner() throws Exception {
		Actions action = new Actions(driver);
		driver.get("https://jqueryui.com/spinner/#time");
		driver.manage().window().maximize();
		
		// we could not locate the webelement because of Iframe
		selLibrary.getAlliframes();
		selLibrary.switchToIframeByIdex(0);

		// locate spinner narrow Icon webelement and perform click action
		WebElement spinnerNarrowIcon = driver.findElement(By.cssSelector(".ui-icon-triangle-1-n"));
		selLibrary.highlightElement(spinnerNarrowIcon);
		action.moveToElement(spinnerNarrowIcon);
		action.click(spinnerNarrowIcon).perform();

		Thread.sleep(5 * 1000);

	}
}
