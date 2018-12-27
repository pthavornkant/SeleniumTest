package page;

import helpers.NavigationHelper;
import org.openqa.selenium.By;

public class hiyokoJisho {

    /*#TODO: Modify this page to match HiyokoJisho (http://www.hiyokojisho.com/)
    * #TODO: Click on elements (search, clear, history, etc.)*/

    public NavigationHelper navigationHelper = new NavigationHelper();

    public void goTo(String url){
        //Go to URL.
        navigationHelper.goTo(url);
    }

    public boolean verifyTextonPage(String text){
        //Verify text exists on page.
        return navigationHelper.isElementPresent(By.xpath("//*[contains(text(),'"+text+"')]"));
    }
}
