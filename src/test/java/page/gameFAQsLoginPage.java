package page;

import helpers.NavigationHelper;
import org.openqa.selenium.By;

public class gameFAQsLoginPage {

    //#TODO: Modify this section to match the GameFAQs Login Page. Get the XPath for the text fields, login buttons, and get the xpaths for the user ID.

    public NavigationHelper navigationHelper = new NavigationHelper();

    //Sign Up Locators
    private By Email_Input_Name= By.name("EMAILADDR");
    private By Password_Input_Name= By.name("PASSWORD");

    //Sign In Locators
    private By Login_Button_Xpath= By.xpath("//*[@id=\"login_submit\"]");

    public void goTo(String url){
        navigationHelper.goTo(url);
    }

    public void clicklogin(){

        navigationHelper.clickElement(Login_Button_Xpath);
    }

    public void LoginInfo(String field, String input){
        assert field!=null:"Field is null";
        assert input!=null:"Input cannot be null for " + field;

        //reformat field to lowercase
        field=field.toLowerCase().trim();

        if(field.equals("email"))
        {
            navigationHelper.input(Email_Input_Name,input);
        }
        else if(field.equals("password"))
        {
            navigationHelper.input(Password_Input_Name,input);
        }
        else{
            System.out.print("===ERROR===:No valid field is found.");
        }
    }

    public boolean verifyText(String text){
        ////*[@id="content"]/div[2]/div/div/table/tbody[1]/tr[2]/td[2] is the xPath for userID
        return navigationHelper.isElementPresent(By.xpath("//*[contains(text(),'"+text+"')]"));
    }
}
