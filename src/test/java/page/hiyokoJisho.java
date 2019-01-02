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
    private By searchBoxLocator = By.xpath("//*[@id=\"root\"]/div/div[1]/form/input");
    private By baseSearchButton = By.xpath("//div[@class='formButtons']//button[@type='submit']");
    private By baseClearButton = By.xpath("//div[@class='formButtons']//button[@class='clear-search']");

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

    public String getSearchText(){
        WebElement searchBox = navigationHelper.getWebDriver().findElement(searchBoxLocator);
        String searchText = searchBox.getAttribute("value");
        return searchText;
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
            System.out.println(textVerificationResult);

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
}
