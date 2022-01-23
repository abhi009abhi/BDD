package com.ms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ms.common.BasePage;
import com.ms.common.FindLocator;
import com.ms.common.Helpers;
import com.ms.common.IPage;
import com.ms.objectMappings.FlipkartHome_OR;

public class FlipkartHomePage extends BasePage implements IPage{
	
	Helpers help = new Helpers();

	@Override
	public boolean selectELemnt(String option, String sFieldName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyElement(String eleId, String sPage) throws Exception {
		// TODO Auto-generated method stub
		WebElement verifyElement =null;
		List<WebElement> allElements = null;
		switch(eleId.toLowerCase())
		{
		  case "logo":
			  verifyElement= FindLocator.getElement(FlipkartHome_OR.fkLogo);
			  break;
		  case "top offer":
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Top Offers");
			  break;
		  case "grocery":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Grocery");
			  break;
		  case "mobile":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Mobiles");
			  break;
		  case "fashion":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Fashion");
			  break;
		  case "electronics":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Electronics");
			  break;
		  case "home":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Home");
			  break;
		  case "appliances":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Appliances");
			  break;
		  case "travel":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
			  verifyElement=help.getElementFromList(allElements, "Travel");
			  break;
		  case "topofferelectronics":	  
			  allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhTopOfferCat);
			  verifyElement=help.getElementFromList(allElements, "Electronics");
			  break;
		  default:
			  Assert.fail("No Case Found in FilpKartHome page verfiyElement for "+eleId);
		}
		
		System.out.println("Sending Element "+verifyElement+ "for visibilty check");
		boolean eleVerify = help.verifyElementPresenceStandarized(verifyElement);
		return eleVerify;
	}

	@Override
	public void setData(String sValue, String caseName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateValue(String expVal, String caseName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean searchElement(String recordType) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyNonPresenceOfElement(String element) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyToolTip(String caseName, String sToolTip) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateValueTable(String expVal, String caseName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveValues(String sFieldValues) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateToPage(String sFieldValues) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickElementWithInSection(String sElement, String section, String pageName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickElement(String sElement) throws Exception {
		// TODO Auto-generated method stub
		WebElement ele2Click=null;
		List<WebElement> allElements = null;
		switch(sElement.toLowerCase())
		{
		case "topoffer":
			allElements= FindLocator.getAllElement(FlipkartHome_OR.fkhCategories);
		    ele2Click=help.getElementFromList(allElements, "Top Offers");
		    System.out.println("element found as "+ele2Click.getText());
		    break;
		    
		default:
			Assert.fail("Element "+sElement+" is not present to click on FlipKart Home");
		}
		System.out.println("Sending webElment "+ele2Click+ " for click ");
		help.clickElementStandardized(ele2Click);
		help.jseClick(ele2Click);
	}

	@Override
	public void updateUrl(String sFiledValues, String sPage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiailizeTemplate(String tempname, String v1, String v2) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyFocus(String comments) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
