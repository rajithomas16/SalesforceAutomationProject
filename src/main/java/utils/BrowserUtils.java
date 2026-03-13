package utils;

import base.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.WaitUtils.waitForClickability;
import static utils.WaitUtils.waitForVisibility;

public class BrowserUtils {




    /**
     * Generic method to expand a shadow host and return its shadow root
     */
    public static SearchContext expandShadowRoot(WebElement host) {
        return host.getShadowRoot();
    }
}

