package stepdefinitions;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import enums.PageObjects;
import managers.PageObjectManager;
import pageobjects.DashboardPage;

public class DashboardPageSteps extends PageObjectManager {
	
	TestContext testContext;
	
	public DashboardPageSteps(TestContext context) {
		testContext = context;
	}
	
	@Given("^Select the service from the below menu options$")
	public void createUser(DataTable table){
		((DashboardPage)getDynamicPageObj(PageObjects.DashboardPage.toString())).selectService(table);
	}
} 