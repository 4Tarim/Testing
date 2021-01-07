package Tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UtilityLibraries.Base;

public class TestingPupopAlerts extends Base {
	final static Logger logger = Logger.getLogger(TestingPupopAlerts.class);
	int counter = 1;

	@Test
	public void browserAlertsTest() {
		driver.get("file:///C:/img/Frank/myData1/alert.html");
		checkAlert();
		WebElement btn = driver.findElement(By.tagName("button"));
		btn.click();
		selLibrary.customWait(2);
		checkAlert();
	}

	private void checkAlert() {
		try {
			logger.info("Counter: " + counter);
			Alert alert = driver.switchTo().alert();
			if (alert != null) {
				logger.info("there is Alert present, " + alert.getText());
				alert.accept();
			} else {
				logger.info("there is NO Alert present, ");
			}
		} catch (Exception e) {
			// logger.error("Error: ", e);
			logger.info("there is NO Alert present, ");
		}
		counter++;
	}
}
