package com.feb17.pagePulse;

public class ScreenshotResult {
    public boolean success;
    public String path;
    public String base64;

    public ScreenshotResult(boolean success, String path, String base64) {
        this.success = success;
        this.path = path;
        this.base64 = base64;
    }
}