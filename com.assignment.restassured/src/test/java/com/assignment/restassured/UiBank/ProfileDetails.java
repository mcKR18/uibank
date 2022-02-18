package com.assignment.restassured.UiBank;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProfileDetails extends RestassuredBase {
	
	@Test(dependsOnMethods = {"com.assignment.restassured.UiBank.LoginUiBank.login"})
	public void getProfileDetails() {
		//baseURi 
		RestAssured.baseURI="https://uibank-api.azurewebsites.net/api/";
		
		//to get account details 
		Response response = RestAssured.
							given()
								.header("authorization", id)
								.pathParam("userid", userId)
								.log().all()
							.when()
								.get("users/{userid}");
		
		// to print reponse in beatifully aligned format
		response.prettyPrint();
		
		JsonPath jsonPath = response.jsonPath();
		assertEquals(jsonPath.getString("firstName"), "monisha");
		//validate status code
		Assert.assertEquals(response.statusCode(), 200);
			
		
	}

}
