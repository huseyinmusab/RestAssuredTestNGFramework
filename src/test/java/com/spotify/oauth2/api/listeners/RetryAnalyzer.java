package com.spotify.oauth2.api.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxTry = 2; // fail olursa maksimum 2 retry

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            System.out.println("Retrying test: " + result.getName() + " | attempt " + count);
            return true;
        }
        return false;
    }

}
