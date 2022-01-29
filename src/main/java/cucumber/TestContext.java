package cucumber;

public class TestContext {

	private String apiResponse;
	private String accessToken;

	
	/**
	 * @return the accessToken
	 */
	public String getaccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setaccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the apiResponse
	 */
	public String getApiResponse() {
		return apiResponse;
	}

	/**
	 * @param apiResponse the apiResponse to set
	 */
	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}

}
