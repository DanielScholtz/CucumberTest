package com.epam.cucumber.epam_bdd.step_definitions;

import com.epam.cucumber.epam_bdd.DriverManager;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@RunWith(Cucumber.class)
@Cucumber.Options(glue = "step_definitions", features = "src/test/java/resources/features")
public class AcceptanceTest {
}
