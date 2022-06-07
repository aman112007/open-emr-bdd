package com.brillio.stepdefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.brillio.base.AutomationHooks;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PatientSteps {

	private static String actualAlertText;

	@When("I click on patient menu")
	public void i_click_on_patient_menu() {
		AutomationHooks.driver.findElement(By.xpath("//div[normalize-space()='Patient']")).click();
	}

	@When("I click on new-search menu")
	public void i_click_on_new_search_menu() {
		AutomationHooks.driver.findElement(By.xpath("//div[normalize-space()='New/Search']")).click();
	}

	@When("I fill the form")
	public void i_fill_the_form(DataTable dataTable) {

		System.out.println(dataTable);
		List<Map<String, String>> lists = dataTable.asMaps();

		String firstname = lists.get(0).get("firstname");
		String lastname = lists.get(0).get("lastname");
		String dob = lists.get(0).get("dob");
		String gender = lists.get(0).get("gender");

		AutomationHooks.driver.switchTo().frame(AutomationHooks.driver.findElement(By.xpath("//iframe[@name='pat']")));
		AutomationHooks.driver.findElement(By.id("form_fname")).sendKeys(firstname);
		AutomationHooks.driver.findElement(By.id("form_lname")).sendKeys(lastname);
		AutomationHooks.driver.findElement(By.id("form_DOB")).sendKeys(dob);

		Select selectGen = new Select(AutomationHooks.driver.findElement(By.id("form_sex")));
		selectGen.selectByVisibleText(gender);

	}

	@When("I click on create new patient")
	public void i_click_on_create_new_patient() {
		AutomationHooks.driver.findElement(By.id("create")).click();
		AutomationHooks.driver.switchTo().defaultContent();
	}

	@When("I click on confirm create new patient")
	public void i_click_on_confirm_create_new_patient() {
		AutomationHooks.driver.switchTo()
				.frame(AutomationHooks.driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		AutomationHooks.driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		AutomationHooks.driver.switchTo().defaultContent();
	}

	@When("I store the alert text and handle it")
	public void i_store_the_alert_text_and_handle_it() {

		WebDriverWait wait = new WebDriverWait(AutomationHooks.driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.alertIsPresent());

		actualAlertText = AutomationHooks.driver.switchTo().alert().getText();
		AutomationHooks.driver.switchTo().alert().accept();

	}

	@When("I close happy birthday popup if avaiable")
	public void i_close_happy_birthday_popup_if_avaiable() {
		// presence of element
		if (AutomationHooks.driver.findElements(By.xpath("//div[@class='closeDlgIframe']")).size() > 0) {
			AutomationHooks.driver.findElement(By.xpath("//div[@class='closeDlgIframe']")).click();
		}

	}

	@Then("I should have the alert text contains {string}")
	public void i_should_have_the_alert_text_contains(String expectedAlertText) {

		Assert.assertTrue("Alert should contain", actualAlertText.contains(expectedAlertText));
	}

	@Then("I should get the added patient name as {string}")
	public void i_should_get_the_added_patient_name_as(String expectedPatientName) {
		AutomationHooks.driver.switchTo().frame(AutomationHooks.driver.findElement(By.xpath("//iframe[@name='pat']")));
		String actualPatientName = AutomationHooks.driver.findElement(By.xpath("//h2[contains(text(),'Medical Rec')]"))
				.getText();

		Assert.assertEquals(expectedPatientName, actualPatientName);
	}

}