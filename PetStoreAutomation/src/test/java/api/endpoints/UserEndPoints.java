package api.endpoints;

// This class created for perform CRUD(create, read, update, delete) request in users API

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.userPayloads; // user POJO class
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(userPayloads Payload) {
		
		Response response=
		given()	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		.when()
			.post(Routes.Post_URL);
		
		return response;
	}
	
	public static Response readUser(String username) {
		
		Response response=
		given()	
			.pathParam("username",username)
		.when()
			.get(Routes.Get_URL);
		
		return response;
	}
	
	public static Response updateUser(String username, userPayloads Payload) {
		
		Response response=
		given()	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(Payload)
		.when()
			.put(Routes.Update_URL);
		
		return response;
	}
	
	public static Response deleteUser(String username) {
		
		Response response=
		given()	
			.pathParam("username",username)

		.when()
			.get(Routes.Delete_URL);
		
		return response;
	}
}
