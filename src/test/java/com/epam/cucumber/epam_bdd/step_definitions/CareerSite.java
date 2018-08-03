package com.epam.cucumber.epam_bdd.step_definitions;

import com.epam.cucumber.epam_bdd.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    private List<WebElement> searchResult;

    @FindBy(css = "*[data-value*='time']")
    private WebElement sortByDate;

    @FindBy(css = ".selected-params selected")
    private WebElement selectedSkillsTabArrow;

    @FindBy(css = "li.search-result__sorting-item:nth-child(1)")
    private WebElement relevanceButton;

    @FindBy(css = ".checkbox-custom-label")
    private List<WebElement> skillList;

    @FindBy(css = "*[class='optgroup']")
    private List<WebElement> countryList;

    @FindBy(css = "*[role='treeitem']")
    private List<WebElement>cityList;

    private WebDriver driver;
    private WebDriverWait wait;
    private Logger log = LoggerFactory.getLogger(CareerSite.class);
    private DriverManager drivermanager = new DriverManager();

    public CareerSite(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Before
    public void initBrowser() {
        driver = drivermanager.getDriver(driver, "Firefox");
        wait = drivermanager.getWait(driver, wait);
    }

    @Given("the EPAM Career website is loaded")
    public void openingCareerSite() {
        driver.get("https://www.epam.com/careers");
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
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
    }

    @When("choosing (.*) as country and (.*) as city for location")
    public void location(String country, String city) {
        locationArrow.click();
        for (WebElement countryElement : countryList) {
            if (countryElement.getText().contains(country)) {
                if (country.length() < 1) {
                    return;
                } else
                    countryElement.click();
                for (WebElement cityElement : cityList) {
                    if(city.equals(country)) {
                        cityElement.getText().contains("all_"+city);
                        cityElement.click();
                    }
                    else {
                        cityElement.click();
                    }
                }
            }
        }
    }

    @When("opening the skills menu")
    public void openSkillTab() {
        skillsTabArrow.click();
    }

    @And("choosing (.*) as a skill")
    public void skills(String text) {
        for (WebElement element : skillList) {
            if (element.getText().contains(text)) {
                if (text.length() < 1) {
                    return;
                } else
                element.click();
            }
        }
    }


    @When("user click on sort by date")
    public void sortJobsByDate() throws StaleElementReferenceException {
        try {
            sortByDate.click();
            wait.until(ExpectedConditions.elementToBeClickable(relevanceButton));
            wait.until(ExpectedConditions.attributeContains(sortByDate, "class", "--active"));
            List<WebElement> allElements = searchResult;
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
        driver.close();
        driver.quit();
    }
}
