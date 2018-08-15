package com.epam.cucumber.epam_bdd.step_definitions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(glue = "step_definitions", features = "src/test/java/resources/features")
public class AcceptanceTest {
}
