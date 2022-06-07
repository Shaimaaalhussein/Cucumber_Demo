package apiPackage;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.github.tomakehurst.wiremock.WireMockServer;



public class MockApi {
	WireMockServer server = new WireMockServer();
	
	
	@BeforeTest
	protected void startServer() {
		 server = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort()
				 .extensions(new ResponseTemplateTransformer(false)));
		    server.start();
		    System.out.println("Start server");
	}
	public void stubGetAll() {	   
	server.stubFor(get(urlEqualTo("/location/get/all"))
	            .willReturn(aResponse()
	                .withHeader("Content-Type", "application/json")
	                .withStatus(200)
	                .withBodyFile("files/GetAll.json")));
		
	}
	 
	 public void stubGetByFileLocation() {
		   
		   server.stubFor(get(urlPathMatching("/location/get/([A-Z]*)"))
	                .willReturn(aResponse()
	    	                .withHeader("Content-Type", "application/json")
	    	                .withStatus(200)
	    	                .withBodyFile("files/Get{{request.pathSegments.[2]}}_location.json")
	    	                .withTransformers("response-template")));
		   
	}
	 public void stubGetByFileLocationNotExist() {
		   
		   server.stubFor(get(urlPathMatching("/location/get/([A-Z]*)"))
	                .willReturn(aResponse()
	    	                .withHeader("Content-Type", "application/json")
	    	                .withStatus(404)
	    	                .withBody("{{request.pathSegments.[2]}} Location not found")
	    	                .withTransformers("response-template")));
		   
	}
	public void stubPostLocation() throws FileNotFoundException, ParseException { 
	
		 File defaultRequestBodyFile=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\__files\\files\\PostDefaultBody.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(defaultRequestBodyFile));

		server.stubFor(post(urlMatching("/location/post"))
				.withRequestBody(equalToJson(obj.toString()))
			      .willReturn(aResponse()
    	                .withHeader("Content-Type", "application/json")
    	                .withStatus(201)
    	                .withBodyFile("files/PostNewLocation.json")
    	                .withTransformers("response-template")));
	
	}


	
	   @AfterTest
		protected void tearDown() {
			server.shutdown();
			 System.out.println("shutdown server");
		}
}
	
	

