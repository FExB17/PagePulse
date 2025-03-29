package com.feb17.pagePulse;


import com.feb17.pagePulse.utils.ConfigReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {


    public static void main (String[] args){
        // 1. website besuchen ohne User Agent oder sonstige Methoden die Bot detection zu umgehen
        // TODO die url soll über api request oder Kommandozentrale kommen
        String url = "https://zalando.com";

        // 1.2 website prüfen
        WebsiteChecker checker = new WebsiteChecker();
        //TODO sagt derzeit nur aus, dass es kein Error gibt sollte besser überprüft werden
        boolean reachable = checker.isWebsiteReachable(url);

        // 2. screenshot aufnehmen und abspeichern
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
        String path = ConfigReader.getProperty("screenshotPath")+timestamp+".png";


        ScreenshotService takeScreenshot = new ScreenshotService();
        boolean screenshotOk = takeScreenshot.captureScreenshot(url, path);
            if (screenshotOk){
                System.out.println("Screenshot saved");
            }else{
                System.out.println("Screenshot failed");
            }
        //TODO gibt not reachable aus obwohl die seite besucht wird -> die methode muss ohnehin verbessert werden
        if (reachable) {
            System.out.println("Page is reachable with plain automation");

        }else {
            System.out.println("Page not reachable with plain automation");
        }
    }
}

