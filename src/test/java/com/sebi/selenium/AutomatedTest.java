package com.sebi.selenium;

import com.sebi.componentbeans.VehicleComponentBean;
import com.sebi.config.BrowserFactory;
import com.sebi.resolver.VehicleResolver;
import com.sebi.utility.Constants;
import com.sebi.utility.FileReaderUtil;
import com.sebi.utility.GeneralUtil;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AutomatedTest {

    private final static Logger LOG = Logger.getLogger(FileReaderUtil.class);

    private final static String FILE_PATH = "register1.csv";

    private WebDriver driver;

    private VehicleComponentBean componentBean;
    private VehicleResolver resolver;

    @Before
    public void setup() {
        driver = BrowserFactory.getDriver();
        componentBean = new VehicleComponentBean();
        resolver = new VehicleResolver();
    }

    @Test
    public void shouldReturnOneMakeAndColor() {

        String fileName = "test-run-1";
        File folder = new File("write-to-csv");

        componentBean = resolver.resolve(FILE_PATH);

        driver.get(Constants.PAGE1_URL);
        WebElement startNow = driver.findElement(By.cssSelector(Constants.CLICK_START_BUTTON));
        clickStartButton(startNow, "1", 0);

        String reg_num = componentBean.getRegNumbers().get(0);
        assertThat(reg_num, is(equalTo("GJ63 WGG")));
        assertThat(driver.getCurrentUrl(), is(equalTo(Constants.PAGE2_URL)));
        WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
        WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
        setRegistrationNumber(input, button, reg_num, "1", 0);

        WebElement regMark = driver.findElement(By.cssSelector(Constants.REG_MARK));
        assertThat(regMark.getText(), is(equalTo("GJ63 WGG")));
        String regStr = getVehicleReg(regMark);

        WebElement make = driver.findElement(By.cssSelector(Constants.VEHICLE_MAKE));
        assertThat(make.getText(), is("VAUXHALL"));
        String makeStr = getVehicleMake(make, regStr);

        WebElement colour = driver.findElement(By.cssSelector(Constants.VEHICLE_COLOR));
        assertThat(colour.getText(), is("GREEN"));
        String colourStr = getVehicleColour(colour, regStr);

        GeneralUtil.writeToCSV(reg_num, makeStr, colourStr, fileName, folder, false);

    }

    @Test
    public void shouldReturnMakeAndColor_forAllExistingRegistrationNumber() {

        componentBean = resolver.resolve(FILE_PATH);

        String fileName = "test-run-2";
        File folder = new File("write-to-csv");

        for (int i = 0; i < componentBean.getRegNumbers().size(); i++) {

            driver.get(Constants.PAGE1_URL);
            WebElement startNow = driver.findElement(By.cssSelector(Constants.CLICK_START_BUTTON));
            clickStartButton(startNow, "2", i);

            String reg_num = componentBean.getRegNumbers().get(i);
            assertThat(driver.getCurrentUrl(), is(equalTo(Constants.PAGE2_URL)));
            WebElement input = driver.findElement(By.cssSelector(Constants.INPUT_NUMBER_PLATE));
            WebElement button = driver.findElement(By.cssSelector(Constants.CLICK_CONTINUE));
            setRegistrationNumber(input, button, reg_num, "2", i);

            WebElement regMark = driver.findElement(By.cssSelector(Constants.REG_MARK));
            String regStr = getVehicleReg(regMark);

            WebElement make = driver.findElement(By.cssSelector(Constants.VEHICLE_MAKE));
            String makeStr = getVehicleMake(make, regStr);

            WebElement colour = driver.findElement(By.cssSelector(Constants.VEHICLE_COLOR));
            String colourStr = getVehicleColour(colour, regStr);

            GeneralUtil.writeToCSV(reg_num, makeStr, colourStr, fileName, folder, true);

        }


    }

    private void clickStartButton(WebElement element, String testNum, int count) {
        String dir = "test-run-" + testNum + "-start-now-screenshot-" + count;
        if (element.isDisplayed()) {
            GeneralUtil.takeScreenShot(driver, dir);
            element.click();
            LOG.info("Opening first page: " + driver.getCurrentUrl());
        } else {
            LOG.info("Element does not exist");
        }
    }

    private void setRegistrationNumber(WebElement input, WebElement button, String reg_num, String testNum, int count) {
        String dir_1 = "test-run-" + testNum + "-input-screenshot-" + count;
        String dir_2 = "test-run-" + testNum + "-summary-screenshot-" + count;
        if (input.isDisplayed() && button.isDisplayed()) {
            input.sendKeys(reg_num);
            GeneralUtil.takeScreenShot(driver, dir_1);
            button.click();
            GeneralUtil.takeScreenShot(driver, dir_2);
            LOG.info("Opening second page: " + driver.getCurrentUrl());
        }
    }

    private String getVehicleReg(WebElement regMark) {
        String regStr = "";
        if (regMark.isDisplayed()) {
            regStr += regMark.getText();
            LOG.info("Register number on page is: " + regStr);
        }
        return regStr;
    }

    private String getVehicleMake(WebElement make, String regStr) {
        String makeStr = "";
        if (make.isDisplayed()) {
            makeStr += make.getText();
            LOG.info("Make for registration " + regStr + " is " + makeStr);
        }
        return makeStr;
    }

    private String getVehicleColour(WebElement colour, String regStr) {
        String colourStr = "";
        if (colour.isDisplayed()) {
            colourStr += colour.getText();
            LOG.info("Colour for registration " + regStr + " is " + colourStr);
        }
        return colourStr;
    }

    private void threadSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private File mockFile(String path) {
        return new File(path);
    }

    @After
    public void quitBrowser() {
        driver.close();
        driver.quit();
    }

}
