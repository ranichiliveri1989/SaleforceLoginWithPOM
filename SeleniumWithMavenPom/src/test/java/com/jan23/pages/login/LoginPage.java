package com.jan23.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jan23.pages.base.BasePage;

public class LoginPage extends BasePage{
	@FindBy(xpath="//input[@id='username']") WebElement username;
	@FindBy(xpath="//input[@id='password']") WebElement password1;
	@FindBy(xpath="//*[@id='Login']") WebElement loginButton;
	@FindBy(xpath="//*[@id=\"error\"]") WebElement errorMessage;
	@FindBy(xpath="//*[@id=\"rememberUn\"]")WebElement rememberMe;
	@FindBy(xpath="//*[@id=\"idcard-identity\"]")WebElement usernameverification;
	@FindBy(xpath="//*[@id=\"forgot_password_link\"]")WebElement forgotPassword;
	
	
	public LoginPage(WebDriver driver)
	{
     super(driver);
	}
	public void enterUserName(String data)
	{
		username.sendKeys(data);	
	}
	public void enterPassword(String data)
	{
		password1.clear();
		password1.sendKeys(data);
	}
	public WebDriver clickLogin()
	{
		loginButton.click();	
		return driver;	
	}
	public String getText()
	{
		return errorMessage.getText();
	}
	public void clickRememberMe()
	{
		rememberMe.click();	
			
	}
	public String getTextusernameverification()
	{
		return usernameverification.getText();
	}
	public void clickforgotpassword()
	{
		forgotPassword.click();
	}

	
    
}


