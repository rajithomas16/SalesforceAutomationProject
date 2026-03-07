package base;

import utils.ConfigReader;//different package so import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//optimized for selenium manager-You no longer need to update WebDriverManager versions in your pom.xml.
//ChromeOptions: I added ChromeOptions.for "Incognito" mode or "Headless" mode
public class DriverManager {

    private static WebDriver driver;
//   DriverManager: "I implemented a Singleton-based DriverManager using Selenium Manager.
//   It handles browser initialization dynamically and ensures we have a single, clean WebDriver instance across the execution."
    private DriverManager() {
    }//private constructor so that no other class can create object

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();

            switch (browser) {

                case "chrome":

                    ChromeOptions chromeOptions = new ChromeOptions();//for
                    chromeOptions.addArguments("--start-maximised");
                    driver = new ChromeDriver(chromeOptions);

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();//for

                    driver = new FirefoxDriver(firefoxOptions);

                default:
                    throw new RuntimeException("Unsupported Browser : " + browser);

            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
