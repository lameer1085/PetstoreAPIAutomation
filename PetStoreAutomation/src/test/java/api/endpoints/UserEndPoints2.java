package api.endpoints;

// This class created for perform CRUD(create, read, update, delete) request in users API

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.userPayloads; // user POJO class
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURLFromPropertiesFile(){
		//resource bundle is java method for handle resource 
		ResourceBundle resource = ResourceBundle.getBundle("routes"); //this will load the properties file we just give the properties file name there
		return resource;
	}	
	
	public static Response createUser(userPayloads Payload) {
		
		String post_url=getURLFromPropertiesFile().getString("Post_URL");
		
		Response response=
		given()	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		.when()
			.post(post_url);
		
		return response;
	}
	
	public static Response readUser(String username) {
		
		String get_url=getURLFromPropertiesFile().getString("Get_URL");
		
		Response response=
		given()	
			.pathParam("username",username)
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String username, userPayloads Payload) {
		
		String update_url=getURLFromPropertiesFile().getString("Update_URL");
		
		Response response=
		given()	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(Payload)
		.when()
			.put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String username) {
		
		String delete_url=getURLFromPropertiesFile().getString("Delete_URL");
		
		Response response=
		given()	
			.pathParam("username",username)

		.when()
			.get(delete_url);
		
		return response;
	}
}
