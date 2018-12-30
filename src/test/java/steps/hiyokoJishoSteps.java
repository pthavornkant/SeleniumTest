package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import page.hiyokoJisho;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Set;

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
        System.out.println( "Title says " + driver.getTitle() );
        assert driver.getTitle().toLowerCase().contains(pageTitle);
    }

    @And("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String pageText) {
        System.out.println("Text to verify is " + pageText);
        /*try {
            assert hiyokoJishoWebpage.verifyText(pageText);
            System.out.println("Result is " + hiyokoJishoWebpage.verifyText(pageText));
        } catch (Exception e) {
            System.out.println("The text: " + pageText + "... is not present on the page.");
            System.out.println("Result is " + hiyokoJishoWebpage.verifyText(pageText));
        }*/
        System.out.println("Result is " + hiyokoJishoWebpage.verifyText(pageText));
        assert hiyokoJishoWebpage.verifyText(pageText);
    }

    @When("^I click on \"([^\"]*)\"$")
    public void iClickOn(String linkText) {
        String currentUrl = hiyokoJishoWebpage.navigationHelper.getCurrentURL();
        System.out.print("Click Link Step - Current URL before clicking is: " + currentUrl);

        //Storing current window's handle
        String strFirstWindowHandle = hiyokoJishoWebpage.navigationHelper.getWebDriver().getWindowHandle();

        hiyokoJishoWebpage.clickLink(linkText);

        //Wait for page to load.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Storing the collection of all opened windows (in our case it would be 2)
        Set<String> setWindowHandles = hiyokoJishoWebpage.navigationHelper.getWebDriver().getWindowHandles();
        //Iterating over all windows handles
        for(String strWindowHandle: setWindowHandles){
            //If the window handle is not same as the one stored before opening up second window, it is the new window
            if(!strWindowHandle.equals(strFirstWindowHandle)){
                //Switch to the new window
                hiyokoJishoWebpage.navigationHelper.getWebDriver().switchTo().window(strWindowHandle);
                //Print window title
                System.out.println(hiyokoJishoWebpage.navigationHelper.getWebDriver().getTitle());
                //Exit from loop
                break;
            }
        }
    }

    @Then("^I am on the page \"([^\"]*)\"$")
    public void iAmOnThePage(String targetURL) {
        //System.out.println("Target URL is " + targetURL);
        hiyokoJishoWebpage.navigationHelper.getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, "w"));
        String currentUrl = hiyokoJishoWebpage.navigationHelper.getCurrentURL();
        //System.out.println("Current URL is " + currentUrl);
        //System.out.println("Test result is: " + currentUrl.equals(targetURL));

        assert currentUrl.equals(targetURL);
    }
}
