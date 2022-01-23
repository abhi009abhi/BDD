package com.ms.pages;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

import com.ms.common.BasePage;
import com.ms.common.FindLocator;
import com.ms.common.Helpers;
import com.ms.common.Helpers.SendKeysType;
import com.ms.common.Helpers.rgbaFontType;
import com.ms.common.IPage;
import com.ms.objectMappings.FlipkartHome_OR;
import com.ms.objectMappings.FlipkartLogin_OR;

public class FlipkartLoginPage extends BasePage implements IPage{
	Helpers help = new Helpers();

	@Override
	public boolean selectELemnt(String option, String sFieldName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyElement(String eleId, String sPage) throws Exception {
		WebElement verifyElement =null;
		switch(eleId.toLowerCase())
		{
		  case "login":
			  verifyElement= FindLocator.getElement(FlipkartLogin_OR.login);
			  break;
		  case "enter mobile no":
			  verifyElement= FindLocator.getElement(FlipkartLogin_OR.mobilNo);
			  break;
		  case "enter password":
			  verifyElement= FindLocator.getElement(FlipkartLogin_OR.pswd);
			  break;
			  
		  case "default":
			 Assert.fail("No case found in veriFyElement FlipkartLogin for "+eleId);
			  break;
		}
		
		boolean eleVerify = help.verifyElementPresenceStandarized(verifyElement);
		System.out.println("returning From Element verify "+eleVerify);
		return eleVerify;
	}

	@Override
	public void setData(String sValue, String field) throws Exception {
		WebElement ele=null;
		switch(field.toLowerCase())
		{
		   case "userid":
			   ele = FindLocator.getElement(FlipkartLogin_OR.mobilNo);
			   help.enterText(sValue,ele,SendKeysType.sendKeysDefault);
			break;
		   case "password":
			   ele = FindLocator.getElement(FlipkartLogin_OR.pswd);
			   help.enterText(sValue,ele,SendKeysType.sendKeysWithEnter);
			break;   
		   case "default":
				 Assert.fail("No case found in SetData FlipkartLogin for "+sValue);
		}
	}

	@Override
	public boolean validateValue(String expVal, String caseName) throws Exception {
		System.out.println("Inside FLipkart ValidateVlue");
		boolean result=false;String actualValue="not initialized";
		WebElement we =null;
		switch(caseName.toLowerCase())
		{
		  case "login button text":
			  we = FindLocator.getElement(FlipkartLogin_OR.loginButtonText);
			  actualValue= help.fontValidation(we,rgbaFontType.white);
			
			  break;
		  default:
			  Assert.fail("case "+caseName +" not found in FlipkartLogin validate Value ");;
		}
		
		result=help.matchStringBoolean(actualValue, expVal);
		return result;
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
