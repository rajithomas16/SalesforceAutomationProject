package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        // Generates both standard HTML and Allure reports
        plugin = {
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        features = "src/test/resources/features",
        glue = "stepdefs",
        dryRun = false,
        tags = "@smoke"
)
public class CukesRunner extends AbstractTestNGCucumberTests {

    /**
     * SENIOR SDET IMPLEMENTATION:
     * Overriding this method enables TestNG to execute Cucumber scenarios in parallel.
     * This proves your ThreadLocal DriverManager is working perfectly.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}