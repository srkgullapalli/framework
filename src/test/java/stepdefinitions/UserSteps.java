package stepdefinitions;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import enums.PageObjects;
import managers.PageObjectManager;
import pageobjects.UserPage;

public class UserSteps extends PageObjectManager {
	
	TestContext testContext;
	
	public UserSteps(TestContext context) {
		testContext = context;
	}
	
	@Given("^Create new user with the below fields$")
	public void selectIDAMService(DataTable table){
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).createUsers(table);
	}
} 