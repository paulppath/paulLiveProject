@HomeScenarios
Feature: Homepage scenarios

  @AD3
  Scenario: Under the homePage, verify that address and phone number
            under the main navigation bar on top of the page is displayed
    #verify first line of text
  Then verify "10090 Main Street" text is displayed
    #verify second line of text
  And verify "Fairfax, VA, USA" text is displayed
  And verify that text is contain "703-831-3217"

  @AD5
  Scenario: Verify the title of the homepage
  Then Verify the title is "Advance Systems - Home"

