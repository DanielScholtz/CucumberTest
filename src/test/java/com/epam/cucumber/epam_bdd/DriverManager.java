package com.epam.cucumber.epam_bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverManager {
    WebDriver driver;

    public static WebDriver getDriver(WebDriver driver) {
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            return driver;
        }
        return driver;
    }

    public static WebDriverWait getWait(WebDriver driver, WebDriverWait wait) {
        return wait = new WebDriverWait(driver, 60);
    }

}
