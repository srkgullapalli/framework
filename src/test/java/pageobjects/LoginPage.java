package pageobjects;

import io.cucumber.datatable.DataTable;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static managers.DriverManager.getDriver;
import static selenium.SeleniumHelper.*;

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
		clickElement(btn_Login);
		waitForAngularRequestToFinish();
		enterTextIntoTextBox(text_Email, hmap.get("UserName"));
		text_Password.sendKeys(hmap.get("Password"));
		clickElement(btn_Singin);
		waitForAngularRequestToFinish();

	}

	public void appLogout() {
		waitForAngularRequestToFinish();
		clickElement(btn_Profile);
		waitForAngularRequestToFinish();
		clickElement(btn_Logout);		
	}

	public void launchApp() {
		String appUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
		loginPageDriver.get(appUrl);
		waitForAngularRequestToFinish();
		
	}
}