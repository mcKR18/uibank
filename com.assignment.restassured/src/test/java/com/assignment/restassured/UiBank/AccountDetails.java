package com.assignment.restassured.UiBank;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AccountDetails extends RestassuredBase{
	
	@Test(dependsOnMethods = {"com.assignment.restassured.UiBank.LoginUiBank.login"})
	public void accounts() {
		//baseURi 
		RestAssured.baseURI="https://uibank-api.azurewebsites.net/api/";
		
		//to get account details 
		Response response = RestAssured.
							given()
								.header("authorization", id)
								.queryParam("filter[where][userId]", userId)
								.log().all()
							.when()
								.get("accounts");
		
		// to print reponse in beatifully aligned format
		response.prettyPrint();
		
		//validate status code
		Assert.assertEquals(response.statusCode(), 200);
	}

}
