package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;


//#TODO: There isn't much to modify in this code, but read it over to see what's going on.
// This code creates the driver to connect to a browser and also implements functionality for Selenium keywords.

public class NavigationHelper {

    private WebDriver driver;

    private Logger logger = LogManager.getLogger(NavigationHelper.class);

    public WebDriver getWebDriver() {
        return this.driver;
    }
    public Logger getLogDevice() {return this.logger;}

    public NavigationHelper() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        logger.info("Starting Firefox Driver");
    }

    public void goTo(String url) {
        assert url != null : "URL can not be null";
        logger.info("Navigating to: " + url);
        driver.get(url);
    }

    public void clickElement(By locator) {
        logger.debug("Clicking element: " + locator.toString());
        driver.findElement(locator).click();
    }

    public void input(By locator, String input) {
        logger.info("At Element: "+ locator.toString() + ". Input Value: " + input);
        driver.findElement(locator).sendKeys(input);
    }

    public boolean isElementPresent(By locator) {
        logger.debug("Looking for " + locator.toString());
        WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(locator));
        return element != null;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();

    }

    public void clickElementText(String text) {
        String ElementXPath = "//*[contains(text(),'" + text + "')]";
        if (isElementPresent(By.xpath(ElementXPath))) {
            logger.info("Element found with Text:"+text);
            driver.findElement(By.xpath(ElementXPath)).click();

        } else {
            logger.info("Element not found");
        }
    }

    public void retryClickUntilElementAppear(By clickLocator, By expectedLocator) {
        int retry = 3;
        while (retry > 0 || !isElementPresent(expectedLocator)) {
            clickElement(clickLocator);
            retry--;
        }
    }
}
