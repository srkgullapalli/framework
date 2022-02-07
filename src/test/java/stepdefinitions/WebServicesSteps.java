package stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.TestContext;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static pageobjects.WebServiceValidationPage.validateAPIResponse;
import static webservices.WebServiceHelper.triggerWebService;

public class WebServicesSteps {
    TestContext testContext;
    public static HashMap<String,Object> sessionMap = new HashMap<>();

    public WebServicesSteps(TestContext context) {
        testContext = context;
    }

    @Given("^Generate the Auth Token with following request \"([^\"]*)\"$")
    public void generateAuthToken(String msgPath, DataTable table) {
        testContext.setApiResponse(triggerWebService(msgPath, table, testContext,sessionMap));
        JSONObject obj = new JSONObject(testContext.getApiResponse());
        testContext.setAccessToken(obj.getJSONObject("msg").getString("access_token"));
    }

    @Given("^Trigger the API with authToken and request-body \"([^\"]*)\"$")
    public void triggerAPI(String msgPath, DataTable table) {
        testContext.setApiResponse(triggerWebService(msgPath, table, testContext, sessionMap));
    }

    @Given("^Delete The Resource$")
    public void deleteResource(DataTable table) {
        String msgPath ="";
        testContext.setApiResponse(triggerWebService(msgPath, table, testContext, sessionMap));
    }

    @Then("^Verify API Response for the following service$")
    public void verifyAPIResponseForTheFollowingService(DataTable table) {
        validateAPIResponse(table, testContext,sessionMap);
    }
}