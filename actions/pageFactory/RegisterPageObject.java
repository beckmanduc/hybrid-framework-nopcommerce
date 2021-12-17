package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "register-button")
	private WebElement registerButton;

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextbox;

	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextbox;

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;

	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;

	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement firstNameErrorMsg;

	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lasttNameErrorMsg;

	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMsg;

	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement passwordErrorMsg;

	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMsg;

	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerdSuccessMsg;

	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]//li")
	private WebElement existedEmailErrorMsg;

	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(registerButton);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver,firstNameErrorMsg);
		return getElementText(firstNameErrorMsg);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver,lasttNameErrorMsg);
		return getElementText(lasttNameErrorMsg);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMsg);
		return getElementText(emailErrorMsg);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMsg);
		return getElementText(passwordErrorMsg);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPasswordErrorMsg);
		return getElementText(confirmPasswordErrorMsg);
	}

	public void sendkeyToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeyToElement(firstNameTextbox, firstName);

	}

	public void sendkeyToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeyToElement(lastNameTextbox, lastName);
	}

	public void sendkeyToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(emailTextbox, emailAddress);
	}

	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(passwordTextbox, password);
	}

	public void sendkeyToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendKeyToElement(confirmPasswordTextbox, confirmPassword);
	}

	public String getRegisteredSucessMessage() {
		waitForElementVisible(driver, registerdSuccessMsg);
		return getElementText(registerdSuccessMsg);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(logoutLink);

	}

	public String getExistedEmailErrorMessage() {
		waitForElementVisible(driver, existedEmailErrorMsg);
		return getElementText(existedEmailErrorMsg);
	}

}
