package com.brillio.stepdefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.brillio.base.AutomationHooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {

	@Given("I have browser with openemr application")
	public void i_have_browser_with_openemr_application() {
		WebDriverManager.chromedriver().setup();

		AutomationHooks.driver = new ChromeDriver();
		AutomationHooks.driver.manage().window().maximize();
		AutomationHooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		AutomationHooks.driver.get("https://demo.openemr.io/b/openemr");
	}

	@When("I enter username as {string}")
	public void i_enter_username_as(String username) {
		AutomationHooks.driver.findElement(By.id("authUser")).sendKeys(username);
	}

	@When("I enter password as {string}")
	public void i_enter_password_as(String password) {
		AutomationHooks.driver.findElement(By.id("clearPass")).sendKeys(password);
	}

	@When("I select the language as {string}")
	public void i_select_the_language_as(String language) {
		Select selectLan = new Select(AutomationHooks.driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText(language);
	}

	@When("I click on login")
	public void i_click_on_login() {
		AutomationHooks.driver.findElement(By.cssSelector("#login-button")).click();
	}

	@Then("I should get access to the portal with title as {string}")
	public void i_should_get_access_to_the_portal_with_title_as(String expectedTitle) {

		WebDriverWait wait = new WebDriverWait(AutomationHooks.driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Patient']")));

		Assert.assertEquals(expectedTitle, AutomationHooks.driver.getTitle());
	}
	
	@Then("I should get the message as {string}")
	public void i_should_get_the_message_as(String expectedError) {
	    String actualError=AutomationHooks.driver.findElement(By.xpath("//div[contains(text(),'Invalid')]")).getText();
	    Assert.assertTrue("Assertion on invalid error message",actualError.contains(expectedError)); //expect true
	}
}
