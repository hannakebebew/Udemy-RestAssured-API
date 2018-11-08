package com.udemy.api.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udemy.files.UdemyBody;
import udemy.files.UdemyResources;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiPostandExtract {
	
	/*
	 *  How extract response? get a response as a raw format, then has to be changed in to string format
	 *  then from string format it will be changed in to a jason format with the help of JsonPath
	 *  then simply call the "place-id"
	 */
	
	Properties prop = new Properties(); 
	
	@BeforeTest
	
	public void getData() throws IOException {
	
	FileInputStream fis = new FileInputStream("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/environment.properties"); 
	prop.load(fis);
	
	}
	@Test
	
	public void postApi() {
		
		//1. get the baseurl
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		
		
		//2. Post and extract response
		Response res = given().queryParam("key", "qaclick123").body(UdemyBody.postBody()).
		when().post(UdemyResources.addResource()).
		then().assertThat().statusCode(200).contentType(ContentType.JSON).and().
		header("Server", "Apache").and().body("scope", equalTo("APP")).extract().response(); 
		
		String stringresponse = res.asString(); 
		
		System.out.println(stringresponse);
		
		//3. get a place-id
		
		JsonPath js= new JsonPath(stringresponse); 
		
		String placeid = js.get("place_id");
		System.out.println("The extracted place_id is:- "+placeid);
		
	}

}
