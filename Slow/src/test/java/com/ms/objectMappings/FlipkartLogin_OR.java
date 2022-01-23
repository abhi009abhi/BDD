package com.ms.objectMappings;

import org.openqa.selenium.By;

public interface FlipkartLogin_OR {
 By login = By.xpath("(//span[text()='Login'])[2]//ancestor::button");
 By loginButtonText = By.xpath("(//span[text()='Login'])[2]");
 By mobilNo = By.xpath("//input[@class='_2IX_2- VJZDxU']");
 By pswd = By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']");
 }
