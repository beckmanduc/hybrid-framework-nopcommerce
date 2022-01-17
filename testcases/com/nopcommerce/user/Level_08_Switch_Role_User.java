package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstant;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.users.UserCustomerInfoPageObject;
import pageObjects.users.UserHomePageObject;
import pageObjects.users.UserLoginPageObject;

public class Level_08_Switch_Role_User extends BaseTest {
	WebDriver driver;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserCustomerInfoPageObject userCustomerInfoPage;
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	
	String userEmailAddress, userPassword, adminEmailAddress, adminPassword;

	@Parameters({ "browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		// 1 - Quyền User mở ra url của trang User lên thì nó qua HomePage
		driver = getBrowserDriver(browserName, GlobalConstant.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userEmailAddress = "abc@gmail.com"; 
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_Switch_User_To_Admin() {
		// Home -> Login(User)
		userLoginPage = userHomePage.clickToLoginLink();

		// Login vào -> ...
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		
		// Home -> My Account
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		
		// Customer Info -> click Logout link -> Home
		userHomePage = userCustomerInfoPage.clickToUserLogoutLink(driver);

		// Home(User) -> Mở ra Admin url -> Login(Admin)
		userHomePage.openUrl(driver, GlobalConstant.ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// Login -> Dashboard
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);

		// Dashboard Page -> Anny page -> Log out -> Login(Admin)
		adminLoginPage = adminDashboardPage.clickToAdminLogoutLink(driver);

	}

	@Test
	public void Role_02_Switch_Admin_To_User() {
		// Login/AnyPage (Admin) -> Open User Url -> Home Page (User)
		adminLoginPage.openUrl(driver, GlobalConstant.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		// Home -> Register / Login / Checkout / Shopping...

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
