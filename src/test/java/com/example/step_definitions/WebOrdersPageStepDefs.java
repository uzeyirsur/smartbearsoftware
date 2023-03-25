package com.example.step_definitions;

import com.example.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class WebOrdersPageStepDefs extends BaseStep {


    @Given("the user is on the web orders page")
    public void the_user_is_on_the_web_orders_page() {
        BrowserUtils.wait(1);
        Assert.assertEquals(pages.webOrdersPage().getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
    }




    @Given("the user logged in with valid credentials, {string} as username and {string} as password")
    public void the_user_logged_in_with_valid_credentials_as_username_and_as_password(String usernameValue, String passwordValue) {
        pages.loginPage().enterUserNameValue(usernameValue);
        pages.loginPage().enterPasswordValue(passwordValue);
        pages.loginPage().clickLoginButton();
    }

    @Then("the user views the level {int} header is displayed as {string}")
    public void the_user_views_the_level_header_is_displayed_as(Integer levelOfHeader, String expectedHeader) {
        String actualHeader = pages.webOrdersPage().getHeaderAsString(levelOfHeader);
        Assert.assertTrue(actualHeader.contentEquals(expectedHeader));
    }

    @When("the user clicks on the logout button")
    public void the_user_clicks_on_the_logout_button() {
       pages.webOrdersPage().clickLogoutButton();
    }

    @Then("the user logs out and navigates to the login page")
    public void the_user_logs_out_and_navigates_to_the_login_page() {
        Assert.assertEquals(pages.loginPage().getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete12%2fWebOrders%2fDefault.aspx");
    }

    @Then("the user should be able to see {string} section in vertical menu bar")
    public void the_user_should_be_able_to_see_section_in_vertical_menu_bar(String verticalMenuBarItem) {
        Assert.assertTrue(pages.webOrdersPage().isVerticalMenuBarItemDisplayed(verticalMenuBarItem));

    }


}
