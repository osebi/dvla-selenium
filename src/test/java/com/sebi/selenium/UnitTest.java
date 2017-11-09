package com.sebi.selenium;

import com.sebi.config.BrowserFactory;
import com.sebi.utility.Constants;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.assertTrue;

public class UnitTest {

    private WebDriver driver;
    private static final String DUMMY_PLATE = "S99 BBY";

    @Before
    public void setup() {
        driver = BrowserFactory.getDriver();
    }

    @Test
    public void assertStartButtonCssIsDisplayedOnPage1() {
        driver.get(Constants.PAGE1_URL);
        WebElement startButton = driver.findElement(By.cssSelector(Constants.CLICK_START_BUTTON));
        assertTrue(startButton.isDisplayed());
    }

    @Test
    public void assertInputBoxCssIsDisplayedOnPage2() {
        driver.get(Constants.PAGE2_URL);
        WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
        assertTrue(input.isDisplayed());
    }

    @Test
    public void assertClickToContinueButtonCssIsDisplayedOnPage2() {
        driver.get(Constants.PAGE2_URL);
        WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
        assertTrue(button.isDisplayed());
    }

    @Test
    public void assertListSummaryCssIsDisplayedPage3() {
        driver.get(Constants.PAGE2_URL);
        WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
        WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
        if (input.isDisplayed() && button.isDisplayed()) {
            input.sendKeys("S99 BBY");
            button.click();
        }
        WebElement summary = driver.findElement(By.cssSelector(Constants.LIST_SUMMARY));
        assertTrue(summary.isDisplayed());
    }

    @Test
    public void assertRegistrationNumberCssIsDisplayedOnPage3() {
        driver.get(Constants.PAGE2_URL);
        WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
        WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
        if (input.isDisplayed() && button.isDisplayed()) {
            input.sendKeys("S99 BBY");
            button.click();
        }
        WebElement make = driver.findElement(By.cssSelector(Constants.REG_MARK));
        assertTrue(make.isDisplayed());
    }

    @Test
    public void assertVehicleMakeCssIsDisplayedOnPage3() {
        driver.get(Constants.PAGE2_URL);
        WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
        WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
        if (input.isDisplayed() && button.isDisplayed()) {
            input.sendKeys("S99 BBY");
            button.click();
        }
        WebElement make = driver.findElement(By.cssSelector(Constants.VEHICLE_MAKE));
        assertTrue(make.isDisplayed());
    }

    @Test
    public void assertVehicleColorCssIsDisplayedOnPage3() {
        driver.get(Constants.PAGE2_URL);
        WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
        WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
        if (input.isDisplayed() && button.isDisplayed()) {
            input.sendKeys("S99 BBY");
            button.click();
        }
        WebElement make = driver.findElement(By.cssSelector(Constants.VEHICLE_COLOR));
        assertTrue(make.isDisplayed());
    }

    @After
    public void quitBrowser() {
        driver.close();
        driver.quit();
    }


}
