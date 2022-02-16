package pageobjects;

import com.paulhammant.ngwebdriver.ByAngularButtonText;
import com.thoughtworks.selenium.SeleneseTestCase;
import io.cucumber.datatable.DataTable;
import model.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.TestResult;

import java.awt.*;
import java.util.List;
import java.util.Map;

import static managers.DriverManager.getDriver;
import static selenium.SeleniumHelper.*;

public class UserPage extends SeleneseTestCase {
    private WebDriver userPageDriver;
    private String currentScreenName;
    private String envName;

    @FindBy(xpath = "//span[contains(.,'IDAM')]")
    private WebElement label_IDAM;

    @FindBy(xpath = "//span[text()='Users']")
    private WebElement label_Users;

    @ByAngularButtonText.FindBy(buttonText = "Add User")
    private WebElement btn_AddUser;

    @FindBy(xpath = "//button[contains(.,'Create User')]")
    private WebElement btn_CreateUser;

    @FindBy(id = "firstName")
    private WebElement input_FirstName;

    @FindBy(id = "lastName")
    private WebElement input_LastName;

    @FindBy(id = "email")
    private WebElement input_Email;

    @FindBy(xpath = "//mat-select[@id='userType']//div[contains(@id,'mat-select')]/span")
    private WebElement select_userType;

    @FindBy(xpath = "//span[contains(.,'Select Document Type')]")
    private WebElement select_IdType;

    @FindBy(xpath = "//mat-select[@id='gender']//div[contains(@id,'mat-select')]/span")
    private WebElement select_Gender;

    @FindBy(id = "documentId")
    private WebElement input_DocumentID;

    @FindBy(xpath = "//mat-select[@id='dialcode']//span[contains(.,'Select Country Code')]")
    private WebElement select_dialcode;

    @FindBy(id = "phone")
    private WebElement input_Phone;

    @FindBy(xpath = "//mat-label[contains(.,'Role Group')]")
    private WebElement select_RoleGroup;

    @FindBy(id = "title")
    private WebElement input_JobTitle;

    @FindBy(id = "description")
    private WebElement input_Justification;

    @FindBy(id = "physicalDeliveryOfficeName")
    private WebElement input_Employer;

    @FindBy(id = "info")
    private WebElement input_SupervisorName;

    @FindBy(xpath = "//mat-select[@id='dialcodei']//span[contains(.,'Select Country Code')]")
    private WebElement select_SupDialCode;

    @FindBy(id = "otherMobile")
    private WebElement input_SupervisorMobile;

    @FindBy(id = "otherMailbox")
    private WebElement input_OtherMail;

    @FindBy(id = "postOfficeBox")
    private WebElement input_ProjectName;

    @FindBy(xpath = "//input[@id='datej']/following-sibling::mat-datepicker")
    private WebElement expiryDate;

    @FindBy(id = "streetAddress")
    private WebElement input_Tag;

    @FindBy(id = "roomNumber")
    private WebElement input_TicketID;

    @FindBy(xpath = "//span[contains(.,'Select Nationality')]")
    private WebElement select_Nationality;

    @FindBy(xpath = "//button[contains(@class,'toggle')]")
    private WebElement toggle;

    @FindBy(xpath = "//img[contains(@src,'avatars')]")
    private WebElement avatar;

    @FindBy(xpath = "//span[@class='mat-option-text' and contains(.,'+91')]")
    private WebElement option_PhnIndia;

    @FindBy(xpath = "//span[@class='mat-option-text' and contains(.,'Automation Administrator')]")
    private WebElement option_Role;

    @FindBy(xpath = "//mat-option[contains(@value,'Qatar ID')]/span")
    private WebElement option_Qatar;

    @FindBy(xpath = "//span[@class='mat-option-text' and contains(.,'🇮🇳 India')]")
    private WebElement option_NationIndia;

    @FindBy(xpath = "//mat-label[contains(.,'Date Of Birth')]/ancestor::mat-form-field//button")
    private WebElement calenderBtn1;

