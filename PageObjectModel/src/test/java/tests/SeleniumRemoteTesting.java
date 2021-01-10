package tests;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleniumRemoteTesting {
	
	WebDriver driver;
	String websiteUrl ="https://www.google.com/";
	String remoteHubUrl = "http://10.0.8.200:4444/wd/hub";
	
	@Test
	public void searchGoogleRemoteTest() {
		
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions chromeOps = new ChromeOptions();
			chromeOps.setHeadless(true);
			chromeOps.merge(capabilities);
			
			driver = new RemoteWebDriver(new URL(remoteHubUrl), chromeOps);
			driver.get(websiteUrl);
			Thread.sleep(10*1000);
			
			
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
