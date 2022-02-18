package com.assignment.restassured.UiBank;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateAccountSavings extends RestassuredBase{

	
	@Test(dependsOnMethods = {"com.assignment.restassured.UiBank.LoginUiBank.login"})
	public void createAccount() {
		String friendlyName = "AwesomeAccount";
		//baseURi 
		RestAssured.baseURI="https://uibank-api.azurewebsites.net/api/";
		
		//to create savings account type pass userId and  FriendlyName concat in body
		Response response = RestAssured.
							given()
								.header("authorization", id)
								.contentType(ContentType.JSON)
								.body("{\r\n"
										+ "\"accountNumber\": 70725454,\r\n"
										+ "\"balance\": 100,\r\n"
										+ "\"friendlyName\": \""+friendlyName+"\",\r\n"
										+ "\"type\": \"savings\",\r\n"
										+ "\"userId\": \""+userId+"\"\r\n"
										+ "}")
								.log().all()
							.when()
								.post("accounts");
		
		// to print reponse in beatifully aligned format
		response.prettyPrint();
		
		JsonPath jsonPath = response.jsonPath();
		assertEquals(jsonPath.getString("friendlyName"), friendlyName);
			
		//validate status code
		Assert.assertEquals(response.statusCode(), 200);
	}
}
