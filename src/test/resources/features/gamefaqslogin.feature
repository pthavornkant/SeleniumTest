Feature: Logging into GameFAQs as a Test

    @gfaqs
    Scenario Outline: I want to log into GameFAQs and verify my userID.
        Given I navigated to "https://gamefaqs.gamespot.com/user/login"
        Then fill in "Username/Email" as "<Username/Email>"
        Then fill in "Password" as "<Password>"
        Then click login
        Then verify the text 7894524 is displayed

        Examples:
            | Username/Email  | Password  |
            | AutomativeActio | Rokudan64 |