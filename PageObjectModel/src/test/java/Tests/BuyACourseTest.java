package Tests;

import org.testng.annotations.Test;

import GreatCoursesPages.ChooseAFormatPage;
import GreatCoursesPages.HistoryPage;
import GreatCoursesPages.HomePage;
import GreatCoursesPages.LogInPage;
import GreatCoursesPages.ProceesToCheckoutPage;
import UtilityLibraries.Base;

public class BuyACourseTest extends Base {
	HomePage myHomePage = new HomePage();
	HistoryPage myHistoryPage;
	ChooseAFormatPage myChooseAFormatPage;
	ProceesToCheckoutPage myProceedCheckOutPage;
	LogInPage myLogInPage;

	@Test(enabled = false)
	public void checkingHomePageTest() {
		myHomePage.goto_theGreatCourseWebsite();
	}

	@Test
	public void buyHolyLandRevealed() {
		checkingHomePageTest();
		myHistoryPage = myHomePage.selectHistoryCategory();
		myChooseAFormatPage = myHistoryPage.selectHolyLandRevealed();
		myChooseAFormatPage.selectInstanceVideoRadioBtn();
		myProceedCheckOutPage=myChooseAFormatPage.clickAddToCartBtn();
		myProceedCheckOutPage.click_ProceedToCheckOutBtn();
		myLogInPage = myProceedCheckOutPage.click_ProceedToCheckOutBtn();
		myLogInPage.login();

	}

}
