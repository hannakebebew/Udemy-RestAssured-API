package com.udemy.api.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udemy.files.UdemyBody;
import udemy.files.UdemyResources;

import static io.restassured.RestAssured.given; 

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ApiDelete {
	/* end to end API functional testing
	 * 1. Post a resource, 
	 * 2. grab the place id 
	 * 3. delete the place id
	 * 4. check if the place id is not existed by trying to delete the place id again (can use a postman)
	 * 
	 * log().all()= it records as per the requirement.
	 */
	
	Properties prop = new Properties(); 
	
	@BeforeTest
	
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream ("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/environment.properties"); 
		prop.load(fis);
	}
	
	@Test 
	public void deletePlaceID() {
		
	RestAssured.baseURI = prop.getProperty("HOST"); 
	
	Response res = given().queryParam("key", "qaclick123").log().all().body(UdemyBody.postBody()).
			when().post(UdemyResources.addResource()).then().assertThat().statusCode(200).contentType(ContentType.JSON)
	.and().header("Server", "Apache").body("scope", equalTo("APP")).extract().response();
	
	String stringresponse = res.asString();
	
	JsonPath js = new JsonPath(stringresponse); 
	
	String placeId = js.get("place_id"); 
	
	System.out.println("THIS IS THE PLACE ID THAT WILL BE DELETED "+placeId);
	
	//4. delete the place id
	
	given().queryParam("key", "qaclick123").
	body("{"+ 
			"\"place_id\": \""+placeId+"\"" + 
			"}").when().post(UdemyResources.deleteResource()).then().assertThat().statusCode(200).contentType(ContentType.JSON).
	and().body("status", equalTo("OK"));
	
	System.out.println("This is the place when the item is not found:- "+ placeId);
		
	}

}
