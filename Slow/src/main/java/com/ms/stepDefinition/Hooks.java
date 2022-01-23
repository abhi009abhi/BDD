package com.ms.stepDefinition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.ms.common.BasePage;

import cucumber.api.Scenario;
import cucumber.api.event.TestCaseFinished;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BasePage{
	
	public String env = config.ReadProperty("Env");
	public static Date runStarted =new Date();
	public static Date runEnded =new Date();
	public static String runStartedDate="";
	public static String runEndedDate="";

	public static boolean verifyEmail=false;
	WebElement ele = null;
	public TestCaseFinished tcFinish;
	public String URL = config.ReadProperty("Fast_Url");
	
	@Before(order = -1)
	public void initiate()
	{
		System.out.println("Inside Hook Initiate");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Hooks.runStartedDate = dateFormat.format(runStarted);
	    System.out.println("Run start date "+runStartedDate);
	    BasePage.initializeDriver();
	}
	
	@Before(order = 0)
	public void launchSF()
	{
		System.out.println("driver is " +driver);
		//driver.get("www.flipkart.com");
		driver.get("https://www.flipkart.com");
	}
	@Before("@emailverfication")
	public void enableEmailListener()
	{
		
	}
	
	@After("@emailverfication")
	public void disableEmailListener()
	{
		
	}
	
	@After(order=0)
	public void closeDriverInstance()
	{
		if(driver!=null)
		{
			driver.quit();
		}	
		driver=null;
	}

	@After(order=1)
 public void embedScreenShotOnFail(Scenario scenario)
 {
	 if(scenario.isFailed())
	 {
	   System.out.println("Taking screenshot for failed cases");
	   try {
		   
		   byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		   scenario.embed(screenshot, "image/png");
		   scenario.write(scenario.getName()+ "is Failed and failed screen is as above screenshot");
	   }
	   catch(WebDriverException wde)
	   {
		   scenario.write("Embed failed " +wde.getMessage());
	   }
	   catch(ClassCastException cce)
	   {
		   System.out.println("After is getting Executed in second cacth");
	       cce.printStackTrace();
	   }
	 } 
	 
 }
 
	@After(order=2)
 public void embedScreenShotOnPass(Scenario scenario)
 {
	 if(!scenario.isFailed())
	 {
	   System.out.println("Taking screenshot for pass cases");
	   try {
		   
		   byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		   scenario.embed(screenshot, "image/png");
		   scenario.write(scenario.getName()+ "is pass and passed screen is as above screenshot");
	   }
	   catch(WebDriverException wde)
	   {
		   scenario.write("Embed passed " +wde.getMessage());
	   }
	   catch(ClassCastException cce)
	   {
		   System.out.println("After is getting passed in second cacth");
	       cce.printStackTrace();
	   }
	 } 
	 
 }
 
 
}
