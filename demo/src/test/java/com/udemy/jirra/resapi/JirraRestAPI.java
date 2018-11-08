package com.udemy.jirra.resapi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udemy.files.ReusableMethods;
import static org.hamcrest.Matchers.equalTo;

public class JirraRestAPI {
	
//	Properties prop = new Properties ();
//	
//	@BeforeTest
//	
//	public void getData() throws IOException {
//		
//		FileInputStream fis = new FileInputStream("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/environment.properties");
//		prop.load(fis);
//		 
//	}
//	
	
	@Test
	public void jirraApi() {
	
		RestAssured.baseURI= "https://hannna.atlassian.net"; 
		Response res = RestAssured.given().header("Content-Type","application/json").
		body("{ \"username\": \"hanna.qassurance@gmail.com\", \"password\": \"@Aaron2016\" }").when().post("/rest/auth/1/session").
		then().assertThat().statusCode(200).contentType(ContentType.JSON).and().extract().response(); 
		
		JsonPath json = ReusableMethods.extractJsRes(res); 
		
		String session=json.get("session.value");
		
		System.out.println(session);
	
	}
	

}
