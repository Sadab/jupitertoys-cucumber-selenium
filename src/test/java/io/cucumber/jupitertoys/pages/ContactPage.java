package io.cucumber.jupitertoys.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage extends BasePage<ContactPage> {

    public ContactPage(WebDriver driver){
        super(driver);
    }

    public ContactPage setEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        return this;
    }

    public String getEmailError() {
        return driver.findElement(By.id("email-err")).getText();
    }

    public ContactPage clickSubmitButton() {
        driver.findElement(By.cssSelector(".btn-contact")).click();
        return this;
    }

    public String getForenameError() {
        return driver.findElement(By.id("forename-err")).getText();
    }

    public String getMessageError() {
        return driver.findElement(By.id("message-err")).getText();
    }

    public ContactPage setForename(String forename) {
        driver.findElement(By.id("forename")).sendKeys(forename);
        return this;
    }

    public ContactPage setMessage(String message) {
        driver.findElement(By.id("message")).sendKeys(message);
        return this;
    }

    public String getSuccessMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(60))
            .until(d -> d.findElement(By.cssSelector(".alert-success")))
            .getText();
    }
}
