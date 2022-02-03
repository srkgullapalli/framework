package pageobjects;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static managers.DriverManager.*;
import static selenium.SeleniumHelper.*;
import cucumber.api.DataTable;

public class DashboardPage {
	private WebDriver dashboardPageDriver;
	private String currentScreenName;
	private String envName;

	public DashboardPage() {
		currentScreenName = this.getClass().getName();
		this.dashboardPageDriver = getDriver();
		PageFactory.initElements(dashboardPageDriver, this);
	}

	@FindBy(xpath = "//span[contains(.,'IDAM')]")
	private WebElement label_IDAM;

	@FindBy(xpath = "//span[text()='Users']")
	private WebElement label_Users;

	
	public void selectService(DataTable table) {
		final Map<String, String> input = table.asMap(String.class, String.class);
		waitForAngularRequestToFinish();

		switch (input.get("service")) {
		case "IDAM":
			clickElement(label_IDAM);
			selectIdamSubMenu(table);
			break;
		case "TicketEnablement":
			break;
		case "MassAccess":
			break;
		case "SpecialNeeds":
			break;
		}
	}

	private void selectIdamSubMenu(DataTable table) {
		final Map<String, String> input = table.asMap(String.class, String.class);
		waitForAngularRequestToFinish();

		switch (input.get("subService")) {
		case "Users":
			clickElement(label_Users);
			break;
		}
	}
}