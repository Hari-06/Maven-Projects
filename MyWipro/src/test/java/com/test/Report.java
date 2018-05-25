package com.test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
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
		 InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // configure environment info
		 reports.setSystemInfo("Username", System.getProperty("user.name"));
		 reports.setSystemInfo("HostName",ip.getHostName());		 
		 reports.setSystemInfo("Host Address",ip.getHostAddress());
		 reports.setSystemInfo("OS", System.getProperty("os.name"));
		 reports.setSystemInfo("JAVA", System.getProperty("java.version"));
		 reports.setSystemInfo("Browser", "firefox");
		 reports.setSystemInfo("Environment", "UAT");
		 reports.setSystemInfo("Region", "UA1");
		 
			
		 // make the charts visible on report open
		 htmlReporter.config().setChartVisibilityOnOpen(true);

		 
		 // report title
		 htmlReporter.config().setDocumentTitle("aventstack - ExtentReports");

		 // encoding, default = UTF-8
		 htmlReporter.config().setEncoding("UTF-8");

		 // protocol (http, https)
		 htmlReporter.config().setProtocol(Protocol.HTTPS);

		 // report or build name
		 htmlReporter.config().setReportName("Build-1224");

		 // chart location - top, bottom
		 htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);

		 // theme - standard, dark
		 htmlReporter.config().setTheme(Theme.STANDARD);

		 // set timeStamp format
		 htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");

		 // add custom css
		 htmlReporter.config().setCSS("css-string");

		 // add custom javascript
		 htmlReporter.config().setJS("js-string");
		
	 }
	 
	 public void onFinish(ITestContext context) 
	 {
		 System.out.println("on finish");
		 reports.flush();
	 }
	}