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
            options.addArguments("--headless");
            options.addArguments("----window-size=1920,1080");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.90 Safari/537.36");
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
