package com.epam.cucumber.epam_bdd.pages;

import com.epam.cucumber.epam_bdd.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class CareerSite {

    @FindBy(css = ".recruiting-search__location")
    private WebElement locationArrow;

    @FindBy(css = "*[id$='all_locations'")
    private WebElement defaultLocation;

    @FindBy(css = "input[class^='recruiting-search__input']")
    private WebElement keywordInput;

    @FindBy(css = "*[class*='selected-params']")
    private WebElement skillsTabArrow;

    @FindBy(css = ".recruiting-search__submit")
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

    private WebDriverWait wait;
    private Logger log = LoggerFactory.getLogger(CareerSite.class);
    private DriverManager drivermanager = new DriverManager();
    private WebDriver driver;
    private static final String COUNTRY_SELECTOR = "[aria-label=\"%s\"]";
    private static final String CITY_SELECTOR = "*[id*='%s']";

      public CareerSite(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
        wait = new WebDriverWait(this.driver, 10);
    }

    public void openingCareerSite() {
        driver.get("https://www.epam.com/careers");
        locationArrow.click();
        defaultLocation.click();
    }

    public void keywordIsEntered(String text) {
        keywordInput.sendKeys(text);
    }

    public void findButtonIsClicked() {
        findButton.click();
    }

    public void location(String country, String city) {
        locationArrow.click();
        driver.findElement(By.cssSelector(String.format(COUNTRY_SELECTOR, country))).click();
        driver.findElement(By.cssSelector(String.format(CITY_SELECTOR, city))).click();
    }

    public void openSkillTab() {
        skillsTabArrow.click();
    }

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

    public void sortJobsByDate() throws StaleElementReferenceException {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
        try {
            sortByDate.click();
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

    public void showOpenPositions(String position) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
        driver.getPageSource().contains(position);
    }
}