    @FindBy(id = "filterText")
    private WebElement input_SearchUser;

    @FindBy(xpath = "//table[@id='mytable']//td[3]")
    private WebElement table_IDAMID;

    @FindBy(xpath = "//table[@id='mytable']//td[6]")
    private WebElement table_userStatus;

    @FindBy(xpath = "//table[@id='mytable']//td[9]")
    private WebElement table_userApprovalStatus;

    @FindBy(xpath = "//button[contains(@aria-label,'row action with a menu')]")
    private WebElement btn_ActionUsers;

    @FindBy(xpath = "//span[contains(.,'View Details')]")
    private WebElement btn_ViewDetails;

    @FindBy(xpath = "//mat-button-toggle[@value='Approved']//button[@name='approvalStatus']//mat-icon")
    private List<WebElement> btn_approveOrReject;

    @FindBy(xpath = "//textarea[@name='Comment']")
    private List<WebElement> textarea_Comments;

    @FindBy(id = "rejectBtn")
    private WebElement btn_Submit;

    @ByAngularButtonText.FindBy(buttonText = "Yes")
    private WebElement confirm_Yes;

    @ByAngularButtonText.FindBy(buttonText = "No")
    private WebElement confirm_No;

    @ByAngularButtonText.FindBy(buttonText = "Block Access")
    private WebElement btn_BlockAccess;

    @ByAngularButtonText.FindBy(buttonText = "Unblock Access")
    private WebElement btn_UnBlockAccess;

    @ByAngularButtonText.FindBy(buttonText = "Close")
    private WebElement btn_CloseUserScreen;

    @FindBy(xpath = "//textarea[contains(@name,'comment.managerComment')]")
    private WebElement textArea_ManagerComments;

    @FindBy(xpath = "(//textarea[contains(@name,'comment.approverComment')])[2]")
    private WebElement textArea_ApproverComments;

    @ByAngularButtonText.FindBy(buttonText = "Ok")
    private WebElement btn_Ok;

    @ByAngularButtonText.FindBy(buttonText = "OK")
    private WebElement btn_OK;

    @FindBy(xpath = "//button//span[contains(.,'Approve Block')]")
    private WebElement btn_ApproveBlock;

    @FindBy(xpath = "//button//span[contains(.,'Approve Unblock')]")
    private WebElement btn_ApproveUnBlock;

    @ByAngularButtonText.FindBy(buttonText = "Next")
    private WebElement btn_Next;

    @FindBy(xpath = "(//button[@type='submit']//span[contains(.,'Next')])[2]")
    private WebElement btn_NextUpload;

    @ByAngularButtonText.FindBy(buttonText = "Upload")
    private WebElement btn_Upload;

    @FindBy(xpath = "//mat-icon[@id='inputFileIcon']")
    private WebElement input_UploadClick;

    @FindBy(id = "inputFile")
    private WebElement input_File;

    public UserPage() {
        currentScreenName = this.getClass().getName();
        this.userPageDriver = getDriver();
        PageFactory.initElements(userPageDriver, this);
    }

    private WebElement getElementByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    private WebElement getElementByCss(String css) {
        return getDriver().findElement(By.cssSelector(css));
    }

