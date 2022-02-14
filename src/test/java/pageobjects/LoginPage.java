package pageobjects;

import com.paulhammant.ngwebdriver.ByAngularButtonText;
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
	
	@FindBy(xpath = "//idams-toolbar-user/div")
	private WebElement btn_Profile;

	@ByAngularButtonText.FindBy(buttonText = "Logout")
	private WebElement btn_Logout;

	@ByAngularButtonText.FindBy(buttonText = "Yes")
	private WebElement confirm_Yes;

	public void appLogin(DataTable table)  {
		final Map<String, String> hmap = table.asMap(String.class, String.class);

		clickElement(btn_Login);
		waitForAngularRequestToFinish();
		enterTextIntoTextBox(text_Email, hmap.get("UserName"));
		enterTextIntoTextBox(text_Password, hmap.get("Password"));
		clickElement(btn_Singin);
		waitForAngularRequestToFinish();
	}

	public void appLogout() {
		waitForElementToBeLoaded(btn_Profile);
		clickElement_JS(btn_Profile);
		waitForElementToBeLoaded(btn_Logout);
		clickElement_JS(btn_Logout);
		clickElement(confirm_Yes);
	}

	public void launchApp(DataTable table) {
		final Map<String, String> hmap = table.asMap(String.class, String.class);
		String appUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();

		if(hmap.get("launchMode").equalsIgnoreCase("Incognito")) {
		   getIncognitoDriver().get(appUrl);
		}
		else {
			getDriver().get(appUrl);
		}
		waitForAngularRequestToFinish();
	}
}