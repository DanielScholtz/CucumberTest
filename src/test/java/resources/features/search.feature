Feature: Searching for a job at Epam

  As a user
  I want to visit the EPAM career site
  So that I can apply for a job

  Background:
    Given the EPAM Career website is loaded

  @smoke
  @JobsWithTAKeyword
  Scenario Outline: CAREER_1.1 - Searching for Test Automation
    When the <keyword> entered
    And the Find button is clicked
    Then <description> description should be displayed
    Examples:
      | keyword         | description                                             |
      | Test Automation | Currently we are looking for a Test Automation Engineer |
      | Java Developer  | Currently we are looking for a Java Developer           |

    @smoke
    @JobsWithSpecificLocation
    Scenario Outline: CAREER_1.2 - Searching jobs in different locations
      When choosing <country> as country and <city> as city for location
      And the Find button is clicked
      Then <description> description should be displayed
      Examples:
        | country | city     | description                                            |
        | Hungary | Debrecen | for our Debrecen office to make the team even stronger |
        | Poland  | Warsaw   |  for our Warsaw office to make the team even stronger  |

      @smoke
      @JobsWithQASkills
      Scenario: CAREER_1.3 - Searching jobs with Test Engineer skill
        When opening the skills menu
        And choosing Software Test Engineer as a skill
        And the Find button is clicked
        Then Test Engineer description should be displayed

        @smoke
        @KeywordLocationSkillsCombinedSearch
        Scenario Outline: CAREER_1.4 - Searching for specific job in different with different skills
          When the <keyword> entered
          And choosing <country> as country and <city> as city for location
          And opening the skills menu
          And choosing <skill> as a skill
          And the Find button is clicked
          Then <description> description should be displayed
          Examples:
            | keyword         | country       | city     | skill                              | description                                                                   |
            | Test Automation | Belarus       | Mogilev  | Software Test Engineering          | Test Automation Engineer in .NET to join our team in Mogilev                  |
            | Manager         | United States | USA      | Consulting & Business Analysis     | You are strategic, resilient, engaging with people and a natural self-starter |
            | Java            | Hungary       | Budapest |                                    | Currently we are looking for Junior Java Developers for our Budapest office   |

          @smoke
          @SortByDate
          Scenario: CAREER_1.5 - Sort jobs by date
            When the Find button is clicked
            And user click on sort by date
            Then Senior iOS Engineer description should be displayed