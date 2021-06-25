package tests;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class RemoteTests {

	@Test
	public void runRemoteTest() {
		WebDriver driver = null;
		
		try {
			String baseURL = "http://www.google.com/";
			String nodeURL = "http://10.0.0.238:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();
			ChromeOptions op = new ChromeOptions();
			// FirefoxOptions op = new FirefoxOptions();
			// InternetExplorerOptions op = new InternetExplorerOptions();
			// op.setHeadless(true);
			op.merge(cap);
			driver = new RemoteWebDriver(new URL(nodeURL), op);
			
			driver.get(baseURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.close();
		driver.quit();
	}

}
