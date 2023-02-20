package com.jan23.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jan23.pages.base.BasePage;

public class HomePage extends BasePage{
	@FindBy(id="userNavLabel") WebElement myprofile;
	
	@FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a[5]")WebElement logout;
			
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	public String getTextFromHomeName()
	{
		System.out.println("I am here in getTextFromHomeName");
		return myprofile.getText();
	}
	public void clickMyProfile()
	{
		myprofile.click();
	}
	public void clickLogout()
	{
		logout.click();
	}
	

}
