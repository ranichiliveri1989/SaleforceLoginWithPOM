package com.jan23.utility;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jan23.commontests.BaseTest;

public class MyTestNgListners implements ITestListener
{
	

	protected static ExtentReportsUtility extentreport=null;
	protected WebDriver driver;
	public void onTestStart(ITestResult result) {
		
		extentreport.startsingleTestReport(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String method_name = result.getMethod().getMethodName();

		extentreport.logTestPassed(method_name);
		BaseTest ob=new BaseTest();
		driver=ob.getDriver();
		String path = ob.getScreenshots(driver, method_name);
		extentreport.logTestScreenshot(path);
	}

	public void onTestFailure(ITestResult result) {

		// TODO Auto-generated method stub
		String method_name = result.getMethod().getMethodName();
		extentreport.logTestFailed(method_name);
		BaseTest ob=new BaseTest();
		driver=ob.getDriver();
		String path = ob.getScreenshots(driver, method_name);
		extentreport.logTestScreenshot(path);

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport=ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport.endReport();
	}
}
