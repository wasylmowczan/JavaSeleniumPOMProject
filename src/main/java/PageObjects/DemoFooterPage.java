package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemoFooterPage extends BasePage {
    private final By demoNoticeLocator = By.cssSelector(".woocommerce-store-notice__dismiss-link");

    protected DemoFooterPage(WebDriver driver) {
        super(driver);
    }

    public void close() {
        driver.findElement(demoNoticeLocator).click();
    }
}