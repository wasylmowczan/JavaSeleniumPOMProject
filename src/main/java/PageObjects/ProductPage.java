package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
    public HeaderPage header;
    public DemoFooterPage demoNotice;
    private final WebDriverWait wait;
    private final By addToCartButtonLocator = By.cssSelector("button[name='add-to-cart']");
    private final By viewCartButtonLocator = By.cssSelector(".woocommerce-message>.button");
    private final By productQuantityFieldLocator = By.cssSelector("input.qty");
    public ProductPage(WebDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
        demoNotice = new DemoFooterPage(driver);
        wait = new WebDriverWait(driver, 7);
    }

    public ProductPage goTo(String productUrl) {
        driver.navigate().to(productUrl);
        return this;
    }

    public ProductPage addToCart() {
        WebElement addButton = driver.findElement(addToCartButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator));
        return this;
    }

    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
        return new CartPage(driver);
    }

    public ProductPage addToCart(int quantity) {
        WebElement quantityField = driver.findElement(productQuantityFieldLocator);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
        return addToCart();
    }
}