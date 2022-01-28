package cucumber;

public class TestContext {
	
	private static String apiResponse;

	/**
	 * @return the apiResponse
	 */
	public static String getApiResponse() {
		return apiResponse;
	}

	/**
	 * @param apiResponse the apiResponse to set
	 */
	public static void setApiResponse(String apiResponse) {
		TestContext.apiResponse = apiResponse;
	}	

}
