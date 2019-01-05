import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features"
        ,monochrome = true
        ,glue = {"steps"}
        ,tags = {"@hiyoko", "@smoke", "@yesJP"}
        ,format = {"json:target/cucumber.json", "html:target/site/cucumber-pretty"}
)

public class runTest
{
    /* This section was to test JUNIT without cucumber.
    @Test
        public void url_check(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://google.com");
        System.out.println("Google launched successfully.");
        driver.close();
        System.out.println("Driver closed.");
    }*/
}
