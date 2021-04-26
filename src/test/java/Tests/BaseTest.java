package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void testSetUp() throws MalformedURLException {
        final String USERNAME = "WasylSH";
        final String ACCESS_KEY = "050b93a3-bcd6-45b3-b36c-05b8289b4184";
        String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Windows 10");
        caps.setCapability("browserVersion", "latest");

        caps.setCapability("build", "Onboarding Sample App - Java-Junit5");

        //set your test case name so that it shows up in Sauce Labs
        caps.setCapability("name", "1-first-test");

        driver = new RemoteWebDriver(new URL(URL), caps);
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//
//        driver.manage().window().setSize(new Dimension(1290, 730));
//        driver.manage().window().setPosition(new Point(8, 30));
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}