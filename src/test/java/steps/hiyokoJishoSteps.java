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
    }

    @Then("^I should see that the title says \"([^\"]*)\"$")
    public void iShouldSeeThatTheTitleSays(String pageTitle) {
        WebDriver driver = hiyokoJishoWebpage.navigationHelper.getWebDriver();
        boolean title_matches = driver.getTitle().toLowerCase().contains(pageTitle);
        assert title_matches;
    }

    @And("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String pageText) {
        boolean text_is_visible = hiyokoJishoWebpage.verifyText(pageText);
        assert text_is_visible;
    }

    @When("^I click on \"([^\"]*)\"$")
    public void iClickOn(String linkText) {
        //CLICKS ON TEXT LINK THAT OPENS NEW TAB, THEN GOES TO THAT NEW TAB.
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
                //Exit from loop
                break;
            }
        }
    }

    @Then("^I am on the page \"([^\"]*)\"$")
    public void iAmOnThePage(String targetURL) {
        hiyokoJishoWebpage.navigationHelper.getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, "w"));
        String currentUrl = hiyokoJishoWebpage.navigationHelper.getCurrentURL();
        boolean now_on_target_page = currentUrl.equals(targetURL);
        assert now_on_target_page;
    }

    @And("^The \"([^\"]*)\" is currently empty$")
    public void theSearchBarIsCurrentlyEmpty(String searchBar_locator) {
        String currentSearchBarText = hiyokoJishoWebpage.getSearchText(searchBar_locator);
        boolean emptybar = currentSearchBarText.isEmpty();
        assert emptybar;
    }

    @Then("^I click on the \"([^\"]*)\" button$")
    public void iClickOnTheButton(String buttonText) {
        hiyokoJishoWebpage.clickButton(buttonText);
    }

    @Then("^I should see \"([^\"]*)\" displayed in search results$")
    public void iShouldSeeDisplayedInSearchResults(String expectedSearchResults)  {
        assert hiyokoJishoWebpage.verifyNoSearchResults(expectedSearchResults);
    }

    @When("^I enter \"([^\"]*)\" into the search bar$")
    public void iEnterIntoTheSearchBar(String textToSearch){
        hiyokoJishoWebpage.enterSearchText(textToSearch);
    }

    @Then("^I should see Heisig Results for the \"([^\"]*)\" phrase: \"([^\"]*)\"$")
    public void iShouldSeeHeisigResultsFor(String language, String textToSearch) {
        boolean heisigResult = (hiyokoJishoWebpage.verifyHeisigSearchResults(language, textToSearch));
        assert heisigResult;
    }

    @And("^I should see Jisho Results for the \"([^\"]*)\" phrase: \"([^\"]*)\"$")
    public void iShouldSeeJishoResultsForTheEnglishPhrase(String language, String textToSearch) {
        boolean jishoResult = (hiyokoJishoWebpage.verifyJishoSearchResults(language, textToSearch));
        assert jishoResult;
    }

    @Given("^I have performed a successful search using the phrase \"([^\"]*)\"$")
    public void iHavePerformedASuccessfulSearchUsingThePhrase(String textToSearch) {
        hiyokoJishoWebpage.goTo("http://www.hiyokojisho.com");
        hiyokoJishoWebpage.enterSearchText(textToSearch);
        hiyokoJishoWebpage.clickButton("basic search");
        boolean heisigResult = (hiyokoJishoWebpage.verifyHeisigSearchResults("English", textToSearch));
        boolean jishoResult = (hiyokoJishoWebpage.verifyJishoSearchResults("English", textToSearch));
        assert heisigResult;
        assert jishoResult;
    }

    @Then("^There should be no search results on the page$")
    public void thereShouldBeNoSearchResultsOnThePage() {
        boolean noResults = hiyokoJishoWebpage.verifyEmptySearchResults();
        assert noResults;
    }

    @Then("^I clear the search bar$")
    public void iClearTheSearchBar() {
        hiyokoJishoWebpage.clearSearchBarText();
    }

    @Then("^\"([^\"]*)\" should be added to the built word search bar$")
    public void shouldBeAddedToTheBuiltWordSearchBar(String addedKanji) {
        boolean kanjiAdded = hiyokoJishoWebpage.verifyBuiltWordSearchBarContains(addedKanji);
        assert kanjiAdded;
    }

    @Then("^The built word search bar should display \"([^\"]*)\"$")
    public void theBuiltWordSearchBarShouldDisplay(String compoundKanji)  {
        boolean kanjiAdded = hiyokoJishoWebpage.verifyBuiltWordSearchBarDisplays(compoundKanji);
        assert kanjiAdded;
    }

    @And("^The \"([^\"]*)\" button should not appear$")
    public void theButtonShouldNotAppear(String button_locator) {
        boolean buttonsAbsent = hiyokoJishoWebpage.buttonIsAbsent(button_locator);
        assert buttonsAbsent;
    }
}
