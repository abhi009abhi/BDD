package com.ms.common;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Helpers extends BasePage {
	// Actions action = new Actions(driver);

	public enum SendKeysType {

		sendKeysDefault, sendKeysWithEnter, sendKeysClear;
	}

	public enum rgbaFontType {
		black_bold, blue, grey, red, Red, white;
	}

//-----------------------------Element visibility helpers------------------------------//	
	public boolean verifyElementPresenceStandarized(WebElement we) {
		System.out.println("Verifying visibility of Element :" + we);
		try {
			boolean vStatus = elementDisplayed(we);
			return vStatus;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean elementDisplayed(WebElement we) {
		waitVisibiltyStandardized(we);
		boolean eleDisplayed = we.isDisplayed();
		return eleDisplayed;
	}

	public void waitVisibiltyStandardized(WebElement we) {
		System.out.println("Waiting for weblement " + we + "we to be visible");
		try {
			wait.until(ExpectedConditions.visibilityOf(we));
		} catch (Exception e) {
			System.out.println("Oops exception occured");
			e.printStackTrace();
			Assert.fail("Visiblity wait time over for Element " + we + " Message " + e.getMessage());
		}
	}

	public void verifyVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Elemenent Not visible By " + by + " Message " + e.getMessage());
		}
	}

	public boolean elementNotVisible(By by) {
		boolean elementInVisibility = false;

		try {
			elementInVisibility = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (NoSuchElementException | TimeoutException e) {
			e.printStackTrace();
		}
		return elementInVisibility;
	}

	public boolean elementNotVisible(WebElement we) {
//	   WebElement eleToVisible;
		boolean elementInVisibility = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(we));
			elementInVisibility = false;
		} catch (NoSuchElementException | TimeoutException e) {
			e.printStackTrace();
			elementInVisibility = false;
		}
		return elementInVisibility;
	}

//-----------------------------Element visibility helpers End------------------------------//		

//----------------------------------------Enter Data----------------------------------------//

	public void enterText(String text, WebElement ele, SendKeysType sk) {

		jse.executeScript("arguments[0].scrollIntoView({block : \"center\"});", ele);
		String valueToEnter = null;
		switch (sk) {
		case sendKeysDefault:
			valueToEnter = text;
			break;
		case sendKeysClear:
			ele.clear();
			valueToEnter = text;
			break;
		case sendKeysWithEnter:
			valueToEnter = Keys.chord(text, Keys.ENTER);
			break;
		default:
			throw new IllegalArgumentException("sendKeys Type incorrect " + sk);
		}

		ele.sendKeys(valueToEnter);
	}

//----------------------------------------------Click Events-----------------------------------------------------//

	public void clickElementStandardized(WebElement we) {
		waitVisibiltyStandardized(we);
		try {
			System.out.println("Clicking on element " + we);
			we.click();
			System.out.println("Click Success");
		} catch (Exception e) {
			System.out.println("Trying to Click via javaScript executor");
			try {
				jseClick(we);
			} catch (Exception jseEx) {
				e.printStackTrace();
				Assert.fail("Unable to click on Element " + we + e.getMessage());
			}
		}

	}

	public boolean jseClick(WebElement we) {
		waitVisibiltyStandardized(we);
		try {
			jse.executeScript("arguments[0].click();", we);
			return true;
		} catch (Exception jseEx) {
			jseEx.printStackTrace();
			Assert.fail("JSE Click on Element not Happened " + we + jseEx.getMessage());
			return false;
		}
	}

	public void waitTillClickableStandardized(WebElement we) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(we));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to wait for Element " + we + e.getMessage());
		}
	}

	public void scrollToElementAndClick(By by) {
		jse.executeScript("argumnets[0].scrollIntoView(false)", driver.findElement(by));
		jseClick(driver.findElement(by));

	}

	public void scrollToElementAndClick(WebElement we) {

	}
//-------------------------------------------Click Events Ends----------------------------//

