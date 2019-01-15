package com.sg.game.steps_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CareerSearchFunctionalityCheck {

    @Given("^Input Test Data \"(.*?)\" in the search Edit box$")
    public void input_Test_Data_in_the_search_Edit_box(String testData) throws Throwable {
        SgDigitalWebsiteTimeOutManage.waitForSeconds(CucumberHooks.driver,15);
        WebElement element = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//form/input[@type='text']"));
        element.clear();
        element.sendKeys(testData);
        String elementAttribute = element.getAttribute("value");
        Assert.assertTrue("(Input Value "+testData+" ) did not match with test element value ("+elementAttribute+") ",testData.equalsIgnoreCase(elementAttribute));
    }

    @Given("^Trigger Search by clicking Search or press enter key$")
    public void click_Search_or_Trigger_Send_Keys_Enter() throws Throwable {
        WebElement searchBtn = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//form/input[@type='button']"));
        searchBtn.click();
        SgDigitalWebsiteTimeOutManage.waitForSeconds(CucumberHooks.driver,20);
    }


    @Then("^Verify the results for given \"([^\"]*)\" and Location \"([^\"]*)\" and Keyword \"([^\"]*)\"$")
    public void verifyTheResultsForGivenAndLocationAndKeywordAndExpectedStatus(String expectedJobText, String expectedLocation,
                                                                               String keyWord) throws Throwable {
        System.out.println("Verifying the test data for (expectedJobText :"+expectedJobText
                    +") (expectedLocation   :"+expectedLocation+") (keyWord :"+keyWord+")");

        SgDigitalWebsiteTimeOutManage.waitForPageLoadComplete(CucumberHooks.driver,20);

        //Compare the job
        WebElement jobSearchElement = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//form/input[@type='text']"));
        String actualJobText = jobSearchElement.getAttribute("value").trim();
        Assert.assertTrue("(Job Search value :"+actualJobText+") did not match with (expected outout :"+expectedJobText+")"
                , expectedJobText.trim().equalsIgnoreCase(actualJobText));

        //Compare the location
        WebElement locationList = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//div[@id='facet_location']//ul"));
        List<WebElement> options = locationList.findElements(By.tagName("li"));
        WebElement selectedOption = null;
        for (WebElement option : options) {
            String liCssClass = option.getAttribute("class");
            String locationValue = option.findElement(By.tagName("span")).getAttribute("data-filter-value");
            if(liCssClass!=null && liCssClass.contains("srSearchOptionListElementChecked")
                    && locationValue !=null && locationValue.contains(expectedLocation)
                    ){
                selectedOption = option;
                break;
            }
        }
        if(selectedOption ==null ){
            Assert.fail("Test Failed. Selected location =("+expectedLocation+") not found.");
        }else {
            WebElement searchTableResult = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']/table[@class='srJobList']"));
            List<WebElement> tableRowElements = searchTableResult.findElements(By.tagName("tr"));

            //if keyword='n'and count > 1then fail the test
            if(keyWord.trim().equalsIgnoreCase("N") &&
                    tableRowElements.size() >1){
                Assert.fail("Test Failed :(Entered Job Search :"+expectedJobText+") is not keyword, but results are retrieved.");
            }else if (tableRowElements.size() > 1) {
                System.out.println("Results retrieved for job [" + expectedJobText + "] location =[" + expectedLocation + "] count=[" + tableRowElements.size() + "]");
            } else {
                System.out.println("No Results found for job [" + expectedJobText + "] location =[" + expectedLocation + "] ");
            }
            String jobTextWithoutSpecialChars = getStringWithoutSpecialChars(expectedJobText);
            int inValidSearchResultCounter = tableRowElements.size() - 1;
            CucumberHooks.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            System.out.println("jobTextWithoutSpecialChars = " + jobTextWithoutSpecialChars);
            for (int index = 1; index < tableRowElements.size(); index++) {
                WebElement currentRowElement = tableRowElements.get(index);
                WebElement jobResultElement = currentRowElement.findElement(By.className("srJobListJobTitle"));
                WebElement locationResultElement = currentRowElement.findElement(By.className("srJobListLocation"));
                boolean jobResultsValid = jobResultElement.getText().toUpperCase().contains(jobTextWithoutSpecialChars.toUpperCase());
                boolean locationResultsValid = locationResultElement.getText().toUpperCase().contains(expectedLocation.toUpperCase());
                System.out.println("Results (job :" + jobResultElement.getText() + ") (location :" + locationResultElement.getText()
                        + ") (jobResultsValid :" + jobResultsValid + ") (locationResultElement :" + locationResultElement + ")");
                if (jobResultsValid && locationResultsValid) {
                    inValidSearchResultCounter = inValidSearchResultCounter - 1;
                }
            }
            if (inValidSearchResultCounter > 0) {
                Assert.fail(inValidSearchResultCounter + " Search results doesn't match with input criteria ");
            }
        }
      }

    private String getStringWithoutSpecialChars(String value) {
        final String regexPattern = "[^A-Za-z0-9 ]";
        return value.replaceAll(regexPattern,"");
    }


}
