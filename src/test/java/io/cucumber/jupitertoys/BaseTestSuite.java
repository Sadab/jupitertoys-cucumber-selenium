package io.cucumber.jupitertoys;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BaseTestSuite {
    private static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    public WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @Before
    public void setupTest() {
        var url = dotenv.get("SELENIUM_URL");
        var browser = dotenv.get("SELENIUM_BROWSER");
        var wait = Integer.parseInt(dotenv.get("SELENIUM_WAIT"));
        var headless = Boolean.parseBoolean(dotenv.get("SELENIUM_HEADLESS"));

        if (browser.toLowerCase().equals("chrome")) {
            var options = new ChromeOptions();
            options.setHeadless(headless);
            options.addArguments(
                    "--disable-gpu",
                    "--window-size=1920,1200",
                    "--ignore-certificate-errors");

            driver = new ChromeDriver(options);
        } else if (browser.toLowerCase().equals("firefox")) {
            var options = new FirefoxOptions();
            options.setLogLevel(FirefoxDriverLogLevel.ERROR);
            options.setHeadless(headless);
            options.addArguments("-width 1920", "-height 1200");

            driver = new FirefoxDriver(options);
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @After
    public void teardownTest() {
        driver.close();
    }
}
