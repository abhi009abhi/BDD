package com.ms.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FindLocator extends BasePage{
	
	static WebElement singleElement;
	static List<WebElement> listOfElements;
	static By by;
	static Helpers helpObj= new Helpers();

	public static WebElement getElement(By locator)
	{
		try {
		singleElement = driver.findElement(locator);
		System.out.println(singleElement);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].style.border='3px solid yellow'",singleElement);
		
		
		}
		catch(NoSuchElementException nse)
		{
			nse.printStackTrace();
			Assert.fail("Element do not exist "+locator);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			singleElement=null;
			Assert.fail("Exception occurred for webElement "+locator);
			
		}
		
		return singleElement;
	}
	
	public static List<WebElement> getAllElement(By locator)
	{
		try {
		listOfElements = driver.findElements(locator);
     	}
		catch(NoSuchElementException nse)
		{
			nse.printStackTrace();
			Assert.fail("Element do not exist "+locator);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			listOfElements=null;
			Assert.fail("Exception occurred for webElement "+locator);
			
		}
		
		return listOfElements;
	}

	
	public static WebElement getElementByCss(By locator)
	{
		try
		{
		 singleElement = driver.findElement(locator);
		
		}
		catch(NoSuchElementException nse)
		{
			nse.printStackTrace();
			Assert.fail("Element do not exist "+locator);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Unable to find Element with locator "+locator);
		}
		
		return singleElement;
	}
	
	public static WebElement getSingleElement(By locator)
	{
		try
		{
		 singleElement = driver.findElement(locator);
		
		}
		catch(NoSuchElementException nse)
		{
			nse.printStackTrace();
			Assert.fail("Element do not exist "+locator);
		}
		catch(StaleElementReferenceException sre)
		{
			singleElement = staleExceptionHandler(locator);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Unable to find Element with locator "+locator);
		}
		
		return singleElement;
	}
	
	private static WebElement staleExceptionHandler(By locator)
	{
		int i=0;
		WebElement staleElementFoundAs =null;
		
		while(i<20)
		{
			try
			{
			  staleElementFoundAs = driver.findElement(locator);
			  if(staleElementFoundAs!=null)
			  {
				  break;
			  }	  
			}
			catch(Exception e)
			{
				System.out.println("Exeception Occured i is "+i);
				
			}
			i++;
		}	
		
		return staleElementFoundAs;
	}
	
	
}//class
