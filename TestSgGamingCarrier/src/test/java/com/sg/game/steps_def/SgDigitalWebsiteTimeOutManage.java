package com.sg.game.steps_def;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by HP on 15/1/2019.
 */
public class SgDigitalWebsiteTimeOutManage {
    public static void waitForSeconds(WebDriver driver,long seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
    public static void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
                }
                catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            Thread.sleep(specifiedTimeout*1000);
            boolean b = wait.until(jQueryLoad) && wait.until(jsLoad);
            System.out.println(" PAge load is complete "+b);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
