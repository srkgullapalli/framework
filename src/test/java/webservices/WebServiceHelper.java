package webservices;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.TestContext;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Siva Rama Krishna Gullapalli
 */
public class WebServiceHelper {
    /**
     * This is method is used to trigger WebServices
     */
    private static Response response;
    private static String jsonString;

    public static String triggerWebService(String filePath, DataTable table, TestContext context, HashMap<String, Object> sessionMap) {
        final Map<String, String> inputMap = table.asMap(String.class, String.class);

        String httpMethod = inputMap.get("httpMethod") != null ? inputMap.get("httpMethod").trim() : "";
        String endPoint = inputMap.get("endPoint") != null ? inputMap.get("endPoint").trim() : "";

        String authToken = context.getAccessToken() != null ? context.getAccessToken().trim() : "";
        String restrictedAccess = inputMap.get("restrictedAccess") != null ? inputMap.get("restrictedAccess").trim() : "";
        String requestBody = new File(filePath).exists() ? generateStringFromRes(filePath) : "";

        String baseURL = "https://identity-test.hostcountry.qa";
        RestAssured.baseURI = baseURL;
        RequestSpecification request = RestAssured.given();

        request.headers("Content-Type", "application/json", "Authorization", authToken, "restrictAccess",
                restrictedAccess);
        int resourceID;
        switch (httpMethod) {
            case "Get":
                response = request.log().all().get(endPoint);
                break;
            case "Post":
                response = request.log().all().body(requestBody).post(endPoint);
                break;
            case "Delete":
                resourceID = inputMap.get("resourceID") != null ? Integer.parseInt(inputMap.get("resourceID")) : (int) sessionMap.get("resourceID");
                endPoint = endPoint + "/" + resourceID;
                response = request.log().all().body(requestBody).delete(endPoint);
                break;
            case "Put":
                resourceID = inputMap.get("resourceID") != null ? Integer.parseInt(inputMap.get("resourceID")) : (int) sessionMap.get("resourceID");
                endPoint = endPoint + "/" + resourceID;
                response = request.log().all().body(requestBody).put(endPoint);
                break;
        }
        int statusCode = response.getStatusCode();
        //Verify Response Status is 200
        Assert.assertEquals(200, statusCode);
        ResponseBody body = response.getBody();
        jsonString = body.asPrettyString();

        System.out.println("######################Completed WebService Cal#####################");
        return jsonString;

    }


    private static String generateStringFromRes(String path) {
        String requestBody = null;

        try {
            requestBody = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestBody;
    }


}
