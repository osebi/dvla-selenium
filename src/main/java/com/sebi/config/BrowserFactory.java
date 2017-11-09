package com.sebi.config;

import com.sebi.utility.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    private static final Browsers DEFAULT_BROWSER = Browsers.CHROME;

    public static WebDriver driver = null;
    public static Browsers browser;

    public enum Browsers {
        CHROME, FIREFOX, SAFARI, //...
    }

    static {
        loadBrowserDriver();
        String browserEnumValue = System.getProperty(Constants.PROPERTY_BROWSER_KEY);
        if (isEmptyOrNull(browserEnumValue)) {
            browser = DEFAULT_BROWSER;
        } else {
            browser = Browsers.valueOf(browserEnumValue);
        }
    }

    private static void loadBrowserDriver() {
        String pathToChromeDriver = "/Users/TRIMZBEATZ/Documents/vehicleselenium/src/test/resources/driver/chromedriver";
        System.setProperty(Constants.CHROME_DRIVER, pathToChromeDriver);
    }

    private static boolean isEmptyOrNull(String s) {
        return s == null || s.isEmpty();
    }

    public static WebDriver getDriver() {
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
//            case SAFARI:
//                driver = new SafariDriver();
//                break;
//            case FIREFOX:
//                driver = new FirefoxDriver();
//                break;
            default:
                driver = new ChromeDriver();
                break;

        }
        return driver;
    }


}
