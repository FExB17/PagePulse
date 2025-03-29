package com.feb17.pagePulse;

import com.feb17.pagePulse.utils.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotService {
    public boolean captureScreenshot(String url, String path) {
        try {
            WebDriver driver = Driver.getDriver(); // nur damit es leserlicher wird
            driver.get(url);
            //sollte warten bis die Seite geladen ist

            // speichert den screenshot in der referenz aber nicht auf der Festplatte
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(path);
            // kopiert den screenshot von der referenz auf die festplatte
            // was kopiert werden soll || wohin es kopiert werden soll || was passieren soll wenn es schon existiert
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch(Exception e){
        System.out.println("Error while taking screenshot: " + e.getMessage());
        return false;
        } finally {
            Driver.quit();
        }
    }
}
