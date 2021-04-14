package com.qa.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ApiGetTest {
	
	@Test
	public void GetBookingIds_VerifyStatusCode() {
		
		
		// Create a request specification 
		RequestSpecification request= RestAssured.given();
		
		//Adding URI
		request.baseUri("https://restful-booker.herokuapp.com/booking");
		
		// Calling GET method on URI. After hitting we get Response
		Response response = request.get();
		
		// Let's print response body.
		String resString = response.asPrettyString();
		System.out.println("Respnse Details : " + resString);
 
		/*
		 * To perform validation on response like status code or value, we need to get
		 * ValidatableResponse type of response using then() method of Response
		 * interface. ValidatableResponse is also an interface.
		 */
		
		ValidatableResponse valRes = response.then();
		// It will check if status code is 200
		valRes.statusCode(200);
		// It will check if status line is as expected
		valRes.statusLine("HTTP/1.1 200 OK");
		valRes.header("Connection", "keep-alive");
		valRes.body("bookingid",Matchers.hasItem(8));
		
	/*	RestAssured.given()
		.baseUri("https://restful-booker.herokuapp.com")
		.when().
		get("/booking")
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.body("bookingid.sum()",Matchers.hasSize(55))
		.body("bookingid[3]", Matchers.equalTo(1));
		*/
		
		
 
	}

}
