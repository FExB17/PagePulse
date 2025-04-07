package com.feb17.pagePulse.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver(){
    }
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigReader.getProperty("driverMode"));
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
