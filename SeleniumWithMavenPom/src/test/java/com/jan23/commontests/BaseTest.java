package com.jan23.commontests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.io.FileHandler;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.jan23.utility.Constants;
import com.jan23.utility.ExtentReportsUtility;
import com.jan23.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	protected static Logger logger=null;
	public static ExtentReportsUtility extentreport=ExtentReportsUtility.getInstance();
	public static PropertiesUtility pUtility=null;
	
	@BeforeTest
	public static void setUpBeforeTest() {
		logger = LogManager.getLogger(BaseTest.class.getName());
		logger.info("Start the test");
	} 
	
    @BeforeMethod
	public static void driverSetup() throws InterruptedException, IOException
	{
    	String driverType = "chrome";
	logger.info("Creating driver");
    if (driverType.equals("chrome")) {
    	driver = setupChrome();
    }
    else if (driverType.equals("firefox")) {
    	driver = setupFirefox();
    }
    else {
    	logger.info("Invalid driver type");
    	}
	driver.get(pUtility.readPropertyData("url"));
	driver.manage().window().maximize();
	logger.info("driver created");
	Thread.sleep(4000);
	
	}
	
	@AfterMethod
	public void close()
	{
		driver.close();
	}
	public static WebDriver setupChrome() throws InterruptedException
	{
	logger.info("Creating Chrome");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	return driver;
	}
	
	public static WebDriver setupFirefox() throws InterruptedException
	{
	logger.info("Creating FireFox");
	WebDriverManager.firefoxdriver().setup();
	driver=new FirefoxDriver();
	return driver;
	}
	
	public static void scrolldown() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(3000);
	}
	
	public static void selectUserMenu() throws InterruptedException
	{
		clickMenu();
		
		List<WebElement> pdropdowns = driver.findElements(By.xpath("//*[@id=\"userNav-menuItems\"]/a"));
	   
		List<String> expected_items = Arrays.asList("My Profile","My Settings",
				"Developer Console","Switch to Lightning Experience","Logout");
		for (WebElement wp: pdropdowns)
	    
	    {
	    	Assert.assertTrue(expected_items.contains(wp.getText())); 
	    }
	}
	
	public static void logout() throws InterruptedException
	{
		//test case 7.1
		logger.info("Loging out application");
		selectUserMenu();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title=\"Logout\"]")).click();

	}
	
	public static void login() throws InterruptedException, IOException
	{
		driverSetup();
		logger.info("Login started");
		WebElement uname=driver.findElement(By.xpath("//input[@id='username']"));
		uname.sendKeys(Constants.USER_NAME);
	  
		WebElement password=driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys(Constants.PASSWORD);
	  
		WebElement login=driver.findElement(By.xpath("//*[@id='Login']"));
		login.click();

	}
	public static void clickMenu()
	{
		WebElement name=driver.findElement(By.id("userNavLabel"));
		name.click();
		
	}
	public static void click(String element)
	{
		driver.findElement(By.xpath(element)).click();
	}
	public static String getText(String element)
	{
		return driver.findElement(By.xpath(element)).getText();
	}

   public static void sendKeys(String element, String keyname)
   {
	   driver.findElement(By.xpath(element)).clear();
	   driver.findElement(By.xpath(element)).sendKeys(keyname);
   }
   public static void waiting() throws InterruptedException
   {
	   Thread.sleep(3000);
   }
   public static void popup()
   {
   Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"tryLexDialogX\"]")).size() > 0;
	if (isPresent == true) 
			{
		driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
			}
   }
   
	public static void getScreenshots() {
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
		String fileName = timeStamp + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcImage = ts.getScreenshotAs(OutputType.FILE);
		File tarImage = new File(Constants.SCREENSHOTS_DIR_PATH+fileName);
		try {
			FileHandler.copy(srcImage, tarImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static String getScreenshots(WebDriver driver, String method_name)  {
        logger.info("Taking Screenshot");
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
		String fileName =  method_name+timeStamp + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcImage = ts.getScreenshotAs(OutputType.FILE);
		
		File tarImage = new File(Constants.SCREENSHOTS_DIR_PATH+fileName);
		try {
			FileHandler.copy(srcImage, tarImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarImage.getAbsolutePath();
	}
	@AfterTest
	public static void teardownAfterTest()
	{
		//extentreport.endReport();
	}
	
}


