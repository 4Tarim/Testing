package GreatCoursesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import UtilityLibraries.Base;

public class ProceesToCheckoutPage extends Base {

	@FindBy(css = "button[title='Proceed To Checkout'][class='button btn-checkout']")
	WebElement proceedToCheckOutBtn;

	public LogInPage click_ProceedToCheckOutBtn() {
		proceedToCheckOutBtn.click();

		return new LogInPage();
	}
}
