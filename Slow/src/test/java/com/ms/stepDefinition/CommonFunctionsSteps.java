package com.ms.stepDefinition;

import org.testng.Assert;

import com.ms.common.BasePage;
import com.ms.common.IPage;
import com.ms.pages.FlipkartHomePage;
import com.ms.pages.FlipkartLoginPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CommonFunctionsSteps extends BasePage {
 
	FlipkartLoginPage fkLogin = new FlipkartLoginPage(); 
	FlipkartHomePage fkHome = new FlipkartHomePage();
	IPage pageFoundAs;
	public IPage goToPage(String page)
	{
		System.out.println("Inside CommonFunction Steps");
		switch(page.toLowerCase())
		{
		 case "flipkarthome":
		 pageFoundAs = new FlipkartHomePage();	 
		 break;
		 case "flipkartlogin":
			 pageFoundAs = new FlipkartLoginPage() ;	 
			 break;
		 default:
		 pageFoundAs=null;
		 Assert.fail("Page not found as Per featur file");
		 break;
		}
		
		return pageFoundAs;
	}
	
	
	@Given("^I open chome browser$")
	public void i_open_chome_browser() throws Throwable {
		
	
	   
	}
	
	@Then("^I verify \"([^\"]*)\" is displayed on \"([^\"]*)\" page$")
	public void verifyElementDisplayed(String ele, String page) throws Throwable {
	    boolean eleDisplayed = goToPage(page).verifyElement(ele,page);
	    Assert.assertTrue(eleDisplayed,String.format("ele '%1$s' not found on page '%2$s'",ele,page));
	    
	  
	    }  
	  @Then("^I enter \"([^\"]*)\" in \"([^\"]*)\" field on \"([^\"]*)\" page$")
	    public void enterData(String text, String field, String page) throws Throwable {
	        goToPage(page).setData(text, field);
	}
	  
	  @Given("^I click on \"([^\"]*)\" field on \"([^\"]*)\" page$")
	  public void clickOnElements(String ele, String page) throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  goToPage(page).clickElement(ele);
	  }  
	  
	  @Given("^I validate \"([^\"]*)\" is present in \"([^\"]*)\" on \"([^\"]*)\" page$")
	  public void validateElementOrValue(String value, String caseName, String page) throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  System.out.println("Page is "+page+" value is "+value+ "caseValue is"+caseName);
		  boolean result=goToPage(page).validateValue(value, caseName);
		  Assert.assertTrue(result,String.format("Failed to validate '%1$s' in '%2$s' in '%3$s'",value,caseName,page));
	  }
}
