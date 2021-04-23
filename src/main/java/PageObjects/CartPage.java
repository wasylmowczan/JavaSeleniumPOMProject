package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    private final By shopTableLocator = By.cssSelector(".shop_table");
    private final By productQuantityFieldLocator = By.cssSelector("div.quantity>input");
    private final String removeProductButtonCssSelector = "a[data-product_id='<product_id>']";
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getProductsAmount(String productId) {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
        By removeProductLocator = By.cssSelector(removeProductButtonCssSelector.replace("<product_id>", productId));
        return driver.findElements(removeProductLocator).size();
    }

    public int getProductQuantity() {
        String quantityString = driver.findElement(productQuantityFieldLocator).getAttribute("value");
        return Integer.parseInt(quantityString);
    }
}