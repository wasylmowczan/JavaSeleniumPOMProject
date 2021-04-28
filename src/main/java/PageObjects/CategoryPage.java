package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage {
    private final WebDriverWait wait;
    private final By viewCartButtonLocator = By.cssSelector(".added_to_cart");
    private final String addToCartButtonCssSelector = ".post-<product_id>>.add_to_cart_button";
    public DemoFooterPage demoNotice;

    public CategoryPage(WebDriver driver) {
        super(driver);
        demoNotice = new DemoFooterPage(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public CategoryPage goTo(String url) {
        driver.navigate().to(url);
        return this;
    }

    public CategoryPage addToCart(String productId) {
        By addToCartButton = By.cssSelector(addToCartButtonCssSelector.replace("<product_id>", productId));
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.attributeContains(addToCartButton, "class", "added"));
        return this;
    }

    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
        return new CartPage(driver);
    }
}