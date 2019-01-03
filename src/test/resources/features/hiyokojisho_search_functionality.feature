# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.

Feature: Suite to verify HiyokoJisho basic search functionality

  #Tests relating to external link verification.
  Background:
    Given I am on the home page of "http://www.hiyokojisho.com"
    And The "search bar" is currently empty

  @hiyoko @noJP @smoke @regression
  Scenario: Empty Search result returns no results
    Then I click on the "basic search" button
    Then I should see "No results!" displayed in search results

  @hiyoko @noJP @smoke @regression
  Scenario: Non-existent word returns no results
    When I enter "afds" into the "search bar"
    Then I click on the "basic search" button
    Then I should see "No results!" displayed in search results

  @hiyoko @yesJP @smoke @regression
  Scenario Outline: Searching for words returns results for both English and Japanese
    When I enter "<text>" into the "search bar"
    Then I click on the "basic search" button
    Then I should see Heisig Results for the "<language>" phrase: "<text>"
    And I should see Jisho Results for the "<language>" phrase: "<text>"

    Examples:
      | language  | text  |
      | English   | water |
      | Japanese  | 水    |