//-------------------------------------------WebElements--------------------------------------------//

	public WebElement getElementFromList(List<WebElement> listOfElements, String findText) {
		WebElement ele = null;
		try {
			for (WebElement e : listOfElements) {
				jse.executeScript("arguments[0].style.border='3px solid yellow'", e);
				e.getText().equals(findText);
				ele = e;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Web elment not found with filter text " + findText);
		}
		return ele;
	}
//------------------------------------------WebElements---------------------------------------------//

//-------------------------Directories--------------------//

	public static void checkDirectory(String dirPath) {
		File dirPresent = new File(dirPath);
		if (dirPresent.exists()) {
			System.out.println("Directory already Present");
		} else {
			dirPresent.mkdirs();
		}
	}
//-------------------------Directories--------------------//
	// -----------------------------BG and Font Color
	// Validation-------/////////////////

	public String fontValidation(WebElement we, rgbaFontType rgba) {
		System.out.println("Inside Helper Font Validation");
		String actualFont;
		String value = null;
		String fontColorRGB = we.getCssValue("color");
		System.out.println("Font color  found as " + fontColorRGB);

		switch (rgba) {
		case black_bold:
			actualFont = changeRGBToHex(fontColorRGB);
		case white:
			actualFont = changeRGBToHex(fontColorRGB);
			if (actualFont.equalsIgnoreCase("#ffffff") && fontColorRGB.equalsIgnoreCase("rgba(255, 255, 255, 1)")) {
				value = "white color";
			}
			break;
		}

		return value;
	}

	public String bgColorValidation(WebElement we, rgbaFontType rgba) {
		String actualFont;
		String value = null;
		String fontColor = we.getCssValue("color");
		System.out.println("Font color  found as " + fontColor);

		switch (rgba) {
		case black_bold:
			actualFont = changeRGBToHex(fontColor);
		}

		return value;
	}

	public String changeRGBToHex(String value) {
		String color_hex[];

		color_hex = value.replace("rgba(", "").split(",");
		printArray(color_hex);
//		System.out.println(Integer.parseInt(color_hex[0].trim()));
//		System.out.println(Integer.parseInt("255"));
		String actualHex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
				Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
		System.out.println("Actual hex found as " + actualHex);

		return actualHex;
	}

	// -----------------------------BG and Font Color
	// Validation--------------------////

	public void printArray(String s[]) {
		;
		for (String s1 : s) {
			System.out.println(s1);
		}
	}

	public String matchString(String actual, String expected, String condition) {
		String afterMatch = null;
		System.out.println("Actual value found as " + actual);
		System.out.println("Expected value found as " + expected);
		switch (condition) {
		case "actual":
			if (actual.equalsIgnoreCase(expected)) {
				afterMatch = actual;
			} else {
				afterMatch = "Actual match failed for string";
			}
			break;
		case "partial":
			if (actual.contains(expected)) {
				afterMatch = expected;
			} else {
				afterMatch = "Partial match failed for string";
			}
			break;
		default:
			afterMatch = "Incorrect Match condition";
		}
		System.out.println("result afer Matching " + afterMatch);
		return afterMatch;
	}

	public boolean matchStringBoolean(String actual, String expected) {
		System.out.println("Actual value found as " + actual);
		System.out.println("Expected value found as " + expected);
		boolean afterMatch = false;

		if (actual.equalsIgnoreCase(expected) || actual.contains(expected)) {
			afterMatch = true;
		}

		System.out.println("result afer boolean String Match " + afterMatch);
		return afterMatch;
	}

	public String getTextStandardized(WebElement we) {
		waitVisibiltyStandardized(we);
		return we.getText();
	}

//--------------------------------Scroll Element-------------------------//
	public void scrollTOElement(WebElement we, boolean direction) {
		// true to move down, false to move up
		jse.executeScript("arguments[0].scrollIntoView(" + direction + ");", we);
	}

	public void scrollTOElement(By byLocator) {
		jse.executeScript("arguments[0].scrollIntoView({block : \"center\"});", driver.findElement(byLocator));
	}

	public void scrollToElement(WebElement we) {
		jse.executeScript("arguments[0].scrollIntoView({block : \"center\", inline : \"center\"});", we);

	}

//----------------------------Scroll Element-------------------------//

	public WebElement getWebElement(By locator, String highlight) {
		WebElement we = null;
		switch (highlight.toLowerCase()) {
		case "yes":
			we = FindLocator.getElement(locator);
			jse.executeScript("arguments[0].style.border='3px solid yellow'", we);
			break;

		case "no":
			we = FindLocator.getElementByCss(locator);
			break;

		default:
			Assert.fail("Invalid higlight option find for locating WebElement");
		}

		return we;
	}

//---------------------------------Color BG,Text------------------------//	
	public String getColor(String rgba) {
		String colorValue = "#";
		String rgbORrgba = rgba.substring(0, 4);
		String colorArray[] = null;
		
		switch (rgbORrgba) {
		case "rgba(":
			colorArray = rgba.replace("rgba(", "").replace(")", "").split(",");

			break;

		case "rgb":
			colorArray = rgba.replace("rgb(", "").replace(")", "").split(",");

			break;

		default:
			Assert.fail("Incorrect rgba value found " + rgba);
		}

		colorValue = String.format("#%02x%02x%02x", Integer.parseInt(colorArray[0].trim()),
				Integer.parseInt(colorArray[1].trim()), Integer.parseInt(colorArray[2].trim()));
		return colorValue;
	}

	

}
//---------------------------------Color BG,Text------------------------//	
//class