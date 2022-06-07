package com.brillio.base;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;

public class AutomationHooks {
	public static WebDriver driver;
	
	
	@After
	public void afterScenario()
	{
		AutomationHooks.driver.quit();
	}
	

}