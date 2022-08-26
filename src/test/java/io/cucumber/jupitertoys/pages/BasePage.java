package io.cucumber.jupitertoys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage<CurrentPage> {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public ContactPage clickContactMenu(){
        driver.findElement(By.cssSelector("#nav-contact a")).click();
        return new ContactPage(driver);
    }


}
