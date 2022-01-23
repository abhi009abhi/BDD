package com.ms.common;

public interface IPage {

	public boolean selectELemnt(String option,String sFieldName) throws Exception;
	public boolean verifyElement(String eleId,String sPage) throws Exception;
	public void setData(String sValue,String caseName) throws Exception;
	public boolean validateValue(String expVal,String caseName) throws Exception;
	public boolean searchElement(String recordType) throws Exception;
	public boolean verifyNonPresenceOfElement(String element) throws Exception;
	public boolean verifyToolTip(String caseName,String sToolTip) throws Exception;
	public boolean validateValueTable(String expVal,String caseName) throws Exception;
	public void saveValues(String sFieldValues) throws Exception;
	public void navigateToPage(String sFieldValues) throws Exception;
	public void clickElementWithInSection(String sElement,String section, String pageName) throws Exception ;
	public void clickElement(String sElement) throws Exception;
	public void updateUrl(String sFiledValues ,String sPage) throws Exception;
	public void initiailizeTemplate(String tempname,String v1,String v2) throws Exception;
	public boolean verifyFocus(String comments) throws Exception;
	
	
}
