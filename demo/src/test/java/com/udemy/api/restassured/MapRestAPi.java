package com.udemy.api.restassured;

import org.junit.Test;

import io.restassured.RestAssured;

public class MapRestAPi {
	
	@Test
	
	public void openWeatherMap() {
		
		RestAssured.baseURI ="https://samples.openweathermap.org";
		
		RestAssured.given().
					param("id", "524901").
					param("lang", "zh_cn").
					param("appid", "b1b15e88fa797225412429c1c50c122a1").
		   when().get("/data/2.5/forecast/daily").then().assertThat().statusCode(200); 
		
	}

}
