package com.udemy.jirra.resapi;

import java.io.File;
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
import udemy.files.UdemyBody;
import udemy.files.UdemyResources;


public class UdemyJirraEndtoEnd {
	
	Properties prop = new Properties(); 
	
	@BeforeTest
	
	public void setEnvironment() throws Exception {
		
		File file = new File("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/environment.properties"); 
		
		FileInputStream fis = new FileInputStream(file); 
		
		prop.load(fis);
		
	}
	
	@Test (priority=0)
	
	public void createSession() {
		
		
		RestAssured.baseURI = prop.getProperty("JiraHost"); 
		
		Response res = RestAssured.given().header("Content-Type","application/json").
		body("{ \"username\": \"hanna.qassurance@gmail.com\", \"password\": \"@Aaron2016\" }").
		when().post(UdemyResources.sessionPut()).then().assertThat().statusCode(200).contentType(ContentType.JSON).and().extract().response();
		
		JsonPath js = ReusableMethods.extractJsRes(res); 
				
		String cookies = js.getString("session.value"); 
		
		System.out.println(cookies);
	}
	
	@Test (priority=1)
	public void createIssue() {
		
		RestAssured.baseURI= prop.getProperty("JiraHost");
		
		Response res1=RestAssured.given().header("Content-Type","application/json").
		header("Cookie", prop.getProperty("Cookie")).body(UdemyBody.createIssue()).when().post("/rest/api/2/issue").
		then().assertThat().statusCode(201).and().extract().response();
		
		JsonPath js = ReusableMethods.extractJsRes(res1);
		
		// response is : "id": "10006", and "key": "RES-7",
		
		String Epic = js.getString("id"); 
		
		System.out.println("The extracted output is :"+ Epic);
	
	}
	
	@Test(priority=2)
	
	public void addComment() {
		
		RestAssured.baseURI=prop.getProperty("JiraHost");
		
		RestAssured.given().queryParam("id", "10006").body(UdemyBody.addComment()).when().post("/rest/api/2/issue/10006/comment").
		then().statusCode(200).contentType(ContentType.JSON); 
	}
	
	@Test(priority=3)
	
	public void getIssue() {
		
		RestAssured.baseURI= prop.getProperty("JiraHost");
		
		RestAssured.given().header("Cookie", prop.getProperty("Cookie")).
		when().get("/rest/api/2/issue/10006").
		then().assertThat().statusCode(200).contentType(ContentType.JSON);
		
				
	}
	
	@Test (priority=4)
	public void deleteIssue() {
		
		RestAssured.baseURI=prop.getProperty("JiraHost");
		
		RestAssured.given().when().post("/rest/api/2/issue/10006/comment").then().assertThat().statusCode(204);

		
	}

}
