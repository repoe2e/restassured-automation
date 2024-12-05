package restassured.utils;

import io.restassured.response.Response;

public class ResponseValidator {

	 public static void validateStatusCode(Response response, int expectedStatusCode) {
	        response.then().statusCode(expectedStatusCode);
	    }

	    public static void validateBodyContains(Response response, String expectedContent) {
	        response.then().body(org.hamcrest.Matchers.containsString(expectedContent));
	    }
	
	
}
