package stepdefinitions;

import model.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
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

	@Then("Select sub service for main service selection")
	public void selectSubServiceForMainServiceSelection(DataTable table) {
		((DashboardPage)getDynamicPageObj(PageObjects.DashboardPage.toString())).selectSubService(table);
	}
}