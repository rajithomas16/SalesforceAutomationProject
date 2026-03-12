package utils;

import base.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class DatePickerUtils {

    public static void setdateJS(WebElement element, String dateValue)
    {
        /**Not worrying about getShadowroot(),SVG and
         * * It uses the thread-safe DriverManager instance directly.
         * Sets the date directly using JavaScript, bypassing the Shadow DOM calendar UI.
         * * @param driver    The WebDriver instance
         * @param element   The date input WebElement (e.g., located via standard XPath/CSS)
         * @param dateValue The exact date string expected by Salesforce (e.g., "2026-03-15")
         */
        JavascriptExecutor js=(JavascriptExecutor)DriverManager.getDriver();
       // Set the value of the input field directly
        js.executeScript("arguments[0].value = arguments[1];", element, dateValue);
        // Dispatch a 'change' event so Salesforce registers that data was entered
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", element);
        // Dispatch a 'blur' event to simulate the user clicking away
        js.executeScript("arguments[0].dispatchEvent(new Event('blur'));", element);

    }

/**
 * Interacts with the calendar using a single Shadow DOM pierce.
 * It uses the thread-safe DriverManager instance directly.
 */
public static void selectDateSimpleUI(String day, String month, String year)
{
    //xpath cant be used for shadow root
    // 1. Find the direct host and pierce it once
    WebElement shadowHost= DriverManager.getDriver().findElement((By.cssSelector("lightning-datepicker")));
    SearchContext shadowRoot = shadowHost.getShadowRoot();
   // 2. Click the input to open the calendar dropdown
    WebElement dateInput = shadowRoot.findElement(By.cssSelector("input.slds-input"));
    dateInput.click();
    // 3. Select Year and Month
    Select select1= new Select(shadowRoot.findElement(By.cssSelector("select[name='year']")));
    select1.selectByVisibleText(year);

    Select select2= new Select(shadowRoot.findElement(By.cssSelector("select[name='month']")));
    select2.selectByVisibleText(month);




}
}
