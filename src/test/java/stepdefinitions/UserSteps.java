package stepdefinitions;

import model.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import enums.PageObjects;
import managers.PageObjectManager;
import pageobjects.UserPage;

import java.awt.*;

public class UserSteps extends PageObjectManager {
	
	TestContext testContext;
	
	public UserSteps(TestContext context) {
		testContext = context;
	}
	
	@Given("^Create new user with the below fields$")
	public void selectIDAMService() throws AWTException {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).createUsers();
	}
} 