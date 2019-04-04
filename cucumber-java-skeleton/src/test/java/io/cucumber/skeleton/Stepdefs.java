package io.cucumber.skeleton;

import cucumber.api.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class Stepdefs {
	private final WebDriver driver = new ChromeDriver();

	@Given("^I access google$")
	public void I_visit_google() {
		driver.get("https:\\www.google.com");
	}

	@When("^I search about \"(.*)\" and your movie \"(.*)\"$")
	public void search_for(String director, String movie) {
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys(director + " + " + movie);
		element.submit();
	}

	@Then("^I recover the approximate amout of results$")
	public void checkResults() {
		WebElement element = driver.findElement(By.id("resultStats"));
		System.out.println(element.getText());
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}
}
