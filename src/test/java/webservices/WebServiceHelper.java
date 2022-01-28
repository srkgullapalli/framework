/**
 * 
 */
package webservices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import cucumber.api.DataTable;

/**
 * @author Siva Rama Krishna Gullapalli
 *
 */
public class WebServiceHelper {

	/**
	 * This is method is used to trigger WebServices Either SOAP or REST
	 */
	public static String triggerWebService(String msgBody, DataTable table) {
		String serviceResponse = null;

		final Map<String, String> inputParamMap = table.asMap(String.class, String.class);

		String serviceProtocol = inputParamMap.get("Service Protocol").trim();
		String endPoint = inputParamMap.get("EndPoint").trim();
		String contentType = inputParamMap.get("Content Type").trim();
		String httpMethod = inputParamMap.get("HTTP Method").trim();
		String requestBody = generateStringFromRes(msgBody);

		// Set headers for request
		HashMap<String, String> headerMap = new HashMap<String, String>();
		if (serviceProtocol.equalsIgnoreCase("SOAP")) {
			String soapAction = inputParamMap.get("SOAP Action").trim();
			headerMap.put("Content-Type", contentType);
			headerMap.put("SOAPAction", soapAction);
		} else {
			headerMap.put("Content-Type", contentType);
		}
		switch (httpMethod) {
		case "Get":
			serviceResponse = given().log().all().headers(headerMap).body(requestBody).get(endPoint).andReturn()
					.asString();
			break;
		case "Post":
			serviceResponse = given().log().all().headers(headerMap).body(requestBody).post(endPoint).andReturn()
					.asString();
			break;
		case "Delete":
			serviceResponse = given().log().all().headers(headerMap).body(requestBody).delete(endPoint).andReturn()
					.asString();
			break;
		case "Put":
			serviceResponse = given().log().all().headers(headerMap).body(requestBody).put(endPoint).andReturn()
					.asString();
			break;
		}
		return serviceResponse;
	}

	private static String generateStringFromRes(String path) {
		String requestBody = null;

		try {
			requestBody = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return requestBody;
	}

}
