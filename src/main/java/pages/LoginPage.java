package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReportUtils;

/**
 * LoginPage extends BasePage
 * - Inherits all common methods (click, sendKeys, getText, etc.)
 * - Inherits PageFactory initialization
 * - Inherits ThreadLocal driver management
 */
public class LoginPage extends BasePage {

    // ==================== PAGE ELEMENTS ====================

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".title")
    private WebElement inventoryTitle;

    @FindBy(xpath = "//div[@class='inventory_list']")
    private WebElement inventoryContainer;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    // ==================== PAGE ACTIONS ====================

    /**
     * Login with username and password
     *
     * Usage:
     * loginPage.login("standard_user", "secret_sauce");
     */
    public void login(String username, String password) {
        sendKeys(usernameField, username);  // ← From BasePage (with wait)
        sendKeys(passwordField, password);  // ← From BasePage (with wait)
        click(loginButton);                 // ← From BasePage (with wait)
        ReportUtils.logStep("Logged in with username: " + username);
    }

    /**
     * Enter username only
     */
    public void enterUsername(String username) {
        sendKeys(usernameField, username);  // ← Cleaner than LoginPage managing waits
        ReportUtils.logStep("Entered username: " + username);
    }

    /**
     * Enter password only
     */
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
        ReportUtils.logStep("Entered password");
    }

    /**
     * Click login button
     */
    public void clickLogin() {
        click(loginButton);  // ← Automatic wait built-in
        ReportUtils.logStep("Clicked login button");
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return getText(inventoryTitle);
    }

    /**
     * Check if inventory page is displayed
     */
    public boolean isInventoryPageDisplayed() {
        return isDisplayed(inventoryContainer);  // ← Safe with built-in wait
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }
}
