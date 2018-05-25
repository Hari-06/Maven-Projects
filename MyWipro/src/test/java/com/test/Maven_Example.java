package com.test;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;


public class Maven_Example extends Report
{
	private RemoteWebDriver driver;		
	@Test				
	public void Launch_Google() throws Exception 
	{	
		driver.get("http://www.google.com");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Google"));
		getScreenshot();

		test.info("details-1", MediaEntityBuilder.createScreenCaptureFromPath("./screenshot.png").build());
		//MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("./screenshot.png").build();
	    //test.info("details-2", mediaModel);	
	    //test.info("details-3").addScreenCaptureFromPath("./screenshot.png");
	}	
	@BeforeTest
	public void beforeTest() 
	{	
		System.setProperty("webdriver.gecko.driver", "D:\\Hari\\drivers\\geckodriver\\geckodriver_64.exe");
		driver = new FirefoxDriver();  
	}		
	@AfterTest
	public void afterTest() 
	{
		driver.quit();			
	}
	public void getScreenshot() throws IOException
	{
		File src=driver.getScreenshotAs(OutputType.FILE);
		File desc=new File("./screenshot.png");
		FileUtils.copyFile(src, desc);
	}
	
}
