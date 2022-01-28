package pageobjects;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static managers.DriverManager.*;
import static selenium.SeleniumHelper.*;

import cucumber.api.DataTable;
import managers.FileReaderManager;

public class LoginPage {
	private WebDriver loginPageDriver;
	private String currentScreenName;
	private String envName;

	public LoginPage() {
		currentScreenName = this.getClass().getName();
		this.loginPageDriver = getDriver();
		PageFactory.initElements(loginPageDriver, this);
	}

	@FindBy(xpath = "//button[contains(.,'Login')]")
	private WebElement btn_Login;

	@FindBy(id = "email")
	private WebElement text_Email;

	@FindBy(id = "password")
	private WebElement text_Password;

	@FindBy(id = "next")
	private WebElement btn_Singin;
	
	@FindBy(xpath = "//div[contains(@class,'profile-pic')]")
	private WebElement btn_Profile;

	@FindBy(xpath = "//span[contains(.,'Logout')]")
	private WebElement btn_Logout;

	

	public void appLogin(DataTable table)  {
		final Map<String, String> hmap = table.asMap(String.class, String.class);
	
		btn_Login.click();
		waitForAngularRequestToFinish();
		text_Email.sendKeys(hmap.get("UserName"));
		text_Password.sendKeys(hmap.get("Password"));
		btn_Singin.click();
		waitForAngularRequestToFinish();

	}

	public void appLogout() {
		waitForAngularRequestToFinish();
		btn_Profile.click();
		waitForAngularRequestToFinish();
		btn_Logout.click();
		
	}

	public void launchApp() {
		String appUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
		loginPageDriver.get(appUrl);
		waitForAngularRequestToFinish();
		
	}
}