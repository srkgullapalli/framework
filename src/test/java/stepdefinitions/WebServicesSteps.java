package stepdefinitions;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import enums.PageObjects;
import managers.PageObjectManager;
import pageobjects.LoginPage;

public class WebServicesSteps extends PageObjectManager {
	
	TestContext testContext;
	
	public WebServicesSteps(TestContext context) {
		testContext = context;
	}
	
	
	
} 