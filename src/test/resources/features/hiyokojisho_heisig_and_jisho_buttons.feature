# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.

Feature: Test suite for HiyokoJisho.
  Verify tests relating to the word builder.

  #Tests relating to external link verification.
  Background:
    Given I have performed a successful search.

  Scenario: Verify Heisig button: Add to built word.
    Then I click on the "Heisig Add to Built Word" button
    Then The built word search bar should now include the first Heisig result

  Scenario: Verify Heisig button: Add to built NEW word.
    Then I click on the "Heisig Add to New Built Word" button
    Then The built word search bar should now only include the first Heisig result

  Scenario: Verify Heisig button: Search.
    Then I click on the "Search Word" button
    Then I should see Heisig Results for <English>
    And I should see Jisho Results for <English>

  Scenario: Verify Jisho button: Add to built word.
    Then I click on the "Jisho Add to Built Word" button
    Then The built word search bar should now include the first Jisho result

  Scenario: Verify Jisho button: Add to built NEW word.
    Then I click on the "Jisho Add to New Built Word" button
    Then The built word search bar should now only include the first Jisho result

  Scenario: Verify that the Clear button erases results.
    When I click on the "Clear" button
    Then There should be no search results on the page.

    Scenario: Verify that the Clear button erases results for built words.
      When I click on the "Heisig Add to Built Word" button
      Then I click on the "Clear Built Word" button
      Then Built words should display an empty box
      And The Built Word button should not appear
      And The Clear Built Word button should not appear