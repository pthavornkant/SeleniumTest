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
    private By baseSearchButton = By.xpath("//div[@class='formButtons']//button[@type='submit']");
    private By baseClearButton = By.xpath("//div[@class='formButtons']//button[@class='clear-search']");

    private By heisigAdd = By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div/div[1]/div/button[1]");
    private By heisigAddNew = By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div/div[1]/div/button[2]");
    private By heisigSearch = By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div/div[1]/div/button[3]");

    private By jishoAdd = By.xpath("//*[@id=\"root\"]/div/div[5]/div[2]/div/div[1]/div[2]/button[1]");
    private By jishoAddNew = By.xpath("//*[@id=\"root\"]/div/div[5]/div[2]/div/div[1]/div[2]/button[2]");

    private By clearBuilt = By.xpath("//div[@class='buttons']//button[@type='button']");
    private By searchBuilt = By.xpath("//div[@class='buttons']//button[@type='submit']");
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

    public void clickLink(String linkText){
        //CLICK ON THE LINK THAT MATCHES THE TEXT OF THE LINK
        if (linkText.equals("Support/Issues")){
            navigationHelper.clickElement(support_and_issues);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (linkText.equals("Hiyoko Jisho Github")){
            navigationHelper.clickElement(hiyokoJishoGitHub);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (linkText.equals("Andrew Tae")){
            navigationHelper.clickElement(andrewTae);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Unidentified link passed in. The text " + linkText + " is not a valid link on HiyokoJisho.");
        }
    }

    public void clickButton (String button_text){
        if (button_text.equals("basic search")){
            navigationHelper.clickElement(baseSearchButton);
        }
        else if (button_text.equals("basic clear")){
            navigationHelper.clickElement(baseClearButton);
        }
        else if (button_text.equals("Heisig Add to Built Word")){
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
        else if (button_text.equals("Clear")){
            navigationHelper.clickElement(baseClearButton);
        }
        else if (button_text.equals("Clear Built Word")){
            navigationHelper.clickElement(clearBuilt);
        }
        else if (button_text.equals("Search Built Word")){
            navigationHelper.clickElement(searchBuilt);
        }


        //Sleep so that I can see the results of clicking the button properly.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterSearchText(String textToSearch) {
        navigationHelper.input(searchBoxLocator, textToSearch);
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

    public boolean buttonIsAbsent(String button_locator) {
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
}
