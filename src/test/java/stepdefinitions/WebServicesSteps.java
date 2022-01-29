package stepdefinitions;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import static webservices.WebServiceHelper.*;
import org.json.JSONObject;

public class WebServicesSteps {

	TestContext testContext;

	public WebServicesSteps(TestContext context) {
		testContext = context;
	}

	@Given("^Generate the Auth Token with following request \"([^\"]*)\"$")
	public void generateAuthToken(String msgPath, DataTable table) {
		testContext.setApiResponse(triggerWebService(msgPath, table,testContext));

		JSONObject obj = new JSONObject(testContext.getApiResponse());
		testContext.setaccessToken(obj.getJSONObject("msg").getString("access_token"));
	}
	
	@Given("^Trigger the API with authToken and requestbody \"([^\"]*)\"$")
	 public void triggerAPI(String msgPath,DataTable table) {
		testContext.setApiResponse(triggerWebService(msgPath, table,testContext));		
	}
}