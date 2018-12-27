# Comment area. Writing this here for practice purposes.
# Continued comment area. May continue to modify this section later.

Feature: Test suite for HiyokoJisho.
  Run through basic regression tests on HiyokoJisho.

  #Tests relating to external link verification.
  Background:
    Given I am on the home page of "http://www.hiyokojisho.com"

  Scenario: Verify that the title and page text all display correctly.
    Then I should see that the title says <HiyokoJisho>
    And I should see "Intermediate Japanese Word Builder Dictionary"
    And I should see "Enter any Kanji, Heisig Keyword, or English/Japanese sentences in the box above."
    And I should see "Use the 'Build Word' button to create kanji compounds based on your search results."
    And I should see "This site uses some heisig json and the Official Unofficial Jisho.org API"
    And I should see "Issues? New Feature Ideas? Support/Issues"
    And I should see "Hiyoko Jisho Github"
    And I should see "Andrew Tae"

  Scenario: Verify that clicking on "Support/Issues" takes the user to the issue report page for Andrew Tae's github.
    When I click on <Support/Issues>
    Then I am on the support/issues page.

  Scenario: Verify that clicking on "HiyokoJisho Github" takes me to the Jisho Word Builder git page.
    When I click on <HiyokoJisho Github>
    Then I am on the Jisho Word Builder git page.

  Scenario: Verify that clicking on "Andrew Tae" takes me to the github page of the creator.
    When I click on <Andrew Tae>
    Then I am on the git hub page for Andrew Tae.