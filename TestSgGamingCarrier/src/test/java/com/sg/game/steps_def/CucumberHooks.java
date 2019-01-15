package com.sg.game.steps_def;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;

public class CucumberHooks {
    public static WebDriver driver;

    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumWebdrivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\SeleniumWebdrivers\\geckodriver.exe");
        System.out.println("**************Before Start of Scenario *********************");
         System.out.println("Called openBrowser");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }


    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.write("Scenario failed. Current Page URL is " + driver.getCurrentUrl());
            try{
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }else{
            scenario.write("Scenario passed. Current Page URL is " + driver.getCurrentUrl());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
        System.out.println("***************end of scneario *********************"+scenario.getName());

    }

}