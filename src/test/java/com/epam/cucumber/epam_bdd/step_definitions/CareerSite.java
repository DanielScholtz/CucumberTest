package com.epam.cucumber.epam_bdd.step_definitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class CareerSite {
    WebDriver driver;

    @Before
    public void browserSetup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Given("the EPAM Career website is loaded")
    public void openingCareerSite() {
        driver.get("https://www.epam.com/careers");
    }

    @When("the (.*) entered")
    public void keywordIsEntered(String keyword) {
        driver.findElement(By.cssSelector("input[class^='job-search__input']")).sendKeys(keyword);
    }

    @And("the Find button is clicked")
    public void findButtonIsClicked() {
        driver.findElement(By.cssSelector(".job-search__submit")).click();
    }

    @Then("(.*) description should be displayed")
    public void showOpenPositions(String position) {
        driver.getPageSource().contains(position);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
