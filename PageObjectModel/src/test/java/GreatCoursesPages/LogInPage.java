package GreatCoursesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import UtilityLibraries.Base;

public class LogInPage extends Base {

	//String UserName = "abdurahman.tevekkul@gmail.com";
	//String UserPassword = "Unicam2020";
	//WebElement EmailAddressField = driver.findElement(By.cssSelector("#login-email"));
	//WebElement PasswordField= driver.findElement(By.cssSelector("#login-password"));
	//WebElement yesIhavePasswordCheckBox = driver.findElement(By.xpath("//label[contains(text(),'Yes, I have a password')]"));
	WebElement ContinueBtn = driver.findElement(By.cssSelector("#checkout-sigin > span > span"));
	
	
	
	public BillingInformationsPage login() {
		//selLibrary.enterTxt(EmailAddressField, UserName);
		//yesIhavePasswordCheckBox.click();
		//selLibrary.enterTxt(PasswordField, UserPassword);
		
		ContinueBtn.click();	
		
		return new BillingInformationsPage();
	}

}
