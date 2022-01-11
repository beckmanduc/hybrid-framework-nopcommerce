package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.OrdersPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private OrdersPageObject orderPage;
	private AddressPageObject addressPage;
	private RewardPointPageObject rewardPointPage;
	private String firstName, lastName, emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		// 1 - Open Url => Home Page
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName = "John";
		lastName = "Terry";
		emailAddress = "johnterry" + generateFakeNumber() + "@mail.net";
		password = "123456";
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
	
	@Test
	public void User_04_Navigate_Page() {
		System.out.println("Customer Infor Page => Orders Page");
		orderPage = customerInfoPage.openOrderPage(driver);
		
		System.out.println("Orders Page => Reward Point");
		rewardPointPage = orderPage.openRewardPointPage(driver);
		
		System.out.println("Reward Point => Orders Page");
		orderPage = rewardPointPage.openOrderPage(driver);
		
		System.out.println("Orders Page => Customer Infor Page");
		customerInfoPage = orderPage.openCustomerInfoPage(driver);
		
		System.out.println("Customer Info Page => Address Page");
		addressPage = customerInfoPage.openAddressPage(driver);
		
		System.out.println("Address Page => Reward Point");
		rewardPointPage =  addressPage.openRewardPointPage(driver);
		
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
