package apiPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class ApiTest extends MockApi {
 
	String[] availLocations= {"LON","MAN","CAM","LCS"};
	String invalidLocation="GHS";
	@Test
	public void testStatusCodeGetAll() {
			
	stubGetAll();		
	Response R=	 RestAssured
			       .given()
			        .header("Content-type", "application/json")
                    .when()
                    .get("http://localhost:" + server.port() +"/location/get/all")
		            .then()
		            .assertThat()
		            .statusCode(200)
		            .extract()
		            .response();
	
	                System.out.println("Get all items\r\n "+R.getBody().asString());
	                JsonPath jsonPathEvaluator = R.jsonPath();
	                
	                //Assert location 
	                Assert.assertEquals( jsonPathEvaluator.getString("items[0].location").contains(availLocations[0]), true,"Response didn't have the specific location LAN");
	                Assert.assertEquals( jsonPathEvaluator.getString("items[1].location").contains(availLocations[1]), true,"Response didn't have the specific location MAN");
	                Assert.assertEquals( jsonPathEvaluator.getString("items[2].location").contains(availLocations[2]), true,"Response didn't have the specific location CAM");
	                Assert.assertEquals( jsonPathEvaluator.getString("items[3].location").contains(availLocations[3]), true,"Response didn't have the specific location LCS");
	               
	}
	
	@Test
	public void testStatusCodeGetByValidLocation() {
			
		String location=availLocations[0];
		
		stubGetByFileLocation();
	    
	    Response R=	 RestAssured
	    		           .given()
	    		           .header("Content-type", "application/json")
	    		           .when()
	    		           .get("http://localhost:" + server.port() +"/location/get/"+location)
	    		           .then()
	    		           .assertThat()
	    		           .statusCode(200)
	    		           .extract()
	    		           .response();
	    
	    System.out.println("Get items by location\r\n"+R.getBody().asString());
	    
	    //assert location
	    Assert.assertEquals(R.getBody().asString().contains(location), true,"Response didn't have the specific location "+location);
	
	
	}
	@Test
	public void testStatusCodeGetByInvalidLocation() {
			
		stubGetByFileLocationNotExist();
	    
	    
	    Response R=	RestAssured
	    		          .given()
	    		          .header("Content-type", "application/json")
	    		          .when()
	    		          .get("http://localhost:" + server.port() +"/location/get/"+invalidLocation)
	    		          .then()
	    		          .assertThat()
	    		          .statusCode(404)
			              .extract()
			              .response();
	    
	    System.out.println("Get location not exist\r\n"+R.getBody().asString());
	    
	    //Assert status code
	    Assert.assertEquals(R.getStatusCode(),404,"Wrong status code for Response didn't have the specific location "+invalidLocation);
	
	}
	
	@Test
	public void testStatusCodePostLocation() throws FileNotFoundException, ParseException {
			
		stubPostLocation();
		
	    //read request body		
        File requestBody=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\__files\\files\\PostRequestBody.json");      
        
        //Get location from request body 
       JSONParser parser = new JSONParser();
       Object obj = parser.parse(new FileReader(requestBody));
       JSONObject jsonObject = (JSONObject) obj;
       String location = jsonObject.getAsString("location");
      
      //send request
      Response R=RestAssured
			        .given()
			        .header("Content-type", "application/json")
                    .when().body(requestBody)
	 		        .post("http://localhost:" + server.port() +"/location/post")
		            .then()
			        .assertThat()
			        .statusCode(201)
			        .extract()
			        .response();
      
	  System.out.println("Add new location\r\n"+R.getBody().asString());
	        
	        //assert on location added
	        Assert.assertEquals(R.getBody().asString().contains(location), true,"Location didn't add "+location);
	}
	
}
