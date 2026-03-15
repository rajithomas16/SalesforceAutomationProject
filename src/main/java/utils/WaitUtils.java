package utils;

import base.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
//1. Centralized Timeout - Easy to change framework-wide
    public static final Duration TIMEOUT=Duration.ofSeconds(10) ;
    /**
     * Waits for an element to be visible in the DOM and on the UI.1.
     * Is the element in the HTML? 2. Is its height and width greater than zero?
     */
    public static WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait= new WebDriverWait(DriverManager.getDriver(),TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOf(element));

    }
    /**
     * Waits for an element to be enabled and clickable.
     * Essential for buttons that might be greyed out during page load.
     */
    public static WebElement waitForClickability(WebElement element) {
        /** test comments this is new comments. Use this as well. This is wait for clickability function **/
        WebDriverWait wait= new WebDriverWait(DriverManager.getDriver(),TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    /**
     *  Fluent Wait
     * Handles elements that 'blink' or refresh (StaleElementReferenceException)
     */
    public static WebElement fluentWait(WebElement element) {
        Wait<WebDriver> wait= new FluentWait<>(DriverManager.getDriver()).withTimeout(TIMEOUT).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class).
                ignoring(StaleElementReferenceException.class);

        return wait.until(ExpectedConditions.visibilityOf(element));

    }
}
