package com.sg.game.steps_def;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class CareerMenuItemCheck {

    private static final String SG_DIGITAL_SITE = "http://www.sgdigital.com";
    private static final String SG_DIGITAL_WEBSITE_TITLE = "SG Digital – Leading Digital Gaming, Sports and iLottery";

    @Given("^Browser is launched$")
    public void when_browser_is_launched() throws Throwable {
        Assert.assertTrue("Browser is not launched.",(CucumberHooks.driver.getWindowHandle()!=null));
    }

    @When("^I Navigate to SgDigital home page$")
    public void i_Navigate_to_sgDigital_home_page() throws Throwable {
        CucumberHooks.driver.get(SG_DIGITAL_SITE);
    }

    @Then("^SgDigital page is displayed$")
    public void sgDigital_page_is_displayed() throws Throwable {
        System.out.println("Browser Title = (" + CucumberHooks.driver.getTitle()+")");
        Assert.assertTrue("Browser didn't navigate to correct url ("+SG_DIGITAL_SITE+")",
                (CucumberHooks.driver.getCurrentUrl().toLowerCase().contains("sgdigital")));
        Assert.assertTrue("Browser Title did not match with ('"+SG_DIGITAL_WEBSITE_TITLE+"')",
                CucumberHooks.driver.getTitle().equalsIgnoreCase(SG_DIGITAL_WEBSITE_TITLE));
    }

    @Then("^Total of (\\d+) Menu items are displayed$")
    public void total_of_Menu_items_are_displayed(int expectedMenuItems) {
        List<WebElement> elements = CucumberHooks.driver.findElements(By.xpath("//*[@id='menu-main-menu-1']//*/a"));
        int countElements = elements.size();
        Assert.assertTrue("Menu Bar does not match with expected no of menu item :"+expectedMenuItems,(countElements==expectedMenuItems));
    }

    @Then("^Careers Menu Item is Enabled and Visible$")
    public void cereers_Menu_Item_is_Enabled_and_Visible() throws Throwable {
        //web element can be used in any of ways :
         //   //*[@id="menu-main-menu-1"]/li[8]/a
         //       //*[@id='menu-main-menu-1']//*/a[text()='Careers']
        //      //*[@id='menu-main-menu-1']//following::a[text()='Careers']
        WebElement element = CucumberHooks.driver.findElement(By.xpath("//*[@id='menu-main-menu-1']//following::a[text()='Careers']"));

        Assert.assertTrue( "HyperLink text does not match with Careers", element.getText().equalsIgnoreCase("Careers"));
        Assert.assertTrue("Careers link is not visible", element.isDisplayed());
        Assert.assertTrue("Careers link is not enabled ", element.isEnabled());

    }




    @Given("^Career Page Navigation is completed$")
    public void when_Career_Page_Navigation_is_completed() throws Throwable {
        WebElement careerLink = CucumberHooks.driver.findElement(By.xpath("//*[@id='menu-main-menu-1']//*/a[text()='Careers']"));
        careerLink.click();
        SgDigitalWebsiteTimeOutManage.waitForSeconds(CucumberHooks.driver,10);
    }

    @Given("^Total of (\\d+) Menu items are displayed in predefined sequence$")
    public void total_of_Menu_items_are_displayed_in_predefined_sequence(int value) throws Throwable {
        verifyHomePageNavigation(value);
    }

    @Then("^\"(.*?)\" Label is visible$")
    public void label_is_visible(String inputLabelValue) throws Throwable {
        final String expectedLabel1 = "Careers with SG Digital";
        final String expectedLabel2 = "Current vacancies";
        if (inputLabelValue.trim().equalsIgnoreCase(expectedLabel1)){
            WebElement textLabel = CucumberHooks.driver.findElement(By.xpath("//div/h1[text()='Careers with SG Digital']"));
            Assert.assertTrue("The Label text ('" +textLabel.getText()+")' does not match with expected text ('"+expectedLabel1+"')",
                    textLabel.getText().equalsIgnoreCase(expectedLabel1));
            Assert.assertTrue("The label "+ inputLabelValue +" is not visible ",textLabel.isDisplayed());
        }else if (inputLabelValue.trim().equalsIgnoreCase(expectedLabel2)){
            WebElement textLabel = CucumberHooks.driver.findElement(By.xpath("//div/h1[text()='Current vacancies']"));
            Assert.assertTrue("The Label text ('" +textLabel.getText()+")' does not match with expected text ('"+expectedLabel2+"')",
                    textLabel.getText().equalsIgnoreCase(expectedLabel2));
            Assert.assertTrue("The Label " + inputLabelValue + "is not visible",textLabel.isDisplayed());
        }else{
            throw new AssertionFailedError("Unrecognised Label '"+inputLabelValue+"'");
        }
    }

    @Then("^Text Search Filter is enabled and editable$")
    public void text_Search_Filter_is_enabled_and_editable() throws Throwable {
        WebElement element = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//form/input[@type='text']"));
        Assert.assertTrue("The Search box is not displayed",element.isDisplayed());
        Assert.assertTrue("The Search box is not editable",element.isEnabled());
        String testData = "Sample Test Data";
        element.clear();
        element.sendKeys(testData);
        Assert.assertTrue("The Search field is not editable because (Test Data :"+testData+") is not sent.",
                testData.equalsIgnoreCase(element.getAttribute("value").trim()));
    }

    @Then("^Search Button is enabled and displayed$")
    public void button_Webelemet_is_enabled_and_clickable() throws Throwable {
        WebElement searchButton = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//form/input[@type='button']"));
        Assert.assertTrue("The Search Button is not visible",searchButton.isDisplayed());
        Assert.assertTrue("the Search Button is not enabled",searchButton.isEnabled());
    }

    @Then("^Location Dropdown is displayed$")
    public void dropdown_Weblist_is_displayed() throws Throwable {
        WebElement locationList = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//div[@id='facet_location']//ul"));
        Assert.assertTrue("Location drop down is not displayed", (locationList != null));
        List<WebElement> options = locationList.findElements(By.tagName("li"));
        for (WebElement option : options) {
            String attributeValue = option.findElement(By.tagName("Span")).getAttribute("data-filter-value");
            System.out.println("printing Location = " + attributeValue);
        }
    }

    @Given("^Select \"(.*?)\" in the location drop down$")
    public void select_in_the_search_Edit_box(String location) throws Throwable {
        WebElement locationList = CucumberHooks.driver.findElement(By.xpath("//*[@id='smartWidget0']//div[@class='srSearch']//div[@id='facet_location']//ul"));
        List<WebElement> options = locationList.findElements(By.tagName("li"));
        WebElement selectedOption = null;
        for (WebElement option : options) {
            String attributeValue = option.findElement(By.tagName("Span")).getAttribute("data-filter-value").trim();
            System.out.println("printing Location = " + attributeValue);
            if(attributeValue.trim().contains(location)){
                selectedOption = option;
                break;
            }
        }
        if(selectedOption !=null) {
            JavascriptExecutor executor = (JavascriptExecutor) CucumberHooks.driver;
            executor.executeScript("arguments[0].click();", selectedOption);
        }else{
            Assert.fail("Invalid Test data for location =["+location+"]");
        }
    }


    @Then("^\"(.*?)\" is displayed in Location dropdown$")
    public void is_displayed_in_Location_dropdown(String expectedLocation) throws Throwable {
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
        if(selectedOption ==null ) {
            Assert.fail("Test Failed. Selected location =(" + expectedLocation + ") not found.");
        }
    }

    private void verifyHomePageNavigation(int expectedMenuItems) {
        List<WebElement> webElements = CucumberHooks.driver.findElements(By.xpath("//*[@id='menu-main-menu-1']/li/a"));
        Assert.assertTrue("Menu Bar does not match with expected no of menu item :"+expectedMenuItems,(webElements.size()==expectedMenuItems));
        for (int index = 0; index <webElements.size() ; index++) {
            WebElement currentItem = webElements.get(index);
            System.out.println("CurrentItem Number= ["+index+"]  currentItemName = [" + currentItem.getText()+"]");
            switch (index) {
                case 0:
                    Assert.assertTrue("menu item name 'Home' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Home"));
                    break;
                case 1:
                    Assert.assertTrue("menu item name 'Games' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Games"));
                    break;
                case 2:
                    Assert.assertTrue("menu item name 'Products' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Products"));
                    break;
                case 3:
                    Assert.assertTrue("menu item name 'News' didn't match.", currentItem.getText().trim().equalsIgnoreCase("News"));
                    break;
                case 4:
                    Assert.assertTrue("menu item name 'About us' didn't match.", currentItem.getText().trim().equalsIgnoreCase("About us"));
                    break;
                case 5:
                    Assert.assertTrue("menu item name 'Meet the team' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Meet the team"));
                    break;
                case 6:
                    Assert.assertTrue("menu item name 'Blog' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Blog"));
                    break;
                case 7:
                    Assert.assertTrue("menu item name 'Careers' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Careers"));
                    break;
                case 8:
                    Assert.assertTrue("menu item name 'Investors' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Investors"));
                    break;
                case 9:
                    Assert.assertTrue("menu item name 'Openbet' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Openbet"));
                    break;
                case 10:
                    Assert.assertTrue("menu item name 'Scientific Games' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Scientific Games"));
                    break;
                case 11:
                    Assert.assertTrue("menu item name 'Contact' didn't match.", currentItem.getText().trim().equalsIgnoreCase("Contact"));
                    break;

            }
        }
    }



}

