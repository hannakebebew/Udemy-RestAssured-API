package com.udemy.api.restassured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udemy.files.ReusableMethods;

import static io.restassured.RestAssured.given;  
import static org.hamcrest.Matchers.equalTo;

public class GetWeatherJson {
	
	
@Test	
	public void getWeather() {
	
	RestAssured.baseURI = "https://samples.openweathermap.org";
	Response res= given().param("id", "524901").param("lang", "zh_cn").param("appid","b1b15e88fa797225412429c1c50c122a1").
	when().get("/data/2.5/forecast/daily").
	then().assertThat().contentType(ContentType.JSON).statusCode(200).
	and().body("city.name", equalTo("Moscow")).and().header("Connection", "keep-alive").
	extract().response();
	JsonPath js = ReusableMethods.extractJsRes(res); 

int count = js.get("list.size()"); 
for(int i=0;i<count;i++) {
	System.out.println("The output is: "+js.get("list["+i+"].dt"));
	
	
}
	
	}

}
