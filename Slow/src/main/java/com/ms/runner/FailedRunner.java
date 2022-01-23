package com.ms.runner;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.BeforeSuite;

import com.ms.listener.Report;

import cucumber.api.CucumberOptions;

@CucumberOptions(
		features ="{@target/rerun.txt}",
		glue="com.ms.stepDefinition",
		plugin ="rerun:target/rerun/failedCases.txt"
		)
public class FailedRunner {
	public static ChromeDriverService chromeDriverService;
	
	 @BeforeSuite
	 public void initializingChromeDriver()
	 {
		 System.out.println("Inside test Runner class");
		 if(chromeDriverService!=null && chromeDriverService.isRunning())
		 {
			 return;
		 }
		 
		 System.out.println("wait confguiring chromeDriverService");
		        chromeDriverService = new ChromeDriverService.Builder()
				 .usingDriverExecutable(new File(System.getProperty("user.dir")+"/drivers/chromedriver.exe"))
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

	 //@AfterSuite
	 public void OnFinish()
	 {
		 if(chromeDriverService!=null && chromeDriverService.isRunning())
		 {
			 chromeDriverService.stop();
		 }
		   Report.moveOldReportToArchive();
		 
		 try
		 {
			 String[] command = {"cmd.exe","/C","Start","generateReport.bat"};
		     Runtime.getRuntime().exec(command);
		     System.out.println("Cucumber Report creation is done");
	         try {
	        	// PlaceReportSharedPath.copyReport();
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
	 }

}
