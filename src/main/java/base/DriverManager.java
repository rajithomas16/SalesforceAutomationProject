//***Browser engine (ThreadLocal)**//
package base;

import utils.PropertyUtils;//different package so import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//optimized for selenium manager-You no longer need to update WebDriverManager versions in your pom.xml.
//ChromeOptions: I added ChromeOptions for "Incognito" mode or "Headless" mode
public class DriverManager {

    //Threadsafe-For parallel execution like 5 test at a time-5 tests will try to use the same driver variable .So we need Threadlocal.
    //One instance for one specific thread, while other threads get their own separate instances.

    private static ThreadLocal<WebDriver> driverPool=new ThreadLocal<>();


    private DriverManager() {//private constructor so that no other class can create object

    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {//driverPool.get() provides a "singleton" instance per thread.
//retreive property called browser from property file
            String browser = PropertyUtils.getProperty("browser").toLowerCase();

            if(browser.equalsIgnoreCase("chrome"))
            {
                ChromeOptions options= new ChromeOptions();
                //for local debugging
                options.addArguments("--start-maximized");

                //for ci cd stability
                options.addArguments("--headless");
                driverPool.set(new ChromeDriver(options));

            } else if (browser.equalsIgnoreCase("firefox"))
            {
                driverPool.set(new FirefoxDriver());
            }
        else {
            throw new RuntimeException("Invalid browser name: " + browser);
        }
    }
        return driverPool.get();//webdriver instance

        }


    public static void quitDriver() {
        if (driverPool != null) {//This checks if the current thread has an active WebDriver instance
            driverPool.get().quit();//This commands Selenium to close all browser windows associated with that driver instance and terminates the browser driver process
            driverPool.remove();//This is critical for preventing memory leaks. clears only the individual locker for the thread that just finished, leaving the room open for others.
        }
    }
}
