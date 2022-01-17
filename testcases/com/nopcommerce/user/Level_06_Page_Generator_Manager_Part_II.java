package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.users.UserCustomerInfoPageObject;
import pageObjects.users.UserHomePageObject;
import pageObjects.users.UserLoginPageObject;
import pageObjects.users.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_Part_II extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass	
	public void beforeClass(String browserName, String url) {
		// 1 - Open Url => Home Page
		driver = getBrowserDriver(browserName, url);
		homePage = new UserHomePageObject(driver);

		firstName="John";
		lastName="Terry";
		emailAddress = "johnterry" + generateFakeNumber() + "@mail.net";
		password="123456";
	}

	@Test
	public void User_01_Register_To_System() {

		// TDD: Test Driven Testing
		// 2 - On Home Page => Click Register link => Open Register Page (Business page)
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisteredSucessMessage(), "Your registration completed");
		
		// 3 - On Register page => switch to Home Page
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login_To_System() {
		// 4 - On Home Page => Switch to Login Page
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		// 5 - On Login Page => Switch to Home Page
		homePage = loginPage.clickToLoginButton();
	}

	@Test
	public void User_03_My_Accoount_Infor() {
		// 6 - On Home Page => Switch to CustomerInfo Page
		customerInfoPage = homePage.clickToMyAccountLink();
		
		Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);

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