    public void createUsers(TestContext testContext) throws AWTException {
        waitForElementToBeLoaded(btn_AddUser);
        clickElement(btn_AddUser);
        waitForAngularRequestToFinish();
        enterTextIntoTextBox(input_FirstName, "Test");
        enterTextIntoTextBox(input_LastName, "Auto");
        enterTextIntoTextBox(input_Email, generateRandomEmail());
        waitInSeconds(2000);

        clickElement_JS(calenderBtn1);
        waitInSeconds(1000);
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]/mat-calendar-header//button/span/div").click();
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//mat-multi-year-view/table//td[contains(.,'1982')]").click();
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'MAR')]").click();
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'20')]").click();
        waitInSeconds(2000);
        selectOptionFromSelectBox(select_userType, "Staff");
        waitForAngularRequestToFinish();
        selectOptionFromSelectBox(select_Gender, "Male");
        waitForAngularRequestToFinish();

        scrollDown(userPageDriver);
        enterTextIntoTextBox(input_DocumentID, "9876543212");
        waitInSeconds(1000);
        selectOptionFromDropDown(select_IdType, option_Qatar);
        waitForAngularRequestToFinish();

        selectOptionFromDropDown(select_dialcode, option_PhnIndia);
        long phnNo = generateRandomNo();
        enterTextIntoTextBox(input_Phone, String.valueOf(phnNo));
        waitInSeconds(2000);
        testContext.setUserPhoneNo(String.valueOf(phnNo));
        selectOptionFromDropDown(select_RoleGroup, option_Role);

        enterTextIntoTextBox(input_JobTitle, "QA Test");
        enterTextIntoTextBox(input_Justification, "AutomationScript");
        enterTextIntoTextBox(input_Employer, "Botnotch");
        enterTextIntoTextBox(input_SupervisorName, "Automation");
        takeScreenshot(getDriver(), TestResult.EXCEPTION, "Date_Screenshot");

        waitInSeconds(3000);
        selectOptionFromDropDown(select_SupDialCode, option_PhnIndia);
        enterTextIntoTextBox(input_SupervisorMobile, String.valueOf(generateRandomNo()));
        enterTextIntoTextBox(input_OtherMail, generateRandomEmail());
        enterTextIntoTextBox(input_ProjectName, "Automation");

        getElementByXpath("//mat-label[contains(.,'Access Expiry Date')]/ancestor::mat-form-field//button").click();
        waitInSeconds(1000);
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]/mat-calendar-header//button/span/div").click();
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//mat-multi-year-view/table//td[contains(.,'2022')]").click();
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'MAR')]").click();
        getElementByXpath("//mat-calendar[contains(@id,'mat-datepicker')]//td[contains(.,'20')]").click();

        enterTextIntoTextBox(input_Tag, "Auto");
        enterTextIntoTextBox(input_TicketID, "56784");
        selectOptionFromDropDown(select_Nationality, option_NationIndia);
        clickElement_JS(btn_CreateUser);
        takeScreenshot(getDriver(), TestResult.EXCEPTION, "Date_Screenshot");
        waitForAngularRequestToFinish();
    }

    public void navigateViewDetails(TestContext testContext) {
        clickElement_JS(btn_ActionUsers);
        waitForElementToBeLoaded(btn_ViewDetails);
        clickElement_JS(btn_ViewDetails);
        waitForAngularRequestToFinish();
        waitInSeconds(3000);
    }

    public void approveRejectUserAccess() {
        scrollDown(userPageDriver);
        Actions act = new Actions(userPageDriver);
        act.moveToElement(textarea_Comments.get(0)).build().perform();
        System.out.println("Element Size " + btn_approveOrReject.size());

        for (int i = 0; i < btn_approveOrReject.size(); i++) {
            waitForElementToBeLoaded(btn_approveOrReject.get(i));
            clickElement_JS(btn_approveOrReject.get(i));
        }
        clickElement_JS(btn_Submit);
        waitInSeconds(2000);
        waitForAngularRequestToFinish();
        clickElement_JS(confirm_Yes);
        waitForAngularRequestToFinish();

    }

    public void verifyRoleApprovalStatus(TestContext testContext, DataTable table) {
        final Map<String, String> hmap = table.asMap(String.class, String.class);
        Actions act = new Actions(userPageDriver);

        if (hmap.get("approvalStatus") != null) {
            waitForElementToBeLoaded(table_userApprovalStatus);
            act.moveToElement(table_userApprovalStatus).build().perform();
            Assert.assertEquals(table_userApprovalStatus.getText(), hmap.get("approvalStatus"));
        } else if (hmap.get("IDAMID") != null) {
            waitForElementToBeLoaded(table_IDAMID);
            act.moveToElement(table_IDAMID).build().perform();
            Assert.assertNotNull(table_IDAMID, "IDAM ID is not generated for user");
        } else if (hmap.get("userStatus") != null) {
            waitForElementToBeLoaded(table_userStatus);
            act.moveToElement(table_userStatus).build().perform();
            Assert.assertEquals(table_userStatus.getText(), hmap.get("userStatus"));
        }
    }

    public void blockUnBlockUser(DataTable table) {
        final Map<String, String> hmap = table.asMap(String.class, String.class);
        if (hmap.get("action").equalsIgnoreCase("Block")) {
            clickElement(btn_BlockAccess);
            waitInSeconds(2000);
            clickElement(confirm_Yes);
            waitInSeconds(2000);
            enterTextIntoTextBox(textArea_ManagerComments, hmap.get("blockComments"));
            waitInSeconds(2000);
            clickElement(btn_OK);
            waitForAngularRequestToFinish();
            clickElement(btn_CloseUserScreen);
        } else {
            clickElement(btn_UnBlockAccess);
            waitInSeconds(2000);
            clickElement(confirm_Yes);
            waitInSeconds(2000);
            enterTextIntoTextBox(textArea_ManagerComments, hmap.get("blockComments"));
            waitInSeconds(2000);
            clickElement(btn_OK);
            waitForAngularRequestToFinish();
            clickElement(btn_CloseUserScreen);
        }
    }

    public void approveRejectBlock(DataTable table) {
        final Map<String, String> hmap = table.asMap(String.class, String.class);

        if (hmap.get("action").equalsIgnoreCase("approveBlock")) {
            scrollTillPageEnd(userPageDriver);
            clickElement_JS(btn_ApproveBlock);
            waitInSeconds(2000);
            enterTextIntoTextBox(textArea_ApproverComments, hmap.get("blockComments"));
            waitInSeconds(2000);
            clickElement(btn_Ok);
            waitForAngularRequestToFinish();
        } else {
            scrollTillPageEnd(userPageDriver);
            clickElement_JS(btn_ApproveUnBlock);
            waitInSeconds(2000);
            enterTextIntoTextBox(textArea_ApproverComments, hmap.get("blockComments"));
            waitInSeconds(2000);
            clickElement(btn_Ok);
            waitForAngularRequestToFinish();
        }
    }

    public void importUser(String msgPath) throws AWTException {
        msgPath = System.getProperty("user.dir") + msgPath;
        waitForAngularRequestToFinish();
        clickElement(btn_Next);

        String winHandleBefore = userPageDriver.getWindowHandle();
        clickElement(input_UploadClick);
        waitInSeconds(3000);
        fileUploadRobot(msgPath);
        waitForAngularRequestToFinish();

        userPageDriver.switchTo().window(winHandleBefore);
        scrollTillPageEnd(userPageDriver);
        waitInSeconds(3000);

        scrollToElementActions(userPageDriver, btn_NextUpload);
        clickElement_JS(btn_NextUpload);
        waitInSeconds(2000);
        clickElement(btn_Upload);
        waitForAngularRequestToFinish();
        waitInSeconds(5000);


    }

    public void searchUser(DataTable table, TestContext testContext) {
        final Map<String, String> map = table.asMap(String.class, String.class);
        waitForElementToBeLoaded(input_SearchUser);

        switch (map.get("searchCriteria")) {
            case "email":
                input_SearchUser.sendKeys(testContext.getUserMailID());
                break;
            case "phoneNo":
                input_SearchUser.sendKeys(testContext.getUserPhoneNo());
                break;
        }
        input_SearchUser.sendKeys(Keys.ENTER);
        waitInSeconds(3000);
        waitForAngularRequestToFinish();
        waitForElementToBeLoaded(btn_ActionUsers);
    }
}