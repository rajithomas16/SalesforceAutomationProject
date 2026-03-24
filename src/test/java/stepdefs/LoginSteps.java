package stepdefs;

import base.DriverManager;
import pages.LoginPage;
import utils.PropertyUtils;
import utils.ReportUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps {

    // ✅ Initialize page objects
    private LoginPage loginPage = new LoginPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        DriverManager.getDriver().get(PropertyUtils.getProperty("url"));
        ReportUtils.logStep("Navigated to Swag Labs login page");
    }

    @When("the user enters standard credentials")
    public void the_user_enters_standard_credentials() {
        // ✅ Using LoginPage methods (which use BasePage methods with built-in waits)
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("the user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_inventory_page() {
        // ✅ All these methods use BasePage's wait methods
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(),
                "Inventory page not displayed");
        String title = loginPage.getPageTitle();
        Assert.assertEquals(title, "Products", "Page title mismatch");
        ReportUtils.logStep("Successfully verified inventory page");
    }

    @Then("the user should see error message")
    public void the_user_should_see_error_message() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message not displayed");
        String errorMsg = loginPage.getErrorMessage();
        ReportUtils.logStep("Error message: " + errorMsg);
    }
}
