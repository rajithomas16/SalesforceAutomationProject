package utils;

import java.awt.*;

import base.DriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.awt.event.KeyEvent;
public class KeyUtils {

    private static Robot robot;
  //The Robot class is a "heavy" object. It has to hook into your computer's native hardware drivers to control the keyboard and mouse.
   //By putting it in a static {} block, we create one single Robot that stays alive for the entire duration of your test execution.
    static
     {
      try
      {
          robot = new Robot();
      }
      catch (AWTException e) {
          throw new RuntimeException(e);
      }
     }
// --- SECTION 1: SELENIUM ACTIONS (Browser-Level) ---

    /**
     * Waits for visibility and then sends a specific key using Actions.
     * Use this for Salesforce shortcuts or navigating dropdowns.
     */
    public static void sendKeyWithActions(WebElement element, Keys key)
    {
    WaitUtils.waitForVisibility(element);
    Actions act= new Actions(DriverManager.getDriver());
    act.moveToElement(element).sendKeys(key).perform();

   }

       // --- SECTION 2: ROBOT CLASS (OS-Level) ---

       /**
        * Direct OS-level Enter key press.
        * Use this ONLY when Selenium's Actions fail to hit 'Enter' on an OS popup.
        */
       public static void robotEnter() {
       robot.keyPress(KeyEvent.VK_ENTER);
       robot.keyRelease(KeyEvent.VK_ENTER);
       robot.delay(500);
   }

       /**
        * Cross-platform Paste command using Robot.
        */
       public static void robotPaste() {
       int controlKey = OSValidator.isMac() ? KeyEvent.VK_META : KeyEvent.VK_CONTROL;
       robot.keyPress(controlKey);
       robot.keyPress(KeyEvent.VK_V);
       robot.keyRelease(KeyEvent.VK_V);
       robot.keyRelease(controlKey);
   }
   }





