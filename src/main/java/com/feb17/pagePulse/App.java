package com.feb17.pagePulse;


import com.feb17.pagePulse.utils.ConfigReader;
import io.javalin.Javalin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class App {
    //TODO muss noch korrigiert werden
    static String url = "";

    // nimmt die url von der Kommandozeile im Befehl oder von der Eingabe
    public static void runCliMode(String[] args){
        String url = "";
        if (args.length > 0) {
            url = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the URL of the website you want to check: ");
            url = scanner.nextLine();
        }

        WebsiteChecker checker = new WebsiteChecker();
        boolean reachable = checker.isWebsiteReachable(url);

        ScreenshotService ss = new ScreenshotService();
        String path = ConfigReader.getProperty("screenshotPath")+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"))+".png";
        boolean success = ss.captureScreenshot(url, path);

        System.out.println("Screenshot saved: " + success);
    }

    public static void startApiServer(){
        Javalin app = Javalin.create().start(8080);
        app.get("/check", ctx ->{
            String url = ctx.queryParam("url");
            if(url == null || url.isBlank()){
                ctx.status(400).result("Missing URL parameter.");
                return;
            }
            WebsiteChecker checker = new WebsiteChecker();
            boolean reachable = checker.isWebsiteReachable(url);

            ScreenshotService ss = new ScreenshotService();
            String path = ConfigReader.getProperty("screenshotPath")+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"))+".png";
            ScreenshotResult result = ss.captureScreenshotBase64(url,path);


            ctx.json(Map.of(
                    "url", url,
                    "reachable", reachable,
                    "screenshot_saved", result.success,
                    "screenshot_path", result.path,
                    "screenshot_base64", result.base64
            ));
        });
    }


    public static void main (String[] args){
        // 1. website besuchen ohne User Agent oder sonstige Methoden die Bot detection zu umgehen
        // TODO die url soll über api request oder Kommandozentrale kommen
        // wird entschieden ob die url per cli oder api übergeben wird
        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            startApiServer();
        } else {
            runCliMode(args);
        }

        // 1.2 website prüfen
        WebsiteChecker checker = new WebsiteChecker();
        //TODO sagt derzeit nur aus, dass es kein Error gibt sollte besser überprüft werden
        boolean reachable = checker.isWebsiteReachable(url);

        // 2. screenshot aufnehmen und abspeichern
        //TODO es werden grenzenlos screenshots aufgezeichnet -> muss limitiert werden
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

