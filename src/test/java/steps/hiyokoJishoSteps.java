package steps;

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
        hiyokoJisho.goTo(url);
        System.out.println( "Navigated to http://www.hiyokojisho.com" );
    }
}
