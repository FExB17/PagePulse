package com.feb17.pagePulse;

import com.feb17.pagePulse.utils.Driver;

public class WebsiteChecker {
    public boolean isWebsiteReachable(String url) {
        Driver.getDriver().get(url);
        return true;
    }
}

