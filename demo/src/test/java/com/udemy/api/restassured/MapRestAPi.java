package com.udemy.api.restassured;


import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MapRestAPi {
	/*archtecture of restApi is
	 * RestAssured.URI ="base url"
	 * given.().. can be parameter, cookie, or 
	 * when()... methods..get("resource")
	 * then()... assert.. status code, contentType, body..
	 * we can also have extract(); 
	
	*/
	Properties prop = new Properties(); 
	
	@BeforeTest
	public void getUrl() throws IOException {
		FileInputStream fis = new FileInputStream("/Users/hannakebebew/git/repository2/demo/src/test/java/udemy/files/environment.properties");
		prop.load(fis); 
		
	}

	@Test 
	
	public void openWeatherMap() {
		
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res= RestAssured.given().
					param("id", "524901").
					param("lang", "zh_cn").
					param("appid", "b1b15e88fa797225412429c1c50c122a1").
		   when().get("/data/2.5/forecast/daily").
		   then().assertThat().statusCode(200).contentType(ContentType.JSON).
		   and().body("city.country", equalTo("RU")).header("server", "openresty/1.9.7.1").extract().response();
		
		String resp = res.toString();
		JsonPath js= new JsonPath (resp);
		
		System.out.println("Output is "+ resp);
	}

}
