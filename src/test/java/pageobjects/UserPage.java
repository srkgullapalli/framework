package pageobjects;

import static managers.DriverManager.getDriver;
import static selenium.SeleniumHelper.*;
import java.util.Map;

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

public class UserPage {
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
	
	@FindBy(id="firstName")
	private WebElement input_FirstName;
	
	@FindBy(id="lastName")
	private WebElement input_LastName;
	
	@FindBy(id="email")
	private WebElement input_Email;
	
	@FindBy(id ="datei")
	private WebElement date_Dob;
	
	@FindBy(id ="datej")
	private WebElement date_Expiry;
	
	@FindBy(xpath="//mat-select[@id='userType']//div[contains(@id,'mat-select')]/span")
	private WebElement select_userType;
	
	@FindBy(xpath="//mat-select[@id='idType']//div[contains(@id,'mat-select')]/span")
	private WebElement select_IdType;
	
	@FindBy(xpath="//mat-select[@id='gender']//div[contains(@id,'mat-select')]/span")
	private WebElement select_Gender;
	
	@FindBy(id="documentId")
	private WebElement input_DocumentID;
	
	@FindBy(id="dialcode")
	private WebElement select_dialcode;
	
	@FindBy(id="phone")
	private WebElement input_Phone;
	
	@FindBy(xpath="//mat-select[@id='mat-select-16']")
	private WebElement select_RoleGroup;
	 
	@FindBy(id="title")
	private WebElement input_JobTitle;
	
	@FindBy(id="description")
	private WebElement input_Justification;
	
	@FindBy(id="physicalDeliveryOfficeName")
	private WebElement input_Employer;
	
	@FindBy(id="info")
	private WebElement input_SupervisorName;
	
	@FindBy(id="dialcodei")
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

	private WebElement getElementByXpath(String xpath){
		return getDriver().findElement(By.xpath(xpath));
	}

	private WebElement getElementByCss(String css){
		return getDriver().findElement(By.cssSelector(css));
	}
	
	public void createUsers(DataTable table) {
		waitForElementToBeLoaded(btn_AddUser);
		clickElement(btn_AddUser);
		waitForAngularRequestToFinish();


		getElementByCss("svg.mat-datepicker-toggle-default-icon.ng-star-inserted > path").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]/mat-calendar-header//button/span/div").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//mat-multi-year-view/table//td[contains(.,'1982')]").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'MAR')]").click();
		getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'20')]").click();

		selectOptionFromSelectBox(select_IdType,"Qatar ID");
		waitForAngularRequestToFinish();
		selectOptionFromSelectBox(select_userType,"Staff");
		waitForAngularRequestToFinish();
		selectOptionFromSelectBox(select_Gender,"Male");
		waitForAngularRequestToFinish();

		enterTextIntoTextBox(input_FirstName, "Test");
		waitInSeconds(10);
		takeScreenshot(getDriver(), TestResult.EXCEPTION, "Date_Screenshot");


		//selectOptionFromSelectBox(select_IdType, "Passport Number");
		/*
		enterTextIntoTextBox(input_FirstName, "Test");
		enterTextIntoTextBox(input_LastName, "Auto");
		enterTextIntoTextBox(input_Email, "test@gmail.com");

        enterTextIntoTextBox(input_DocumentID, "9876543212");
        
       selectOptionFromSelectBox(select_IdType, "Passport Number");
       
       
        selectOptionFromSelectBox(select_userType, "Staff");
        selectOptionFromSelectBox(select_Gender, "Male");
        waitInSeconds(5);
        Actions at = new Actions(userPageDriver);
        at.sendKeys(Keys.PAGE_DOWN).build().perform();
        
        selectOptionFromSelectBox(select_dialcode, "🇮🇳 India+91");
		enterTextIntoTextBox(input_Phone, "9676098748");
        selectOptionFromSelectBox(select_RoleGroup, "test34");
		enterTextIntoTextBox(input_JobTitle, "QA Test");
		enterTextIntoTextBox(input_Justification, "AutomationScript");
		enterTextIntoTextBox(input_Employer, "Botnotch");
		enterTextIntoTextBox(input_SupervisorName, "Automation");
        waitInSeconds(5);

        selectOptionFromSelectBox(select_SupDialCode, "🇮🇳 India+91");
		enterTextIntoTextBox(input_SupervisorMobile, "9247783959");
		enterTextIntoTextBox(input_OtherMail, "test@live.com");
		enterTextIntoTextBox(input_ProjectName, "Automation");
        selectDateByJS(date_Expiry, "15-Dec-2022");
		enterTextIntoTextBox(input_Tag, "Auto");
		enterTextIntoTextBox(input_TicketID, "56784");
        waitInSeconds(5);

        selectDateByJS(select_Nationality, "🇮🇳 India");
        
			waitInSeconds(30);
		
        //clickElement(btn_AddUser);

		*/
	}
}