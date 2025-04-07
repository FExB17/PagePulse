package com.feb17.pagePulse;

import com.feb17.pagePulse.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Base64;

public class ScreenshotService {

    private File saveScreenshot(String url, String path) throws IOException {
        WebDriver driver = Driver.getDriver();
        driver.get(url);

        //sollte warten bis die Seite geladen ist
        // speichert den screenshot in der referenz aber nicht auf der Festplatte
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File(path);

        // kopiert den screenshot von der referenz auf die festplatte
        // was kopiert werden soll || wohin es kopiert werden soll || was passieren soll wenn es schon existiert
        Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return destination;
    }

    public boolean captureScreenshot(String url, String path) {
        try {
            saveScreenshot(url, path);
            return true;
        } catch (IOException e) {
            System.out.println("Error while capturing the screenshot: " + e.getMessage());
            return false;
        } finally {
            Driver.quit();
        }
    }

    public ScreenshotResult captureScreenshotBase64(String url, String path) {
        Driver.getDriver().get(url);

        try {

            File file = saveScreenshot(url, path);
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            return new ScreenshotResult(true, path, base64);

        } catch (IOException e) {
            System.out.println("Fehler beim Screenshot mit Base64: " + e.getMessage());
            return new ScreenshotResult(false, path, null);
        } finally {
            Driver.quit();
        }
    }

    public BufferedImage captureScreenshotBuffered (String url){
        WebDriver driver = Driver.getDriver();
        driver.get(url);

        // die Bilder waren vorher gezoomt
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        try {
            return ImageIO.read(new ByteArrayInputStream(screenshot));
        } catch (IOException e) {
            System.out.println("Error while buffering image" +e);
            return null;
        }finally{
            driver.quit();
        }
    }
}




