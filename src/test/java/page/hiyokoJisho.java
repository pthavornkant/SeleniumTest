package page;

import helpers.NavigationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class hiyokoJisho {
    public NavigationHelper navigationHelper = new NavigationHelper();
    //LOCATORS START
    private By support_and_issues = By.linkText("Support/Issues");
    private By hiyokoJishoGitHub = By.linkText("Hiyoko Jisho Github");
    private By andrewTae = By.linkText("Andrew Tae");
    /* CORRESPONDING XPATHS FOR THE ABOVE ByLink LOCATORS
     * private By support_and_issues = By.xpath("//*[@id=\"root\"]/div/div[7]/p[2]/a");
     * private By hiyokoJishoGitHub = By.xpath("//*[@id=\"root\"]/div/div[7]/a[1]");
     * private By andrewTae = By.xpath("//*[@id=\"root\"]/div/div[7]/a[2]");
     * */
    private By searchBoxLocator = By.xpath("//form[@class='search']//input");
    private By bwSearchBoxLocator = By.xpath("//div[@class='builtWord']//input[@type='text']");

    //Buttons
    private By heisigAdd = By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div/div[1]/div/button[1]");
    private By heisigAddNew = By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div/div[1]/div/button[2]");
    private By heisigSearch = By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div/div[1]/div/button[3]");

    private By jishoAdd = By.xpath("//*[@id=\"root\"]/div/div[5]/div[2]/div/div[1]/div[2]/button[1]");
    private By jishoAddNew = By.xpath("//*[@id=\"root\"]/div/div[5]/div[2]/div/div[1]/div[2]/button[2]");

    private By clearBuilt = By.xpath("//div[@class='buttons']//button[@type='button']");
    private By searchBuilt = By.xpath("//div[@class='buttons']//button[@type='submit']");

    private By historyList = By.xpath("//div[@class='history-widget fadeInRight']");
    private By clearHistoryButton = By.xpath("//div[@class='history-item history-item-button']//p[text()[contains(., 'Clear History')]]");
    //End Buttons

    //LOCATORS END

    public void goTo(String url){
        //Go to URL.
        navigationHelper.goTo(url);
    }

    public boolean verifyNoSearchResults (String expectedSearchResults){
        System.out.println("Verifying text: " + expectedSearchResults);
        boolean textVerificationResult = navigationHelper.isElementPresent(By.xpath("//h3[contains(text(), '" + expectedSearchResults + "')]"));
        System.out.println(textVerificationResult);

        return textVerificationResult;
    }

    public boolean verifyText(String pageText){
        //Verify text exists on page.
        System.out.println("Now verifying: " + pageText);
        return navigationHelper.isElementPresent(By.xpath("//*[contains(text(),\"" + pageText + "\")]"));
        //return navigationHelper.isElementPresent(By.xpath("//*[contains(text(),'Use the 'Build Word' button to create kanji compounds based on your search results.')]"));
    }

    public String getSearchText(String sb_locator){
        if (sb_locator.equals("search bar")) {
            WebElement searchBox = navigationHelper.getWebDriver().findElement(searchBoxLocator);
            String searchText = searchBox.getAttribute("value");
            return searchText;
        }
        else if (sb_locator.equals("built word bar")){
            WebElement searchBox = navigationHelper.getWebDriver().findElement(bwSearchBoxLocator);
            String searchText = searchBox.getAttribute("value");
            return searchText;
        }
        else return "faulty locator";
    }

    public void clickHiyokoJishoExternalLink(String linkText){
        //CLICK ON THE LINK THAT MATCHES THE TEXT OF THE LINK
        By externalLink = By.xpath("//a[contains(@rel,'noreferrer noopener') and contains(text(),'" + linkText + "')]");

        if (navigationHelper.isElementClickable(externalLink)){
            navigationHelper.clickElement(externalLink);
        }
        else {
            navigationHelper.getLogDevice().error("Invalid link text. " + linkText + " is not valid, clickable text on HiyokoJisho.");
        }
    }

    public void clickHiyokoJishoButton(String button_text){
        //For the Heisig and Jisho Add/Search buttons, their locators are a bit wonky without also adding in another search parameter,
        // but we can still handle them here, albeit a bit messily.
        if (button_text.equals("Heisig Add to Built Word")){
            navigationHelper.clickElement(heisigAdd);
        }
        else if (button_text.equals("Heisig Add to New Built Word")){
            navigationHelper.clickElement(heisigAddNew);
        }
        else if (button_text.equals("Search Word")){
            navigationHelper.clickElement(heisigSearch);
        }
        else if (button_text.equals("Jisho Add to Built Word")){
            navigationHelper.clickElement(jishoAdd);
        }
        else if (button_text.equals("Jisho Add to New Built Word")){
            navigationHelper.clickElement(jishoAddNew);
        }
        else if (button_text.equals("Clear History")){
            //this looks like a button, but was coded as a link, so we handle it differently
            navigationHelper.clickElement(clearHistoryButton);
        }
        else {//The locator naming is intuitive, the button properly contains the text within the button.
            By buttonLocator = By.xpath("//button[contains(text(), '" + button_text + "')]");
            navigationHelper.clickElement(buttonLocator);
        }

        //Sleep so that I can see the results of clicking the button properly.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterSearchText(String textToSearch, String searchBarLocator) {
        if (searchBarLocator.equals("search bar")){
            navigationHelper.input(searchBoxLocator, textToSearch);
        }
        else if (searchBarLocator.equals("built word search bar")){
            navigationHelper.input(bwSearchBoxLocator, textToSearch);
        }

    }

    public boolean verifyHiyokoJishoTitle(String pageTitle) {
        boolean expectedTitle = false;
        if ((pageTitle.toLowerCase()).equals(navigationHelper.getWebDriver().getTitle().toLowerCase().contains(pageTitle.toLowerCase())))
        {
            expectedTitle = true;
        }
        return expectedTitle;
    }

    public boolean verifyHeisigSearchResults(String language, String textToSearch) {
        if (language.equals("English")) {
            System.out.println("Verifying text: " + textToSearch);
            boolean textVerificationResult = navigationHelper.isElementPresent(By.xpath("//div[@class='animated fadeIn']//p[text()='" + textToSearch + "']"));
            System.out.println(textVerificationResult);

            return textVerificationResult;
        }

        else if (language.equals("Japanese")){
            System.out.println("Verifying text: " + textToSearch);
            boolean textVerificationResult = navigationHelper.isElementPresent(By.xpath("//div[@class='animated fadeIn']//h2[text()[contains(., '" + textToSearch + "')]]"));
            System.out.println(textVerificationResult);

            return textVerificationResult;
        }
        else return false;
    }

    public boolean verifyJishoSearchResults(String language, String textToSearch) {
        if (language.equals("English")) {
            System.out.println("Verifying text: " + textToSearch);
            boolean textVerificationResult = navigationHelper.isElementPresent(By.xpath("//div[@class='sense-entry' and text()[contains(., '" + textToSearch + "')]]"));
            //System.out.println(textVerificationResult);

            return textVerificationResult;
        }
        else if (language.equals("Japanese")){
            System.out.println("Verifying text: " + textToSearch);
            boolean textVerificationResult = navigationHelper.isElementPresent(By.xpath("//div[@class='entry-word']//h2[text()[contains(., '" + textToSearch + "')]]"));
            System.out.println(textVerificationResult);

            return textVerificationResult;
        }
        else return false;
    }

    public boolean verifyEmptySearchResults() {
        //Verify that the Heisig Search Results container and Jisho Search Results containers are not present.
        //These locators should not exist on the page if you press the clear button.
        if (navigationHelper.getWebDriver().findElements(By.xpath("//div[@class='container animated fadeIn']//div[@class='heisig']")).size() < 1 &&
                navigationHelper.getWebDriver().findElements(By.xpath("//div[@class='container animated fadeIn']//h3[@class='jisho']")).size() < 1){
            return true;
        }
        else return false;
    }

    public void clearSearchBarText() {
        navigationHelper.getWebDriver().findElement(searchBoxLocator).clear();
    }

    public boolean verifyBuiltWordSearchBarContains(String addedKanji) {
        String bwBoxText = navigationHelper.getWebDriver().findElement(bwSearchBoxLocator).getText();
        if (bwBoxText.contains(addedKanji)) {
            return true;
        }
        else return false;
    }

    public boolean verifyBuiltWordSearchBarDisplays(String single_or_compound_kanji) {
        String bwBoxText = navigationHelper.getWebDriver().findElement(bwSearchBoxLocator).getText();
        if (bwBoxText.equals(single_or_compound_kanji)) {
            return true;
        }
        else return false;
    }

    public boolean builtWordButtonIsAbsent(String button_locator) {
        if (button_locator.equals("Search Built Word")){
            if (navigationHelper.getWebDriver().findElements(clearBuilt).size() < 1){
                return true;
            }
            else return false;
        }
        else if (button_locator.equals("Clear Words")){
            if (navigationHelper.getWebDriver().findElements(searchBuilt).size() < 1){
                return true;
            }
            else return false;
        }
        else return false;
    }

    public boolean checkHistoryExpansion(String expanded) {
        boolean expansion = false;
        if (expanded.equals("should not"))
        {
            if (navigationHelper.getWebDriver().findElements(historyList).size() < 1){
                expansion = true; //Actually means not expanded, but labeling as true for coding purposes.
            }
        }
        else if (expanded.equals("should")){
            if (navigationHelper.getWebDriver().findElements(historyList).size() > 0){
                expansion = true; //Means we have expanded history list.
            }
        }
        return expansion;
    }

    public boolean verifyHistoryContains(String historic_result) {
        boolean result_exists = false;
        if (historic_result.equals("No Results")){
            if (navigationHelper.getWebDriver().findElements(By.xpath("//div[@class='history-item' and contains(text(), ' No Results')]")).size() > 0){
                result_exists = true;
            }
            else {
                System.out.print(navigationHelper.getWebDriver().findElement(By.xpath("//div[@class='history-item' and contains(text(), ' No Results')]")));
            }
        }
        else if (historic_result.equals("Your Search History")){
            if (navigationHelper.getWebDriver().findElements(By.xpath("//div[@class='history-item history-item-header']//p//strong[text()[contains(., '" + historic_result + "')]]")).size() > 0){
                result_exists = true;
            }
            else {
                System.out.print(navigationHelper.getWebDriver().findElement(By.xpath("//div[@class='history-item' and contains(text(), ' No Results')]")));
            }
        }
        else { //we are looking for an actual result
            if (navigationHelper.getWebDriver().findElements(By.xpath("//div[@class='history-item history-item-button']//p[text()[contains(., '" + historic_result + "')]]")).size() > 0){
                result_exists = true;
            }
            else {
                System.out.print(navigationHelper.getWebDriver().findElement(By.xpath("//div[@class='history-item history-item-button']//p[text()[contains(., '" + historic_result + "')]]")));
            }
        }

        return result_exists;
    }

    public void clickHistoryResult(String historic_result) {
        navigationHelper.getWebDriver().findElement(By.xpath("//div[@class='history-item history-item-button']//p[text()[contains(., '" + historic_result + "')]]")).click();
        //Sleep so that I can see the results of clicking the button properly.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
