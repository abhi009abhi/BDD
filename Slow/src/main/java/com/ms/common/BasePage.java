package com.ms.common;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.genericfunction.Property;
import com.ms.runner.TestRunner;

public class BasePage {

	public static WebDriver driver;
	public static Wait<WebDriver> wait;
	public static String SceanrioID,downloadReportAt;
    public static String SheetName,scenTempname,productname,scenName;
    public static String SHEETNAME;
    public static int RowNo;
    public static JavascriptExecutor jse;
    public static Actions action;
    public static Wait<WebDriver> largewait;
    
    public static HashMap<String,String> hm= new HashMap<String,String>();
    //public static EWSEmailService; Custom Class
    
    protected String oldUserEmail,newUserEmail;
    protected long emailSubscriptionStartTimeInNanos;
    public static ChromeOptions chromeOptions;
    //GenericFunction gf = new GenericFunctions(); /custom class
    
     public Property config = new 
    		 Property(System.getProperty("user.dir")+  
    		 "src/main/resources/"+"config.properties"); //custom class
     
    public static void initializeDriver()
    {
    	System.out.println("Inside Base page Launching chrome browser");
    	//System.setProperty("webdrive.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
    	chromeOptions = setChromeBrowserConfig();
    	
    	try
    	{
    		driver = new ChromeDriver(TestRunner.chromeDriverService);
    		//driver = new ChromeDriver(TestRunner.chromeDriverService,chromeOptions);
    	    System.out.println("driver is "+driver);
    	}
    	catch(Exception e)
    	{
    		//driver = new ChromeDriver(MSChromeDriverServiceHandler.CHROME_Driver_Service,chromeOptions)
    	  System.out.println(e);
    	}
    	
    	wait = new WebDriverWait(driver,30);
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    	jse = (JavascriptExecutor)driver;
    	largewait = new WebDriverWait(driver,60);
    	actions = new Actions(driver);
    	
    }
    public static ChromeOptions setChromeBrowserConfig()
	{
		String downloadReportAt = System.getProperty("user.dir")+"/Download/";
	    File downloadPath = new File(downloadReportAt);
	    System.out.println(downloadPath);
		HashMap<String,Object> setChromePrefAs = new HashMap<String,Object>();
		//setChromePrefAs.put("profile.default_content_settings.popups",0);
		setChromePrefAs.put("download.default_directory",downloadPath);
		//setChromePrefAs.put("safebrowsing.enabled",true);
		
	   ChromeOptions chromeOptions = new ChromeOptions();
	   chromeOptions.setExperimentalOption("pref",setChromePrefAs);
	  /* chromeOptions.addArguments("test-type"); 
	   chromeOptions.addArguments("--diable-web-security");
	   chromeOptions.addArguments("--no-sandbox");
	  */
	   DesiredCapabilities capabilties = DesiredCapabilities.chrome();
       capabilties.setCapability("chrome.switches",Arrays.asList("--ignore-certficate-errors"));
	   capabilties.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
	   capabilties.setCapability("disable-popup-blocking",true);
	   capabilties.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
	 chromeOptions.merge(capabilties); //selenium 2.53version
	   
	   return chromeOptions;
	}

     actions.
}
