package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_Employees_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response Response;
	
	String empname = RestUtils.empName();
	String empsal = RestUtils.empSal();
	String empage = RestUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("*********Started Test case TC004_PUT_Employees_Record*******");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empname);
		requestParams.put("salary", empsal);
		requestParams.put("age", empage);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response= httpRequest.request(Method.PUT,"/update/"+ empID);
		
		Thread.sleep(5);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("*********Checking Response Body*******");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertEquals(responseBody.contains(empname), true);
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
	
	@AfterClass
	void tearDown() {
		logger.info("*********Finished Test case TC004_PUT_Employees_Record********");
	}
}
