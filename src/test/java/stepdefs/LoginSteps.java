package stepdefs;

import base.DriverManager;
import utils.PropertyUtils;
import utils.ReportUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginSteps {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Your PropertyUtils fetches the SauceDemo URL
        DriverManager.getDriver().get(PropertyUtils.getProperty("url"));
        ReportUtils.logStep("Navigated to Swag Labs");
    }

    @When("the user enters standard credentials")
    public void the_user_enters_standard_credentials() {
        DriverManager.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverManager.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverManager.getDriver().findElement(By.id("login-button")).click();
        ReportUtils.logStep("Entered credentials and clicked login");
    }

    @Then("the user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_inventory_page() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"));
        ReportUtils.logStep("Successfully verified inventory page URL");
    }
}