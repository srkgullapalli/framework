package stepdefinitions;

import model.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import enums.PageObjects;
import managers.PageObjectManager;
import pageobjects.LoginPage;

public class LoginPageSteps extends PageObjectManager {
	
	TestContext testContext;
	
	public LoginPageSteps(TestContext context) {
		testContext = context;
	}
	
	@Given("^Launch Application with URL$")
	public void launchBrowser(DataTable table) throws Exception{
		((LoginPage)getDynamicPageObj(PageObjects.LoginPage.toString())).launchApp(table);
	}
	
	@Given("^User is in LoginPage and performs login with below fields$")
	public void login(DataTable table) throws Exception{
		((LoginPage)getDynamicPageObj(PageObjects.LoginPage.toString())).appLogin(table);
	}
	@Given("^Logout of the Application$")
	public void logout() throws Exception{
		((LoginPage)getDynamicPageObj(PageObjects.LoginPage.toString())).appLogout();
	}
} 