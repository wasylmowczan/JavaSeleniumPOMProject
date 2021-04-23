package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    private final By demoNoticeLocator = By.cssSelector(".woocommerce-store-notice__dismiss-link");

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeDemoNotice() {
        driver.findElement(demoNoticeLocator).click();
    }
}
