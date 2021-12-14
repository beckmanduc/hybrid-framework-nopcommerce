package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String emailAddress;
	
	// Declare
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName,url);

		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		
		// Initial page
		homePage = new HomePageObject(driver);

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		// TDD: Test Driven Testing
		// 2 - On Home Page => Click Register link => Open Register Page (Business page)
		homePage.clickToRegisterLink();
		registerPage =  new RegisterPageObject(driver);
		
		registerPage.clickToRegisterButton();
	
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox("123@456#%*");
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("123456");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisteredSucessMessage(), "Your registration completed");

		// 3 - Register Page switch to Home Page
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getExistedEmailErrorMessage(), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123");
		registerPage.sendkeyToConfirmPasswordTextbox("123");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("654321");

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
