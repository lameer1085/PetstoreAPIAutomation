 package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payloads.userPayloads;
import io.restassured.response.Response;

public class userTestcase2 {
	
	Faker fake;
	userPayloads user;
	public Logger log;

	@BeforeClass
	public void setupData() {
		
		fake = new Faker();
		user= new userPayloads();
		
		user.setId(fake.idNumber().hashCode()); // idnumber give repeated vlue so we use hascode together with idNumer
		user.setUsername(fake.name().username());
		user.setFirstName(fake.name().firstName());
		user.setLastName(fake.name().lastName());
		user.setEmail(fake.internet().safeEmailAddress());
		user.setPassword(fake.internet().password(5,10));
		user.setPhone(fake.phoneNumber().cellPhone());
		
		log = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void createTestcase() {
		
		log.info("*************** creating user ****************");
		Response response = UserEndPoints2.createUser(user);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("*************** user is created ****************");

	}
	
	@Test(priority=2)
	public void getUserByName() {
		log.info("*************** read user ****************");
		Response response = UserEndPoints2.readUser(this.user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("*************** user value readed ****************");
	}
	
	@Test(priority=3)
	public void updateUserTestCase() {
		
		log.info("*************** updating user ****************");
		//update details
		user.setFirstName(fake.name().firstName());
		user.setLastName(fake.name().lastName());
		user.setEmail(fake.internet().safeEmailAddress());
		
		
		Response response = UserEndPoints2.updateUser(this.user.getUsername(), user);
				
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); // this is TestNG assertion
		response.then().log().body().statusCode(200);   // theis is also asserton, it is called chi assertion
		
		//check data after update
		Response responseAfterUpadte=UserEndPoints2.readUser(this.user.getUsername());
		responseAfterUpadte.then().log().body();
		responseAfterUpadte.then().log().body().statusCode(200);
		
		log.info("***************  user is updated ****************");
		
	}
	
	@Test(priority=4)
	public void deleteUserbyName() {
		
		log.info("*************** delete user ****************");
		Response response=UserEndPoints2.deleteUser(this.user.getUsername());
		response.then().log().body().statusCode(200);
		log.info("***************  user is deleted ****************");
	}
	
}
