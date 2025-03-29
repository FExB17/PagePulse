package com.feb17.pagePulse.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private Driver(){
    }
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }
    public static void quit(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
