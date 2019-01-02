# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.

Feature: Test suite for HiyokoJisho.
  Verify tests relating to the word builder.

  #Tests relating to external link verification.
  Background:
    Given I have performed a successful search using the phrase "darkness"

  Scenario: Verify Heisig button: Add to built word.
    When I click on the "Heisig Add to Built Word" button
    Then "暗" should be added to the built word search bar
    Then I clear the search bar
    Then I enter "water" into the search bar
    Then I click on the "basic search" button
    Then I click on the "Heisig Add to Built Word" button
    Then "水" should be added to the built word search bar
    Then The built word search bar should display "暗水"

  Scenario: Verify Heisig button: Add to built NEW word.
    Then I click on the "Heisig Add to New Built Word" button
    Then "暗" should be added to the built word search bar
    Then I clear the search bar
    Then I enter "water" into the search bar
    Then I click on the "basic search" button
    Then I click on the "Heisig Add to New Built Word" button
    Then The built word search bar should display "水"

  Scenario: Verify Heisig button: Search.
    Then I click on the "Search Word" button
    Then I should see Heisig Results for the "Japanese" phrase: "暗"
    And I should see Jisho Results for the "Japanese" phrase: "暗"

  Scenario: Verify Jisho button: Add to built word.
    Then I click on the "Jisho Add to Built Word" button
    Then "闇" should be added to the built word search bar
    Then I clear the search bar
    Then I enter "water" into the search bar
    Then I click on the "basic search" button
    Then I click on the "Jisho Add to Built Word" button
    Then "水" should be added to the built word search bar
    Then The built word search bar should display "闇水"

  Scenario: Verify Jisho button: Add to built NEW word.
    Then I click on the "Jisho Add to New Built Word" button
    Then "闇" should be added to the built word search bar
    Then I clear the search bar
    Then I enter "water" into the search bar
    Then I click on the "basic search" button
    Then I click on the "Jisho Add to New Built Word" button
    Then "水" should be added to the built word search bar
    Then The built word search bar should display "水"

  Scenario: Verify that the Clear button erases results.
    When I click on the "Clear" button
    Then There should be no search results on the page

  Scenario: Verify that the Clear button erases results for built words.
    When I click on the "Heisig Add to Built Word" button
    Then I click on the "Clear Built Word" button
    Then The "built word bar" is currently empty
    And The "Search Built Word" button should not appear
    And The "Clear Words" button should not appear