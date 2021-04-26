package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {
    private final By totalPriceLocator = By.cssSelector(".cart-contents");

    protected HeaderPage(WebDriver driver) {
        super(driver);
    }

    public CartPage viewCart() {
        driver.findElement(totalPriceLocator).click();
        return new CartPage(driver);
    }
}