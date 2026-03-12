package stepdefs;

import base.DriverManager;
import utils.PropertyUtils;
import utils.ReportUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
/// Cucumber automatically finds any class with @Before
/// and @After annotations as long as it's in the "glue" path (which we set in the CukesRunner).
public class Hooks {

    @Before
    public void setUp() {
        // 1. Initialize the ThreadLocal driver
        WebDriver driver = DriverManager.getDriver();

        // 2. Maximize and set up the starting URL
        driver.manage().window().maximize();
        driver.get(PropertyUtils.getProperty("url"));

        ReportUtils.logStep("Browser initialized and navigated to: " + PropertyUtils.getProperty("url"));
    }

    @After
    public void tearDown(Scenario scenario) {///Scenario scenario parameter in the @After method.
        /// This is a built-in Cucumber object that knows exactly which test just ran and if it passed or failed.
        // 3. Automated Failure Handling - The Senior SDET way
        if (scenario.isFailed()) {
            // This pulls the screenshot from the correct thread's driver
            ReportUtils.takeScreenshot("FAILED_SCENARIO_" + scenario.getName());
            ReportUtils.logStep("Scenario Failed. Screenshot captured.");
        }

        // 4. Clean up the driver instance for this thread
        DriverManager.quitDriver();
        ReportUtils.logStep("Browser session closed.");
    }
}