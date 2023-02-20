package com.jan23.pages.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jan23.commontests.BaseTest;
import com.jan23.pages.forgotpasswordpage.ForgotPasswordpage;
import com.jan23.pages.home.HomePage;
import com.jan23.pages.login.LoginPage;
import com.jan23.utility.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(com.jan23.utility.MyTestNgListners.class)
public class SaleforceLoginWithPOM extends BaseTest{
	

    @Test
	public void Login_Script() throws InterruptedException
	{
		LoginPage loginpage=new LoginPage(driver);
    	loginpage.enterUserName(Constants.USER_NAME);
    	loginpage.enterPassword(Constants.PASSWORD);
    	driver=loginpage.clickLogin();
    	waiting();
    	HomePage homepage=new HomePage(driver);
    	logger.info("<<<< driver 2" + driver);
    	logger.info("<<<< homepage 2" + homepage);
    	String actual_name=homepage.getTextFromHomeName();
    	Assert.assertEquals(actual_name, "Rani chiliveri");
	}

	@Test
	public static void loginErrorMessageTc1() throws InterruptedException, IOException
	{
    	LoginPage loginpage=new LoginPage(driver);
    	loginpage.enterUserName(Constants.USER_NAME);
    	loginpage.enterPassword(" ");
    	driver=loginpage.clickLogin();
		String actualErrorMessage=loginpage.getText();
		String expectedErrorMessage="Please enter your password.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	}
	
	@Test
	public static void checkRememberMe() throws InterruptedException, IOException
	{
    	LoginPage loginpage=new LoginPage(driver);
    	loginpage.enterUserName(Constants.USER_NAME);
    	loginpage.enterPassword(Constants.PASSWORD);
    	loginpage.clickRememberMe();
    	driver=loginpage.clickLogin();
		 
    	Thread.sleep(3000);
    	
    	HomePage homepage=new HomePage(driver);
    	homepage.clickMyProfile();
    	homepage.clickLogout();
    	
		Thread.sleep(3000);
		
		String actualUserName=loginpage.getTextusernameverification();
		String expectedUserName="rani.ch@adcd.com";
		Assert.assertEquals(expectedUserName, actualUserName);
		
	}
	
	@Test
	public static void forgotPassword() throws InterruptedException, IOException
	{
    	LoginPage loginpage=new LoginPage(driver);
    	loginpage.enterUserName(Constants.USER_NAME);
		
    	loginpage.enterPassword(" ");
    	
		loginpage.clickforgotpassword();
		
		
		ForgotPasswordpage fpg=new ForgotPasswordpage(driver);
		fpg.senduserName(Constants.USER_NAME);
		fpg.clickMycontinue();
		
		String actualMessage=fpg.getmessage();
		String expectedMessage="Check Your Email";
		Assert.assertEquals(expectedMessage, actualMessage);	
	}
	
	@Test
	public static void wrongCredentials() throws InterruptedException, IOException 
	{
    	LoginPage loginpage=new LoginPage(driver);
    	loginpage.enterUserName("123");
    	loginpage.enterPassword("22131");
    	driver=loginpage.clickLogin();
    	
		String actualErrorName=loginpage.getText();
		String expectedUserName="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(expectedUserName, actualErrorName);
	}
	}
	
    

