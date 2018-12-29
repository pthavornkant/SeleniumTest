package page;

import helpers.NavigationHelper;
import org.openqa.selenium.By;

public class hiyokoJisho {

    /*#TODO: Modify this page to match HiyokoJisho (http://www.hiyokojisho.com/)
     * #TODO: Click on elements (search, clear, history, etc.)*/

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

    //LOCATORS END

    public void goTo(String url){
        //Go to URL.
        navigationHelper.goTo(url);
    }

    public boolean verifyText(String text){
        //Verify text exists on page.
        System.out.println("Now verifying: " + text);
        return navigationHelper.isElementPresent(By.xpath("//*[contains(text(),'" + text + "')]"));
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
            System.out.println("Clicked on " + linkText);
        }
        else if (linkText.equals("Hiyoko Jisho Github")){
            navigationHelper.clickElement(hiyokoJishoGitHub);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Clicked on " + linkText);
        }
        else if (linkText.equals("Andrew Tae")){
            navigationHelper.clickElement(andrewTae);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Clicked on " + linkText);
        }
        else {
            System.out.println("Unidentified link passed in. The text " + linkText + "was considered unidentified.");
        }
    }
}
