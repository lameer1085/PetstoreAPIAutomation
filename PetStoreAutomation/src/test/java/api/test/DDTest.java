package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;



import api.endpoints.UserEndPoints;
import api.payloads.userPayloads;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {
	
	
	@Test(priority=1,enabled=true, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void tetsPostUser(String UserID, String userName,String fName,String lName, String userMail, String password,String phoneNumber) {
		
		userPayloads payload = new userPayloads();
		payload.setId(Integer.parseInt(UserID));
		payload.setUsername(userName);
		payload.setFirstName(fName);
		payload.setLastName(lName);
		payload.setEmail(userMail); 
		payload.setPassword(password);
		payload.setPhone(phoneNumber);
		
		Response response=UserEndPoints.createUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserName", dataProviderClass=DataProviders.class)
	public void tetsDeletUser(String userName) {
		
		
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
