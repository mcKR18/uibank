package com.assignment.restassured.UiBank;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoginUiBank extends RestassuredBase{
	
	@Test
	public void login() {
		
		//baseURi 
				RestAssured.baseURI="https://uibank-api.azurewebsites.net/api/";
				
		//login with credentials and save response in id of RestassuredBase class
		Response response = RestAssured.
							given()
								.contentType(ContentType.JSON)
								.body("{\r\n"
										+ " \"username\": \"monisha.divi@gmail.com\",\r\n"
										+ " \"password\": \"monisha10\"\r\n"
										+ " }")
								.log().all()
							.when()
							  .post("users/login");
		
		//parsing json response to get id
		JsonPath jsonPath = response.jsonPath();
		id = jsonPath.getString("id");
		userId = jsonPath.getString("userId");
		
		//printing id from response
		System.err.println(id);
		
		response.prettyPrint();
		
		//validate status code
		Assert.assertEquals(response.statusCode(), 200);
		
		
	}

}
