//package com.udemy.api.restassured;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.Properties;
//import java.lang.Object;
//
//import org.junit.Test;
//import org.testng.annotations.BeforeTest;
//
//import com.google.common.io.Files;
//import com.jayway.jsonpath.internal.Path;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.path.xml.XmlPath;
//import io.restassured.response.Response;
//import udemy.files.ReusableMethods;
//
//public class ApiXMLPost {
//	
//	Properties prop = new Properties(); 
//	
//	@BeforeTest
//	
//	public void getData() throws IOException {
//		FileInputStream fis = new FileInputStream("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/environment.properties"); 
//		prop.load(fis);
//	}
//	
//	@Test
//	
//	public void addXMLApi() throws IOException {
//		
//		String postData=GenerateStringFromResouce("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/API.xml"); 
//		RestAssured.baseURI = prop.getProperty("HOST"); 
//		
//		Response res = RestAssured.given().queryParam("key", prop.getProperty("key")).body(postData).
//				when().post("/maps/api/place/add/xml").
//		then().assertThat().statusCode(200).contentType(ContentType.XML).extract().response(); 
//		
//		XmlPath x = ReusableMethods.extractXRes(res);
//		
//		String bod= x.get("response.reference"); 
//		System.out.println(bod);
//		
//	}
//	 
//	public static String GenerateStringFromResouce(String path1) throws IOException {
//		return new String(Files.readAllByes(Paths.get(path1))); 
//	}
//
//}
