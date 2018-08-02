package com.epam.cucumber.epam_bdd.step_definitions;

import com.epam.cucumber.epam_bdd.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.cucumber.epam_bdd.urls.CAREER_URL;

public class CareerSite {

    @FindBy(css = "*[id^='select-box-location-'")
    private WebElement locationArrow;
    @FindBy(css = "*[id$='all_locations'")
    private WebElement defaultLocation;
    @FindBy(css = "input[class^='job-search__input']")
    private WebElement keywordInput;
    @FindBy(css = "*[class*='selected-params']")
    private WebElement skillsTabArrow;
    @FindBy(css = ".job-search__submit")
    private WebElement findButton;
    @FindBy(css = ".search-result__item-name")
    private WebElement searchResult;
    @FindBy(css = "*[data-value*='time']")
    private WebElement sortByDate;
    @FindBy (css = ".selected-params selected")
    private WebElement selectedSkillsTabArrow;
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger log = LoggerFactory.getLogger(CareerSite.class);
    DriverManager drivermanager = new DriverManager();

    @Before
    public void initBrowser() {
        driver = drivermanager.getDriver(driver);
        wait = drivermanager.getWait(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Given("the EPAM Career website is loaded")
    public void openingCareerSite() {
        driver.get(CAREER_URL);
        locationArrow.click();
        defaultLocation.click();
    }

    @When("the (.*) entered")
    public void keywordIsEntered(String text) {
        keywordInput.sendKeys(text);
    }

    @When("the Find button is clicked")
    public void findButtonIsClicked() {
        findButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-result__item-name")));
    }

    @When("choosing (.*) as country and (.*) as city for location")
    public void location(String country, String city) {
        locationArrow.click();
        driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
        if (city.equals(country)) {
            driver.findElement(By.cssSelector("*[id*='all_" + city + "']")).click();
        } else {
            driver.findElement(By.cssSelector("*[id*='" + city + "']")).click();
        }
    }

    @When("opening the skills menu")
    public void openSkillTab() {
        skillsTabArrow.click();
    }

    @And("choosing \"([^\"]*)\" as a skill")
    public void skills(String skill) {
        driver.findElement(By.xpath("//*[@class='checkbox-custom-label' and contains(., '" + skill + "')]")).click();
    }

    @When("user click on sort by date")
    public void sortJobsByDate() throws StaleElementReferenceException {
        try {
            sortByDate.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.search-result__sorting-item:nth-child(1)")));
            List<WebElement> allElements = driver.findElements(
                    By.className("search-result__item-name")
            );
            for (WebElement element : allElements) {
                String idList = element.getAttribute("href");
                log.info(idList);
            }
        } catch (StaleElementReferenceException e) {
            log.error("error in sortJobsByDate", e);
        }
    }

    @Then("(.*) description should be displayed")
    public void showOpenPositions(String position) {
        driver.getPageSource().contains(position);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }


}
