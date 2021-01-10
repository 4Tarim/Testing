package tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utilityLibraries.Base;

public class FileUploadTests extends Base {

	
	@Test
	public void fileUploadTest1() {
		driver.get("https://html.com/input-type-file/");
		//assertTrue(false);
		selLibrary.customWait(3);
		selLibrary.uploadFile("src/test/resources/drivers/chromedriver.exe", By.id("fileupload"));
		selLibrary.customWait(8);
	}
	
	@Test
	public void fileUploadTest2() {
		//assertTrue(false);
		driver.get("https://html.com/input-type-file/");		
		selLibrary.customWait(3);
		selLibrary.uploadFile("src/test/resources/drivers/chromedriver.exe", By.id("fileupload"));
		selLibrary.customWait(8);
	}
	
	@Test
	public void fileUploadTest3() {
		driver.get("https://html.com/input-type-file/");		
		selLibrary.customWait(3);
		selLibrary.uploadFile("src/test/resources/drivers/chromedriver.exe", By.id("fileupload"));
		//assertTrue(false);
		selLibrary.customWait(8);
	}
	
}
