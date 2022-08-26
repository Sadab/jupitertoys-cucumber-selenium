package io.cucumber.jupitertoys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.jupitertoys.pages.ContactPage;
import io.cucumber.jupitertoys.pages.HomePage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}