package stepdefinitions;

import model.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import enums.PageObjects;
import managers.PageObjectManager;
import pageobjects.UserPage;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class UserSteps extends PageObjectManager {
	
	TestContext testContext;
	
	public UserSteps(TestContext context) {
		testContext = context;
	}
	
	@Given("^Create new user with the below fields$")
	public void selectIDAMService() throws AWTException {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).createUsers(testContext);
	}

	@Then("Inject data into testContext")
	public void injectDataIntoTestContext(DataTable table) {
		final Map<String, String> hmap = table.asMap(String.class, String.class);
		if(hmap.get("phoneNo")!=null) {
			testContext.setUserPhoneNo(hmap.get("phoneNo"));
		}
		else if(hmap.get("userEmail")!=null){
			testContext.setUserMailID(hmap.get("userEmail"));
		}
	}

	@Then("navigate to view details screen for the user")
	public void navigateToViewDetaisScreenForTheUser() {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).navigateViewDetails(testContext);
	}

	@Then("Approve or Reject RequestAccess for the user")
	public void approveOrRejectRequestAccessForTheUser() {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).approveRejectUserAccess();

	}

	@Then("Verify Approval Status in User Details Screen")
	public void verifyApprovalStatusInUserDetailsScreen(DataTable table) {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).verifyRoleApprovalStatus(testContext,table);
	}

	@Then("Perform Block and UnBlock Access for user")
	public void performBlockAndUnBlockAccessForUser(DataTable table) {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).blockUnBlockUser(table);

	}

	@Then("Approve or Reject Block Access For User")
	public void approveOrRejectBlockAccessForUser(DataTable table) {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).approveRejectBlock(table);
	}

    @Then("Perform User import using the file {string}")
    public void performUserImportUsingTheFile(String msgPath) throws IOException, AWTException {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).importUser(msgPath);
    }

	@Then("perform search for user in Users screen with the following")
	public void performSearchForUserInUsersScreenWithTheFollowing(DataTable table) {
		((UserPage)getDynamicPageObj(PageObjects.UserPage.toString())).searchUser(table,testContext);
	}
}