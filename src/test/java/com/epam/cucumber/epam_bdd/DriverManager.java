package com.epam.cucumber.epam_bdd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {

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
}
