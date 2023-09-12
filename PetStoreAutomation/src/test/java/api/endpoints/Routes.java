package api.endpoints;

/*
 swagger URL	--- https://petstore.swagger.io/
 
 create user (POST)   ---	https://petstore.swagger.io/v2/user
 Get user (GET)		  --- 	https://petstore.swagger.io/v2/user/{username}
 update user (PUT)	  --- 	https://petstore.swagger.io/v2/user/{username}
 delete user (DELETE)	  --- 	https://petstore.swagger.io/v2/user/{username}
 */


public class Routes {
		// Route class only has URL 
	
	public static String BASE_URL = "https://petstore.swagger.io/v2";
	
	//User module
	public static String Post_URL = BASE_URL+"/user";
	public static String Get_URL = BASE_URL+"/user/{username}";
	public static String Update_URL = BASE_URL+"/user/{username}";
	public static String Delete_URL = BASE_URL+"/user/{username}";
	
	//Store module
	
		//here we have store module URL
	
	
	//Pet module
	
			//here we have pet module URL
}
