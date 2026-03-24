package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Reusable utility for handling WebDriver element interactions.
 * Focuses on synchronization and null-safety.
 */
public class ElementUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private final int DEFAULT_TIMEOUT = 10;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    /**
     * Centralized retrieval method. Ensures element is present before any action.
     */
    private WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void doClick(By locator) {
        // Wait until clickable is better for buttons/links
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Clears existing text and types new value. Includes null-check logic.
     */
    public void doSendKeys(By locator, String value) {
        if (value == null) {
            throw new RuntimeException("Error: Value for sendKeys cannot be null at locator: " + locator);
        }
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public String doGetElementText(By locator) {
        return getElement(locator).getText();
    }

    /**
     * Checks visibility within the timeout period. Returns false instead of crashing.
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public List<WebElement> getElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public String getAttributeValue(By locator, String attrName) {
        return getElement(locator).getAttribute(attrName);
    }
}