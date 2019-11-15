package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/myReport.html"); // specify location
		
		htmlReport.config().setDocumentTitle("AutomationReport");
		htmlReport.config().setReportName("rest API testing report");
		htmlReport.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "sai");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());// create new entry in report
		test.log(Status.PASS, "Test Passed is " + result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());// create new entry in report
		test.log(Status.FAIL, "Test failed is " + result.getName());
		test.log(Status.FAIL, "Test failed is " + result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());// create new entry in report
		test.log(Status.SKIP, "Test skipped is " + result.getName());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
