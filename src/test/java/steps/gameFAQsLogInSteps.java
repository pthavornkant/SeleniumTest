package steps;

//import com.sun.xml.internal.bind.v2.TODO;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import page.gameFAQsLoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.apache.log4j.Logger;

public class gameFAQsLogInSteps {
    private gameFAQsLoginPage gameFAQsLoginPage;
    //private Logger log = Logger.getLogger(CreateAccountSteps.class);

    //#TODO: Need to edit all sections to match my own tests.

    @Before
    public void testStartUp() {
        gameFAQsLoginPage = new gameFAQsLoginPage();
    }

    @After
    public void testShutDown(Scenario scenario) {
        //log.info("Test shutting down.");
        WebDriver driver = gameFAQsLoginPage.navigationHelper.getWebDriver();
        /*if(scenario.isFailed())
        {
            byte[] screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
            scenario.write("Scenario: failed");
        }
        else
            {
            scenario.write("Scenario: passed");
        }*/
        driver.quit();
    }

    @Given("^I navigated to \"([^\"]*)\"$")
    public void i_navigated_to(String url) {
        // navigationHelper.goTo(url);
        gameFAQsLoginPage.goTo(url);
        System.out.println( "Navigated to " + url );
    }

    @Then("^fill in \"([^\"]*)\" as \"([^\"]*)\"$")
    public void fill_in_as(String field, String input) throws Throwable {
        gameFAQsLoginPage.LoginInfo(field, input);
        System.out.println( "Fields filled in." );
    }

    @Then("^click login$")
    public void clickLogin() {

        gameFAQsLoginPage.clicklogin();
        System.out.println( "Login clicked." );
    }

    @Then("^verify the text (\\d+) is displayed$")
    public void verifyTheTextIsDisplayed(String text) {
        try {
            gameFAQsLoginPage.verifyText(text);
        } catch (Exception e) {
            if (gameFAQsLoginPage.verifyText("User ID does not match expected user ID.")) {
                assert false : ("Please verify you have the appropriate user ID for this test.");
            } else {
                //log.info(e.toString());
            }
        }
    }
}
