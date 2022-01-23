package com.ms.runner;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ms.listener.Report;
import com.ms.mailerUtility.PlaceReportSharedPath;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions(
		features ="src/test/resources/com/ms/feature/",
		glue="com.ms.stepDefinition",
		plugin = { "pretty", "json:Report/Cucumber.json" },
		dryRun=false
		)	

public class TestRunner extends AbstractTestNGCucumberTests
{

	public static ChromeDriverService chromeDriverService;
	Instant start,end ;
	 @BeforeSuite
	 public void initializingChromeDriver()
	 {
			System.out.println("Inside Test Runner ");
		 start = Instant.now();
		 System.out.println("Inside test Runner class");
		 if(chromeDriverService!=null && chromeDriverService.isRunning())
		 {
			 return;
		 }
		 
		 System.out.println();
		        chromeDriverService = new ChromeDriverService.Builder()
				 .usingDriverExecutable(new File(System.getProperty("user.dir")+"/drivers/chromedriver93.exe"))
				 .usingAnyFreePort().build();
				 System.out.println("Chromedriver service SetUp done");
		 try
		 {
			 System.out.println("Chrome driver service started");
			 chromeDriverService.start();
		 }
		 catch(IOException e)
		 {
			 System.out.println("Error Occured while Strting chrome driver service making it null");
		     System.out.println("Error is :: "+e.getMessage());
		     chromeDriverService=null;
		     throw new RuntimeException("Error Occured while Strating Chrome", e);
		 }
	 }

	 @AfterSuite
	 public void OnFinish()
	 {
		 if(chromeDriverService!=null && chromeDriverService.isRunning())
		 {
			 chromeDriverService.stop();
			 
		 }
//		   Helpers.checkDirectory(System.getProperty("user.dir")+"/Report/");
//		   Helpers.checkDirectory(System.getProperty("user.dir")+"/Report/Archives");
//		   
		   Report.moveOldReportToArchive();
		 
		 try
		 {
			 String[] command = {"cmd.exe","/C","Start","generateReport.bat"};
		     Runtime.getRuntime().exec(command);
		     System.out.println("Cucumber Report creation is done");
	         try {
	        	 PlaceReportSharedPath.copyReport();
	        	// SendMailSMTP.configEmail();
	        	 
	         } 
	         catch(Exception e)
	         {
	        	 e.printStackTrace();
	         }
		 }
		 catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
		 end = Instant.now();
		 Duration timeElapsed = Duration.between(start, end);
		 System.out.println("time ELapsed "+timeElapsed.getSeconds());
	 }

	 
	
}
