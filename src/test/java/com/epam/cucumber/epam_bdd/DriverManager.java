package com.epam.cucumber.epam_bdd;

import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DriverManager {
    private WebDriver driver;

    public static WebDriver getDriver(WebDriver driver, String browser) {
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            return driver;
        }
        else
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            return driver;
    }

    public static WebDriverWait getWait(WebDriver driver, WebDriverWait wait) {
        return wait = new WebDriverWait(driver, 20);
    }
}
