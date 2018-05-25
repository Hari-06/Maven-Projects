package com.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report implements ITestListener 
{
	 protected static WebDriver driver;
	 protected static ExtentReports reports;
	 protected static ExtentTest test;
	 protected static ExtentHtmlReporter htmlReporter;
	 
	 
	 public void onTestStart(ITestResult result) 
	 {
		 
	  System.out.println("on test start");
	  test = reports.createTest(result.getMethod().getMethodName());
	  test.log(Status.INFO,  result.getMethod().getMethodName() + ":: test is started");
	  
	 }
	 
	 public void onTestSuccess(ITestResult result) 
	 {
		 
	  System.out.println("on test success");
	  test.log(Status.PASS,  result.getMethod().getMethodName() + ":: test is passed");
	
	 }
	 
	 public void onTestFailure(ITestResult result) 
	 {
		 
	  System.out.println("on test failure");
	  test.log(Status.FAIL,  result.getMethod().getMethodName() + ":: test is failed");
	  TakesScreenshot ts = (TakesScreenshot) driver;
	  File src = ts.getScreenshotAs(OutputType.FILE);
	  try 
	  {
	   FileUtils.copyFile(src, new File("C:\\images\\" + result.getMethod().getMethodName() + ".png"));
	   test.log(Status.FAIL, result.getMethod().getMethodName() + "test is failed"+test.addScreenCaptureFromPath("C:\\images\\" + result.getMethod().getMethodName() + ".png"));
	   test.log(Status.FAIL, result.getMethod().getMethodName() + result.getThrowable().getMessage());
	  } 
	  catch (IOException e) 
	  {
	   e.printStackTrace();
	  }
	  
	 }
	 
	 public void onTestSkipped(ITestResult result) 
	 {
		 
	  System.out.println("on test skipped");
	  test.log(Status.SKIP, result.getMethod().getMethodName() + ":: test is skipped");
	 
	 }
	 
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	 {
		 
	  System.out.println("on test sucess within percentage");
	  
	 }
	 
	 public void onStart(ITestContext context) 
	 {
		 System.out.println("on start");
		 htmlReporter = new ExtentHtmlReporter(".//report1.html");
		 
		 // attach the html file to extent reports
		 reports = new ExtentReports();
		 reports.attachReporter(htmlReporter);
		 
		 //load extent-config file
		// htmlReporter.loadXMLConfig(".//test-output//extent-config.xml");
		 
		 // configure environment info
		 reports.setSystemInfo("Hostname", "localhost");
		 reports.setSystemInfo("Browser", "firefox");
		 reports.setSystemInfo("Environment", "UAT");
		 reports.setSystemInfo("Region", "UA1");
		 
		 htmlReporter.config().setChartVisibilityOnOpen(true);
		 htmlReporter.config().setDocumentTitle("Automation Testing");
		 htmlReporter.config().setReportName("My Own Report");
		 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		 htmlReporter.config().setTheme(Theme.STANDARD);
	 }
	 
	 public void onFinish(ITestContext context) 
	 {
		 System.out.println("on finish");
		 reports.flush();
	 }
	}