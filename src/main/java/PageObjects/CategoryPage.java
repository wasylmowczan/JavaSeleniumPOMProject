package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
    WebDriver driver;

    public CategoryPage(WebDriver driver) {
    this.driver = driver;
    }

    public CategoryPage goTo(String url) {
        driver.navigate().to(url);
        return this;
    }

    public CategoryPage addToCart(String productId) {
        By categoryPageAddToCartButton = By.cssSelector(".post-" + productId + ">.add_to_cart_button");
        driver.findElement(categoryPageAddToCartButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.attributeContains(categoryPageAddToCartButton, "class", "added"));
        return this;
    }

    public CartPage viewCart() {
        By viewCartButton = By.cssSelector(".added_to_cart");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        return new CartPage(driver);
    }
}
