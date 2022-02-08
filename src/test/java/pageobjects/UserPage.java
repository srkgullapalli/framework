package pageobjects;

import static managers.DriverManager.getDriver;
import static selenium.SeleniumHelper.*;

import java.awt.*;
import java.util.Map;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.paulhammant.ngwebdriver.ByAngularButtonText;
import io.cucumber.datatable.DataTable;
import selenium.TestResult;

public class UserPage extends SeleneseTestCase {
	private WebDriver userPageDriver;
	private String currentScreenName;
	private String envName;

	public UserPage() {
		currentScreenName = this.getClass().getName();
		this.userPageDriver = getDriver();
		PageFactory.initElements(userPageDriver, this);
	}

	@FindBy(xpath = "//span[contains(.,'IDAM')]")
	private WebElement label_IDAM;

	@FindBy(xpath = "//span[text()='Users']")
	private WebElement label_Users;

	@ByAngularButtonText.FindBy(buttonText = "Add User")
	private WebElement btn_AddUser;

	@FindBy(xpath ="//button[contains(.,'Create User')]")
	private WebElement btn_CreateUser;
	
	@FindBy(id="firstName")
	private WebElement input_FirstName;
	
	@FindBy(id="lastName")
	private WebElement input_LastName;
	
	@FindBy(id="email")
	private WebElement input_Email;

	@FindBy(xpath="//mat-select[@id='userType']//div[contains(@id,'mat-select')]/span")
	private WebElement select_userType;
	
	@FindBy(xpath="//span[contains(.,'Select Document Type')]")
	private WebElement select_IdType;
	
	@FindBy(xpath="//mat-select[@id='gender']//div[contains(@id,'mat-select')]/span")
	private WebElement select_Gender;
	
	@FindBy(id="documentId")
	private WebElement input_DocumentID;
	
	@FindBy(xpath="//mat-select[@id='dialcode']//span[contains(.,'Select Country Code')]")
	private WebElement select_dialcode;
	
	@FindBy(id="phone")
	private WebElement input_Phone;
	
	@FindBy(xpath="//mat-label[contains(.,'Role Group')]")
	private WebElement select_RoleGroup;
	 
	@FindBy(id="title")
	private WebElement input_JobTitle;
	
	@FindBy(id="description")
	private WebElement input_Justification;
	
	@FindBy(id="physicalDeliveryOfficeName")
	private WebElement input_Employer;
	
	@FindBy(id="info")
	private WebElement input_SupervisorName;
	
	@FindBy(xpath="//mat-select[@id='dialcodei']//span[contains(.,'Select Country Code')]")
	private WebElement select_SupDialCode;
	
	@FindBy(id="otherMobile")
	private WebElement input_SupervisorMobile;
	
	@FindBy(id="otherMailbox")
	private WebElement input_OtherMail;
	
	@FindBy(id="postOfficeBox")
	private WebElement input_ProjectName;
	
	@FindBy(xpath="//input[@id='datej']/following-sibling::mat-datepicker")
	private WebElement expiryDate;
	
	@FindBy(id="streetAddress")
	private WebElement input_Tag;
	
	@FindBy(id="roomNumber")
	private WebElement input_TicketID;
	
	@FindBy(xpath="//span[contains(.,'Select Nationality')]")
	private WebElement select_Nationality;

	@FindBy(xpath="//button[contains(@class,'toggle')]")
	private WebElement toggle;

	@FindBy(xpath="//img[contains(@src,'avatars')]")
	private WebElement avatar;

	@FindBy(xpath="//span[@class='mat-option-text' and contains(.,'+91')]")
	private WebElement option_PhnIndia;

	@FindBy(xpath="//span[@class='mat-option-text' and contains(.,' test ')]")
	private WebElement option_Role;

	@FindBy(xpath="//mat-option[contains(@value,'Qatar ID')]/span")
	private WebElement option_Qatar;

	@FindBy(xpath="//span[@class='mat-option-text' and contains(.,'🇮🇳 India')]")
	private WebElement option_NationIndia;

	@FindBy(xpath="//mat-label[contains(.,'Date Of Birth')]/ancestor::mat-form-field//button")
	private WebElement calenderBtn1;

	private WebElement getElementByXpath(String xpath){
		return getDriver().findElement(By.xpath(xpath));
	}

	private WebElement getElementByCss(String css){
		return getDriver().findElement(By.cssSelector(css));
	}
	
	public void createUsers() throws AWTException {
		waitForElementToBeLoaded(btn_AddUser);
		clickElement(btn_AddUser);
		waitForAngularRequestToFinish();
		enterTextIntoTextBox(input_FirstName, "Test");
		enterTextIntoTextBox(input_LastName, "Auto");
		enterTextIntoTextBox(input_Email, "test@gmail.com");
		waitInSeconds(2000);

		clickElement_JS(calenderBtn1);
		waitInSeconds(1000);
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]/mat-calendar-header//button/span/div").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//mat-multi-year-view/table//td[contains(.,'1982')]").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'MAR')]").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'20')]").click();
		waitInSeconds(2000);
		selectOptionFromSelectBox(select_userType,"Staff");
		waitForAngularRequestToFinish();
		selectOptionFromSelectBox(select_Gender,"Male");
		waitForAngularRequestToFinish();

		scrollDown(userPageDriver);
		enterTextIntoTextBox(input_DocumentID, "9876543212");
		waitInSeconds(1000);
		selectOptionFromDropDown(select_IdType,option_Qatar);
		waitForAngularRequestToFinish();

		selectOptionFromDropDown(select_dialcode,option_PhnIndia);
		enterTextIntoTextBox(input_Phone, "9676098748");
		waitInSeconds(2000);
		selectOptionFromDropDown(select_RoleGroup,option_Role);

		enterTextIntoTextBox(input_JobTitle, "QA Test");
		enterTextIntoTextBox(input_Justification, "AutomationScript");
		enterTextIntoTextBox(input_Employer, "Botnotch");
		enterTextIntoTextBox(input_SupervisorName, "Automation");
		takeScreenshot(getDriver(), TestResult.EXCEPTION, "Date_Screenshot");

		waitInSeconds(3000);
		selectOptionFromDropDown(select_SupDialCode,option_PhnIndia);
		enterTextIntoTextBox(input_SupervisorMobile, "9247783959");
		enterTextIntoTextBox(input_OtherMail, "test@live.com");
		enterTextIntoTextBox(input_ProjectName, "Automation");

		getElementByXpath("//mat-label[contains(.,'Access Expiry Date')]/ancestor::mat-form-field//button").click();
		waitInSeconds(1000);
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]/mat-calendar-header//button/span/div").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//mat-multi-year-view/table//td[contains(.,'2022')]").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'MAR')]").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'20')]").click();

		enterTextIntoTextBox(input_Tag, "Auto");
		enterTextIntoTextBox(input_TicketID, "56784");
		selectOptionFromDropDown(select_Nationality,option_NationIndia);
        clickElement_JS(btn_CreateUser);
		takeScreenshot(getDriver(), TestResult.EXCEPTION, "Date_Screenshot");
		waitForAngularRequestToFinish();
	}
}