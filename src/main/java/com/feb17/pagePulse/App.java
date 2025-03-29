package com.feb17.pagePulse;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {


    public static void main (String[] args){
        // 1. website besuchen ohne User Agent oder sonstige Methoden die Bot detection zu umgehen
        // TODO die url soll über api request oder Kommandozentrale kommen
        String url = "https://amazon.com";

        // 1.2 website prüfen
        WebsiteChecker checker = new WebsiteChecker();
        boolean reachable = checker.isWebsiteReachable(url);

        // 2. screenshot aufnehmen und abspeichern
        ScreenshotService takeScreenshot = new ScreenshotService();
        boolean screenshotOk = takeScreenshot.captureScreenshot(url, "output/screenshot.png");
            if (screenshotOk){
                System.out.println("Screenshot saved");
            }else{
                System.out.println("Screenshot failed");
            }

        if (reachable) {
            System.out.println("Page is reachable with plain automation");

        }else {
            System.out.println("Page not reachable with plain automation");
        }
    }
}

