package stepdefinitions;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import managers.PageObjectManager;
import static webservices.WebServiceHelper.*;

public class WebServicesSteps {
	
	TestContext testContext;
	
	public WebServicesSteps(TestContext context) {
		testContext = context;
	}
	
	@Given("^Generate the Auth Token with following request \"([^\"]*)\"$")
		public void generateAuthToken(String msgPath,DataTable table ) {
		String res = triggerWebService(msgPath,table);
		TestContext.setApiResponse(res);
		System.out.print("API Response"+TestContext.getApiResponse());
	}
} 