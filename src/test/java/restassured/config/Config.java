package restassured.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class Config {

	public static final String BASE_URL = "http://localhost:8080";

	static {
		RestAssured.baseURI = BASE_URL;
		RestAssured.requestSpecification = new RequestSpecBuilder()
			    .addHeader("Content-Type", "application/json")
			    .build();
	}
}
