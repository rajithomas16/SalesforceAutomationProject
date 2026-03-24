package pages;

import base.DriverManager;
import utils.JSUtils;
import utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * BasePage: Common functionality for all page objects
 *
 * This class:
 * 1. Initializes PageFactory once
 * 2. Provides reusable methods with built-in waits
 * 3. Integrates ThreadLocal driver from DriverManager
 * 4. Eliminates code duplication across page classes
 */
public class BasePage {

    // ✅ Protected = Accessible to child classes only
    protected WebDriver driver;

    /**
     * CONSTRUCTOR - Automatically called when any child page is instantiated
     *
     * Example:
     * LoginPage loginPage = new LoginPage();  // Constructor chain
     * ├── super() calls BasePage()
     * ├── Initializes driver from ThreadLocal
     * └── Initializes PageFactory for @FindBy annotations
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    // ==================== ELEMENT INTERACTION METHODS ====================

    /**
     * Safe sendKeys with explicit wait
     *
     * Usage:
     * sendKeys(usernameField, "standard_user");
     */
    public void sendKeys(WebElement element, String text) {
        WaitUtils.waitForVisibility(element);
        element.clear();  // Clear any pre-filled data
        element.sendKeys(text);
    }

    /**
     * Safe click with explicit wait
     *
     * Usage:
     * click(loginButton);
     */
    public void click(WebElement element) {
        WaitUtils.waitForClickability(element);
        element.click();
    }

    /**
     * Safe getText with explicit wait
     *
     * Usage:
     * String title = getText(inventoryTitle);
     */
    public String getText(WebElement element) {
        WaitUtils.waitForVisibility(element);
        return element.getText();
    }

    /**
     * Check if element is displayed
     *
     * Usage:
     * if (isDisplayed(inventoryContainer)) { ... }
     */
    public boolean isDisplayed(WebElement element) {
        try {
            WaitUtils.waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get attribute value safely
     *
     * Usage:
     * String value = getAttribute(inputField, "value");
     */
    public String getAttribute(WebElement element, String attribute) {
        WaitUtils.waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    /**
     * Check if element is enabled
     *
     * Usage:
     * if (isEnabled(submitButton)) { ... }
     */
    public boolean isEnabled(WebElement element) {
        try {
            return WaitUtils.waitForClickability(element).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== JAVASCRIPT HELPER METHODS ====================

    /**
     * Click element using JavaScript (fallback for stale elements)
     *
     * Usage:
     * jsClick(hardToClickElement);
     */
    public void jsClick(WebElement element) {
        JSUtils.clickWithJS(element);
    }

    /**
     * Scroll to element and click
     *
     * Usage:
     * scrollAndClick(bottomButton);
     */
    public void scrollAndClick(WebElement element) {
        JSUtils.scrollToAndClick(element);
    }

    /**
     * Scroll to element
     *
     * Usage:
     * scrollTo(hiddenElement);
     */
    public void scrollTo(WebElement element) {
        JSUtils.scrollToElement(element);
    }

    /**
     * Set value using JavaScript (for calendar pickers)
     *
     * Usage:
     * jsSetValue(dateField, "2026-03-24");
     */
    public void jsSetValue(WebElement element, String value) {
        JSUtils.setValueWithJS(element, value);
    }

    // ==================== LIST OPERATIONS ====================

    /**
     * Get size of list of elements
     *
     * Usage:
     * int count = getListSize(products);
     */
    public int getListSize(List<WebElement> elements) {
        return elements.size();
    }

    /**
     * Click element by index in list
     *
     * Usage:
     * clickByIndex(products, 0);  // Click first product
     */
    public void clickByIndex(List<WebElement> elements, int index) {
        WaitUtils.waitForClickability(elements.get(index));
        elements.get(index).click();
    }

    /**
     * Get text from element by index
     *
     * Usage:
     * String firstProduct = getTextByIndex(products, 0);
     */
    public String getTextByIndex(List<WebElement> elements, int index) {
        WaitUtils.waitForVisibility(elements.get(index));
        return elements.get(index).getText();
    }

    // ==================== NAVIGATION METHODS ====================

    /**
     * Navigate to URL
     *
     * Usage:
     * navigateTo("https://www.saucedemo.com/inventory.html");
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Get current page URL
     *
     * Usage:
     * String url = getCurrentUrl();
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     *
     * Usage:
     * String title = getPageTitle();
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Refresh current page
     *
     * Usage:
     * refreshPage();
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Go back to previous page
     *
     * Usage:
     * goBack();
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Go forward to next page
     *
     * Usage:
     * goForward();
     */
    public void goForward() {
        driver.navigate().forward();
    }

    // ==================== WAIT & SYNCHRONIZATION ====================

    /**
     * Custom wait (if needed beyond WaitUtils)
     *
     * Usage:
     * waitForSeconds(2);
     */
    public void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ==================== SWITCH OPERATIONS ====================

    /**
     * Switch to alert and accept it
     *
     * Usage:
     * acceptAlert();
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Switch to alert and dismiss it
     *
     * Usage:
     * dismissAlert();
     */
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Get alert text
     *
     * Usage:
     * String alertMsg = getAlertText();
     */
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }
}
