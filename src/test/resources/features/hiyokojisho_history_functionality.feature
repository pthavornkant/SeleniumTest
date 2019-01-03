# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.
Feature: Verify tests relating to the history button

  Background:
    Given I am on the home page of "http://www.hiyokojisho.com"

  @hiyoko @noJP @smoke @regression
  Scenario: By default, the history widget is not expanded
    Then The history widget "should not" be expanded

  @hiyoko @noJP @smoke @regression
  Scenario: Clicking on the history widget should expand it, clicking it again should make it go away
    When I click on the "History" button
    Then The history widget "should" be expanded
    And I should see "Your Search History" displayed in history
    Then I click on the "expanded History" button
    Then The history widget "should not" be expanded

  @hiyoko @noJP @regression
  Scenario: Empty history should return "No Results"
    #NOTE: Be sure to code properly to distinguish having no search history vs. literally searching the text "No Results"
    #The difference in code is that the literal search will result in a <p> tag including No Results, otherwise it is contained in a <div>
    When I click on the "History" button
    Then The history widget "should" be expanded
    And I should see "No Results" displayed in history

  @hiyoko @noJP @regression
  Scenario: Performing a search should add a result to the search history
    When I have performed a successful search using the phrase "darkness"
    And I click on the "History" button
    Then I should see "darkness" displayed in history
    Then I clear the search bar
    Then I enter "water" into the "search bar"
    Then I click on the "basic search" button
    Then I should see "water" displayed in history
    And I should see "darkness" displayed in history

  @hiyoko @noJP @regression
  Scenario: Clicking on a result in the history tab takes me to a page with that search query
    When I have performed a successful search using the phrase "darkness"
    Then I clear the search bar
    Then I enter "water" into the "search bar"
    Then I click on the "basic search" button
    And I click on the "History" button
    Then I click on the result stored in history, "darkness"
    Then I am on the page "http://www.hiyokojisho.com/darkness"

  @hiyoko @noJP @regression
  Scenario: Clearing history erases previous results
    When I have performed a successful search using the phrase "darkness"
    And I click on the "History" button
    Then I click on the "Clear History" button
    Then I should see "No Results" displayed in history