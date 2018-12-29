package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import page.hiyokoJisho;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

//assert driver.getTitle().toLowerCase().contains("text");

public class hiyokoJishoSteps {

    private hiyokoJisho hiyokoJishoWebpage;

    @Before
    public void testStartUp() {

        hiyokoJishoWebpage = new hiyokoJisho();
    }

    @After
    public void testShutDown(Scenario scenario) {
        //Shut off the driver, ending tests.
        System.out.println("Tests shutting down.");
        WebDriver driver = hiyokoJishoWebpage.navigationHelper.getWebDriver();
        driver.quit();
    }

    @Given("^I am on the home page of \"([^\"]*)\"$")
    public void iAmOnTheHomePageOfHiyokoJisho(String url) {
        hiyokoJishoWebpage.goTo(url);
        System.out.println( "Navigated to http://www.hiyokojisho.com" );
    }

    @Then("^I should see that the title says \"([^\"]*)\"$")
    public void iShouldSeeThatTheTitleSays(String pageTitle) {
        WebDriver driver = hiyokoJishoWebpage.navigationHelper.getWebDriver();
        assert driver.getTitle().toLowerCase().contains(pageTitle);
        System.out.println( "Title says " + driver.getTitle() );
    }

    @And("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String pageText) {
        System.out.println("Text to verify is " + pageText);
        try {
            assert hiyokoJishoWebpage.verifyText(pageText);
            System.out.println("Result is " + hiyokoJishoWebpage.verifyText(pageText));
        } catch (Exception e) {
            System.out.println("The text: " + pageText + "... is not present on the page.");
            System.out.println("Result is " + hiyokoJishoWebpage.verifyText(pageText));
        }
    }

    @When("^I click on \"([^\"]*)\"$")
    public void iClickOn(String linkText) {
        String currentUrl = hiyokoJishoWebpage.navigationHelper.getCurrentURL();
        System.out.print("Click Link Step - Current URL before clicking is: " + currentUrl);
        hiyokoJishoWebpage.clickLink(linkText);
        //NOTE: MAY WANT TO ADD A WAIT COMMAND HERE TO ENSURE THE PROGRAM DOES NOT GO TOO QUICKLY
    }

    @Then("^I am on the page \"([^\"]*)\"$")
    public void iAmOnThePage(String targetURL) {
        System.out.print("Target URL is " + targetURL);
        String currentUrl = hiyokoJishoWebpage.navigationHelper.getCurrentURL();
        System.out.print("Current URL is " + currentUrl);
        System.out.print("Test result is: " + currentUrl.equals(targetURL));
    }
}
