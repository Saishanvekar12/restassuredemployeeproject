package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employees_Record extends TestBase{

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("*********Started Test case TC002_Get_Single_Employees_Record*******");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response= httpRequest.request(Method.GET,"/employee/"+empID);
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("*********Checking Response Body*******");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatusCode() {
		logger.info("*********Checking Status Code*******");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("*********Checking Response Time*******");
		
		long responseTime = response.getTime();
		logger.info("Response Time ==>" + responseTime);
		
		if(responseTime > 5000) {
			logger.warn("Response time is greater than 5000");
			Assert.assertTrue(responseTime<5000);
		}
	}
	
	@Test
	void checkStatusLine() {
		logger.info("*********Checking Status Line*******");
		
		String statusLine= response.getStatusLine();
		logger.info("Status Line ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("*********Checking Content Type*******");
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkServerType() {
		logger.info("*********Checking Server Type*******");
		
		String serverType = response.header("Server");
		logger.info("Content Type ==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("*********Finished Test case TC002_Get_Single_Employees_Record********");
	}
}
