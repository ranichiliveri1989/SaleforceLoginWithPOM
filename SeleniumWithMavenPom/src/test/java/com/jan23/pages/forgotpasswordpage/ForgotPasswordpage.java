package com.jan23.pages.forgotpasswordpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jan23.utility.Constants;

public class ForgotPasswordpage
  {
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"un\"]") WebElement username;
	@FindBy(xpath="//*[@id=\"continue\"]") WebElement clickcontinue;	
	@FindBy(xpath="//*[@id=\"header\"]") WebElement message;
	
	public ForgotPasswordpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void senduserName(String data)
	{
		username.sendKeys(data);
	}
	public void clickMycontinue()
	{
		clickcontinue.click();
    }
	public String getmessage()
	{
		return message.getText();
    }
	
}

