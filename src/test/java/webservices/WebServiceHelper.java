/**
 * 
 */
package webservices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Assert;

import cucumber.TestContext;
import cucumber.api.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

/**
 * @author Siva Rama Krishna Gullapalli
 *
 */
public class WebServiceHelper {

	/**
	 * This is method is used to trigger WebServices Either SOAP or REST
	 */
	private static final String baseURL = "https://identity-test.hostcountry.qa";
	private static Response response;
	private static String jsonString;

	public static String triggerWebService(String filePath, DataTable table, TestContext context) {
		final Map<String, String> inputMap = table.asMap(String.class, String.class);

		String httpMethod = inputMap.get("httpMethod") != null ? inputMap.get("httpMethod").trim() : "";
		String endPoint = inputMap.get("endPoint") != null ? inputMap.get("endPoint").trim() : "";

		String authToken = context.getaccessToken() != null ? context.getaccessToken().trim() : "";
		String restrictedAccess = inputMap.get("restrictedAccess") != null ? inputMap.get("restrictedAccess").trim()
				: "";
		String requestBody = new File(filePath).exists() ? generateStringFromRes(filePath) : "";

		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();

		request.headers("Content-Type", "application/json", "Authorization", authToken, "restrictAccess",
				restrictedAccess);

		switch (httpMethod) {
		case "Get":
			response = request.log().all().get(endPoint);
			break;
		case "Post":
			response = request.log().all().body(requestBody).post(endPoint);
			break;
		case "Delete":
			response = request.log().all().body(requestBody).delete(endPoint);
			break;
		case "Put":
			response = request.log().all().body(requestBody).put(endPoint);
			break;
		}

		@SuppressWarnings("rawtypes")
		ResponseBody body = response.getBody();
		jsonString = body.asPrettyString();
		System.out.println("Service Response is====>" + jsonString);

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
