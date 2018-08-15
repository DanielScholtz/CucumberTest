package com.epam.cucumber.epam_bdd.step_definitions;

import com.epam.cucumber.epam_bdd.DriverManager;
import com.epam.cucumber.epam_bdd.pages.CareerSite;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class Step_def {
    private WebDriver driver;
    CareerSite careerSite;
    private DriverManager driverManager = new DriverManager();

    public Step_def() {
        driver = driverManager.getDriver(driver, "Firefox");
    }

    @Before
    public void object() {
        careerSite = new CareerSite(driver);
    }

    @Given("the EPAM Career website is loaded")
    public void Epam_website_is_loaded() {
        careerSite.openingCareerSite();
    }

    @When("the (.*) entered")
    public void the_keyword_entered(String text) {
        careerSite.keywordIsEntered(text);
    }

    @When("the Find button is clicked")
    public void find_button_is_clicked() {
        careerSite.findButtonIsClicked();
    }

    @When("choosing (.*) as country and (.*) as city for location")
    public void choosing_country_and_city(String country, String city) {
        careerSite.location(country, city);
    }

    @When("opening the skills menu")
    public void opening_skills_menu() {
        careerSite.openSkillTab();
    }

    @And("choosing (.*) as a skill")
    public void choosing_skill(String text) {
        careerSite.skills(text);
    }


    @When("user click on sort by date")
    public void sort_by_date() throws StaleElementReferenceException {
        careerSite.sortJobsByDate();
    }

    @Then("(.*) description should be displayed")
    public void description_should_be_displayed(String position) {
        careerSite.showOpenPositions(position);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
