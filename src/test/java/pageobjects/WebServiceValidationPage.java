package pageobjects;

import io.cucumber.datatable.DataTable;
import model.TestContext;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class WebServiceValidationPage {

    public static void validateAPIResponse(DataTable table, TestContext testContext, HashMap<String, Object> sessionMap) {
        final Map<String, String> inputMap = table.asMap(String.class, String.class);
        String jsonResponse = testContext.getApiResponse();

        switch(inputMap.get("service")){
            case"GetAllEvents":
                validateGetAllEvents(jsonResponse);
                break;
            case "PostingEvent":
                validatePostAndUpdateEvents(jsonResponse,sessionMap);
                break;
            case "Update Event":
                validatePostAndUpdateEvents(jsonResponse,sessionMap);
                break;
            case "DeleteEvent":
                validateDeleteEvent(jsonResponse);
                break;
            case "GetAllServices":
                validateGetAllServices(jsonResponse);
                break;
            case "PostService":
                validatePostService(jsonResponse);
                break;

        }
    }

    private static void validatePostService(String jsonResponse) {
        JSONObject obj = new JSONObject(jsonResponse);
        //Verify Status is OK
        Assert.assertEquals("ok",obj.getString("status"));
        JSONObject msgBody= obj.getJSONObject("msg");
        //Verify message body is not null
        Assert.assertNotNull(msgBody,"Message Body is not Null");
        String status = msgBody.getString("status");
        Assert.assertEquals("Valid",msgBody.getString("status"));
    }

    private static void validateGetAllServices(String jsonResponse) {
        JSONObject obj = new JSONObject(jsonResponse);
        //Verify Status is OK
        Assert.assertEquals("ok",obj.getString("status"));

        JSONArray eventsArray = obj.getJSONObject("msg").getJSONArray("data");
        //Verify Data array is not null
        Assert.assertNotNull(eventsArray,"No data retruned from API");
        //Verify Current Events Count equals no of objects in Data Array
        int currentEvents = obj.getJSONObject("msg").getInt("current");
        Assert.assertEquals(currentEvents, eventsArray.length());
    }



    private static void validateDeleteEvent(String jsonResponse) {
        JSONObject obj = new JSONObject(jsonResponse);
        //Verify Status is OK and message is deleted
        Assert.assertEquals("ok",obj.getString("status"));
        Assert.assertEquals("Deleted",obj.getString("msg"));
    }

    private static void validatePostAndUpdateEvents(String response, HashMap<String, Object> sessionVariable) {
        JSONObject obj = new JSONObject(response);
        //Verify Status is OK
        Assert.assertEquals("ok",obj.getString("status"));
        JSONObject msgBody= obj.getJSONObject("msg");
        //Verify message body is not null
        Assert.assertNotNull(msgBody,"Message Body is not Null");
        String masStatus = msgBody.getString("masStatus");
        Assert.assertEquals("Success",msgBody.getString("masStatus"));
        //Verify EventID is created for the Input
        int eventID = Integer.parseInt(msgBody.getString("eventId"));
        Assert.assertNotNull(eventID,"Message Body is not Null");
        sessionVariable.put("resourceID",eventID);


    }

    private static void validateGetAllEvents(String response) {
        JSONObject obj = new JSONObject(response);
        //Verify Status is OK
        Assert.assertEquals("ok",obj.getString("status"));
        //Verify Current Events Count equals no of objects in Data Array
        int currentEvents = obj.getJSONObject("msg").getInt("current");
        JSONArray eventsArray = obj.getJSONObject("msg").getJSONArray("data");
        Assert.assertEquals(currentEvents, eventsArray.length());
        //Verify Data array is not null
        Assert.assertNotNull(eventsArray,"No data retruned from API");
    }
}
