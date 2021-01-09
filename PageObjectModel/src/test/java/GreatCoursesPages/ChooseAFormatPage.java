package GreatCoursesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import UtilityLibraries.Base;

public class ChooseAFormatPage extends Base {
	private WebElement instantVideoRadioBtn;

	public ChooseAFormatPage() {
		instantVideoRadioBtn = selLibrary
				.waitForElementPresence(By.cssSelector("#media-format-radio > div:nth-child(1) > label"));
		Assert.assertNotNull(instantVideoRadioBtn, "Instant Video Radio button element is not visible.");
	}

	public ChooseAFormatPage selectInstanceVideoRadioBtn() {
		instantVideoRadioBtn.click();
		selLibrary.handleCheckBox(instantVideoRadioBtn, true);
		return this;
	}

	public ProceesToCheckoutPage clickAddToCartBtn() {
		WebElement addToCartBtn = selLibrary.waitForElementToBeClickable(By.cssSelector("#add-to-cart-btn"));
		addToCartBtn.click();
		return new ProceesToCheckoutPage();
	}
}
