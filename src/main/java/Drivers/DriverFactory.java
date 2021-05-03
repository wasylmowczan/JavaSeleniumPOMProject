package Drivers;

import Utils.ConfigurationManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private RemoteWebDriver driver;

    public WebDriver create() {
        Browser browserType = Browser.valueOf(ConfigurationManager.getInstance().getBrowser());
        switch (browserType) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException("Provided browser doesn't exist");
        }
    }

    private WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        return getDriver(options);
    }

    private WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.VERSION, "66");
        return getDriver(options);
    }

    private WebDriver getDriver(MutableCapabilities options) {
        try {
            driver = new RemoteWebDriver(new URL(ConfigurationManager.getInstance().getHubUrl()), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e + " was thrown. HubUrl in the configuration file is incorrect or missing. " +
                    "Check the configuration file: " + ConfigurationManager.getInstance().getConfigurationLocation());
        }
        return driver;
    }
}