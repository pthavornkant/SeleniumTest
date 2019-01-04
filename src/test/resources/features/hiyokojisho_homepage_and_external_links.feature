# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.
Feature: Verify text & external links on HiyokoJisho

  #Tests relating to external link verification.
  Background:
    Given I am on the home page of "http://www.hiyokojisho.com"

  @hiyoko @noJP @smoke @regression
  Scenario: Verify that the title displays correctly
    Then I should see that the title says "HiyokoJisho"

  @hiyoko @noJP @smoke @regression
  Scenario: Verify that all page text displays properly
    And I should see "Intermediate Japanese Word Builder Dictionary"
    And I should see "Enter any Kanji, Heisig Keyword, or English/Japanese sentences in the box above."
    And I should see "Use the 'Build Word' button to create kanji compounds based on your search results."
    And I should see "This site uses some heisig json and the Official Unofficial Jisho.org API"
    And I should see "Issues? New Feature Ideas?"

  @hiyoko @noJP @smoke @regression @extlinks
  Scenario Outline: Verify that clicking on each external link takes me to the expected external link
    When I click on "<linktext>"
    Then I am on the external page "<URL>"
    Examples:
      | linktext             |           URL                                     |
      | Support/Issues       | https://github.com/atae/jisho-word-builder/issues |
      | Hiyoko Jisho Github  | https://github.com/atae/jisho-word-builder        |
      | Andrew Tae           | https://github.com/atae/                          |