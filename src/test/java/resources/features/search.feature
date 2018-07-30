Feature: Searching for a job at Epam

  As a user
  I want to visit the EPAM career site
  So that I can apply for a job

  Scenario Outline: CAREER_1 - Searching for Test Automation
    Given the EPAM Career website is loaded
    When the <keyword> entered
    And the Find button is clicked
    Then <description> description should be displayed
    Examples:
      | keyword         | description                                             |
      | Test Automation | Currently we are looking for a Test Automation Engineer |
      | Java Developer  | Currently we are looking for a Java Developer           |