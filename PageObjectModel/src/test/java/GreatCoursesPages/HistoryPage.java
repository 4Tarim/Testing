package GreatCoursesPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import UtilityLibraries.Base;

public class HistoryPage extends Base {

	public HistoryPage() {
		WebElement historyCourseRegion = selLibrary.waitForElementPresence(By.className("category-products-container"));
		Assert.assertNotNull(historyCourseRegion, "Category Products region is not visible.");
	}

	public ChooseAFormatPage selectHolyLandRevealed() {
		selectAProductName("Holy Land Revealed");
		return new ChooseAFormatPage();
	}

	public ChooseAFormatPage selectAmericanCivilWar() {
		selectAProductName("selectAmericanCivilWarCourse");
		return new ChooseAFormatPage();
	}

	public ChooseAFormatPage selectVikingsCourse() {
		selectAProductName("Holy Land Revealed");
		return new ChooseAFormatPage();
	}
	
	
	
	
	
	
	

	// Helper method and visible only within this class
	private void selectAProductName(String productName) {
		WebElement courseRegion = selLibrary.waitForElementPresence(By.className("category-products-container"));
		Assert.assertNotNull(courseRegion);

		List<WebElement> liElems = courseRegion.findElements(By.tagName("li"));
		for (WebElement li : liElems) {
			WebElement productNameElem = li.findElement(By.tagName("h2"));

			System.out.println("Product name: [ " + productNameElem.getText() + "]");
			if (productNameElem.getText().contains(productName)) {
				productNameElem.click();
				break;
			}
		}
	}
}
