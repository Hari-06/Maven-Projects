package com.test;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class Maven_Example extends Report
{
	private RemoteWebDriver driver;	
	Markup m1,m2,m3;
	@Test				
	public void Launch_Google() throws Exception 
	{	
		driver.get("http://www.google.com");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Google"));
		getScreenshot();
		
		test.info("html", MediaEntityBuilder.createScreenCaptureFromPath("./screenshot.png").build());
		//MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("./screenshot.png").build();
	    //test.info("details-2", mediaModel);	
	    //test.info("details-3").addScreenCaptureFromPath("./screenshot.png");
		
		//Table
		String[][] data = {
			    { "Step no", "Step Description", "Expected" , "Actual"},
			    { "Content.1.1", "Content.2.1", "Content.3.1" , "Content.4.1 " },
			    { "Content.1.2", "Content.2.2", "Content.3.2" , "Content.4.2 " },
			    { "Content.1.3", "Content.2.3", "Content.3.3" , "Content.4.3 " },
			    { "Content.1.4", "Content.2.4", "Content.3.4" , "Content.4.4 " }
			};
			m1 = MarkupHelper.createTable(data);

			test.pass(m1);
			// or
			test.log(Status.PASS, m1);
			
			//Label
			String text = "extent";
			 m2 = MarkupHelper.createLabel(text, ExtentColor.BLUE);

			test.pass(m2);
			// or
			test.log(Status.PASS, m2);
			
			
			//Code Block
			String code = "\n\t\n\t\tText\n\t\n";
			Markup m3 = MarkupHelper.createCodeBlock(code);

			test.pass(m3);
			// or
			test.log(Status.PASS, m3);
			
			
			
	}	
	@BeforeTest
	public void beforeTest() 
	{	
		System.setProperty("webdriver.gecko.driver", ".//geckodriver_64.exe");
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
