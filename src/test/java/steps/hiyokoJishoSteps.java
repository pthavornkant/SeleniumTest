package steps;

import cucumber.api.java.en.Given;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import page.hiyokoJisho;
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
        WebDriver driver = hiyokoJishoWebpage.navigationHelper.getWebDriver();
        hiyokoJishoWebpage.navigationHelper.getLogDevice().info("Closing web driver.");
        driver.quit();
        hiyokoJishoWebpage.navigationHelper.getLogDevice().info("Web driver closed.");
    }

    @Given("^I am on the home page of \"([^\"]*)\"$")
    public void iAmOnTheHomePageOfHiyokoJisho(String url) {
        hiyokoJishoWebpage.goTo(url);
    }

    @Given("^I have performed a successful search using the phrase \"([^\"]*)\"$")
    public void iHavePerformedASuccessfulSearchUsingThePhrase(String textToSearch) {
        hiyokoJishoWebpage.goTo("http://www.hiyokojisho.com");
        hiyokoJishoWebpage.enterSearchText(textToSearch, "search bar");
        hiyokoJishoWebpage.clickHiyokoJishoButton(" Search");
        boolean heisigResult = (hiyokoJishoWebpage.verifyHeisigSearchResults("English", textToSearch));
        boolean jishoResult = (hiyokoJishoWebpage.verifyJishoSearchResults("English", textToSearch));
        assert heisigResult;
        assert jishoResult;
    }

    @Then("^I should see that the title says \"([^\"]*)\"$")
    public void iShouldSeeThatTheTitleSays(String pageTitle) {
        boolean isPageTitleCorrect = hiyokoJishoWebpage.verifyHiyokoJishoTitle(pageTitle);
        assert isPageTitleCorrect;
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

        hiyokoJishoWebpage.clickHiyokoJishoExternalLink(linkText);

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
        String currentUrl = hiyokoJishoWebpage.navigationHelper.getCurrentURL();
        boolean now_on_target_page = currentUrl.equals(targetURL);
        hiyokoJishoWebpage.navigationHelper.getLogDevice().debug("Testing if " + targetURL + " has been reached, result is: " + now_on_target_page);

        assert now_on_target_page;
    }

    @Then("^I am on the external page \"([^\"]*)\"$")
    public void iAmOnTheExternalPage(String targetURL) {
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
        hiyokoJishoWebpage.clickHiyokoJishoButton(buttonText);
    }

    @Then("^I should see \"([^\"]*)\" displayed in search results$")
    public void iShouldSeeDisplayedInSearchResults(String expectedSearchResults)  {
        assert hiyokoJishoWebpage.verifyNoSearchResults(expectedSearchResults);
    }

    @When("^I enter \"([^\"]*)\" into the \"([^\"]*)\"$")
    public void iEnterIntoTheSearchBar(String textToSearch, String searchBarLocator){
        hiyokoJishoWebpage.enterSearchText(textToSearch, searchBarLocator);
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
        //We just want to see kanji added to the bar properly, in any order, without regards to prior/future searches.
        System.out.println(hiyokoJishoWebpage.verifyBuiltWordSearchBarContains(addedKanji));
    }

    @Then("^The built word search bar should display \"([^\"]*)\"$")
    public void theBuiltWordSearchBarShouldDisplay(String compoundKanji)  {
        //We want an exact match to verify a specific combination of kanji.
        System.out.println(hiyokoJishoWebpage.verifyBuiltWordSearchBarDisplays(compoundKanji));
    }

    @And("^The \"([^\"]*)\" button should not appear$")
    public void theButtonShouldNotAppear(String button_locator) {
        boolean buttonsAbsent = hiyokoJishoWebpage.builtWordButtonIsAbsent(button_locator);
        assert buttonsAbsent;
    }

    @Then("^The history widget \"([^\"]*)\" be expanded$")
    public void theHistoryWidgetBeExpanded(String expanded) {
        boolean isExpanded = hiyokoJishoWebpage.checkHistoryExpansion(expanded);

        assert isExpanded;
    }

    @And("^I should see \"([^\"]*)\" displayed in history$")
    public void iShouldSeeDisplayedInHistory(String historic_result)  {
        boolean result_found = hiyokoJishoWebpage.verifyHistoryContains(historic_result);

        assert result_found;

    }

    @Then("^I click on the result stored in history, \"([^\"]*)\"$")
    public void iClickOnTheResultStoredInHistory(String historic_result)  {
        hiyokoJishoWebpage.clickHistoryResult(historic_result);
    }
}
