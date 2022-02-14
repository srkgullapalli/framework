package pageobjects;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static managers.DriverManager.getDriver;
import static selenium.SeleniumHelper.clickElement;
import static selenium.SeleniumHelper.waitForAngularRequestToFinish;

public class DashboardPage {
    private WebDriver dashboardPageDriver;
    private String currentScreenName;
    private String envName;

    @FindBy(xpath = "//span[contains(.,'IDAM')]")
    private WebElement label_IDAM;

    @FindBy(xpath = "//span[text()='Users']")
    private WebElement label_Users;

	@FindBy(xpath = "//span[text()='Pending Approvals']")
	private WebElement label_PendingApprovals;

    public DashboardPage() {
        currentScreenName = this.getClass().getName();
        this.dashboardPageDriver = getDriver();
        PageFactory.initElements(dashboardPageDriver, this);
    }

    public void selectService(DataTable table) {
        final Map<String, String> input = table.asMap(String.class, String.class);
        waitForAngularRequestToFinish();
        switch (input.get("service")) {
            case "IDAM":
                clickElement(label_IDAM);
                break;
            case "TicketEnablement":
                break;
            case "MassAccess":
                break;
            case "SpecialNeeds":
                break;
        }
    }

    public void selectSubService(DataTable table) {
        final Map<String, String> input = table.asMap(String.class, String.class);
        switch (input.get("subService")) {
            case "Users":
                clickElement(label_Users);
                break;
            case "Pending Approvals":
                clickElement(label_PendingApprovals);
                break;
        }
    }
}