package io.cucumber.jupitertoys;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.jupitertoys.data.ContactData;
import io.cucumber.jupitertoys.pages.ContactPage;
import io.cucumber.jupitertoys.pages.HomePage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

public class ContactSteps {
    private BaseTestSuite baseTestSuite;
    WebDriver driver;

    public ContactSteps(BaseTestSuite baseTestSuite) {
        this.baseTestSuite = baseTestSuite;
        driver = baseTestSuite.getDriver();
    }

    @Given("The customer tries to Contact us")
    public void The_customer_tries_to_Contact_us() {
        new HomePage(driver).clickContactMenu();
    }

    @When("The customer puts invalid email address")
    public void The_customer_puts_invalid_email_address() {
        new ContactPage(driver).setEmail("notavalidemail");
    }

    @Then("Please enter a valid email message should be displayed")
    public void Please_enter_a_valid_email_message_should_be_displayed() {
        assertEquals("Please enter a valid email", new ContactPage(driver).getEmailError());
    }

    @When("Customer submits empty info")
    public void Customer_submits_empty_info() {
        new ContactPage(driver).clickSubmitButton();
    }

    @Then("Error message for mandatory information are displayed")
    public void Error_message_for_mandatory_information_are_displayed() {
        var contactPage = new ContactPage(driver);

        assertEquals("Forename is required", contactPage.getForenameError());
        assertEquals("Email is required", contactPage.getEmailError());
        assertEquals("Message is required", contactPage.getMessageError());
    }

    @When("Customer populates mandatory field")
    public void Customer_populates_mandatory_field(DataTable customerList) {
        for (Map<String, String> data : customerList.asMaps(String.class, String.class)) {
            new ContactPage(driver)
                .setForename(data.get("Forename"))
                .setEmail(data.get("Email"))
                .setMessage(data.get("Message"));
        }
    }

    @When("Customer clicks submit button")
    public void Customer_clicks_submit_button() {
        new ContactPage(driver).clickSubmitButton();
    }

    @Then("Thanks message is displayed")
    public void Thanks_message_is_displayed() {
        var successMessage = new ContactPage(driver);

        assertEquals("Thanks John, we appreciate your feedback.", successMessage.getSuccessMessage());
    }

}