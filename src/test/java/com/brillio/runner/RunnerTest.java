package com.brillio.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
//		features = {"src/test/resources/feature/Login.feature",
//				"src/test/resources/feature/Patient.feature"}
		features = {"src/test/resources/feature"}
		,glue = {"com.brillio.stepdefinitions","com.brillio.base"}
		//,dryRun = true
		,monochrome = true
		//,publish = false
		,plugin = {"html:target/cucumber-report.html","json:target/cucumber-report.json"}
		,tags ="@addpatient"
		)

@RunWith(Cucumber.class)
public class RunnerTest {

}