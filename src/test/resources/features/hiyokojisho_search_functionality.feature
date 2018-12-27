# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.

Feature: Test suite for HiyokoJisho.
  Run through basic regression tests on HiyokoJisho.

  #Tests relating to external link verification.
  Background:
    Given I am on the home page of "http://www.hiyokojisho.com"

  Scenario: Empty Search result returns no results.

  Scenario: Non-existant word returns no results.

  Scenario: Searching for English returns results.

  Scenario: Searching with kanji returns results.

  Scenario: Clear button erases results.

  Scenario: Verify search results displays Heisig results.

  Scenario: Verify Heisig button: Add to built word.

  Scenario: Verify Heisig button: Add to built NEW word.

  Scenario: Verify Heisig button: Search.

  Scenario: Verify search results displays Jisho results.

  Scenario: Verify Jisho button: Add to built word.

  Scenario: Verify Jisho button: Add to built NEW word.

  Scenario: Verify Jisho button: Search.