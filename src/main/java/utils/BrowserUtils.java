package utils;

import base.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils {

/***WAIT UTILITIES**/

    public static WebElement waitForVisibility(WebElement element) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));

    }

    public static WebElement waitForClickability(WebElement element) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));

    }
    /****JAVASCRIPT UTILITIES**/

    public static void clickWithJS(WebElement element) {
        waitForVisibility(element);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click()", element);

    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void setValueWithJS(WebElement element, String value) {
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].value='" + value + "';", element);
    }
}

