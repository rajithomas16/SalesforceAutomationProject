package utils;

import base.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static utils.WaitUtils.waitForClickability;
import static utils.WaitUtils.waitForVisibility;

public class JSUtils {

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
    public static void scrollToAndClick(WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        waitForClickability(element);
        element.click();
    }
}
