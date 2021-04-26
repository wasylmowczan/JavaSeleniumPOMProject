package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    private final WebDriverWait wait;
    private final By shopTableLocator = By.cssSelector("form>.shop_table");
    private final By productQuantityFieldLocator = By.cssSelector("div.quantity>input");
    private final String removeProductButtonCssSelector = "a[data-product_id='<product_id>']";
    private final By cartItemLocator = By.cssSelector(".cart_item");
    private final By updateCartButtonLocator = By.cssSelector("[name='update_cart']");
    private final By loaderLocator = By.cssSelector(".blockOverlay");
    public CartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public int getProductQuantity() {
        waitForShopTable();
        String quantityString = driver.findElement(productQuantityFieldLocator).getAttribute("value");
        return Integer.parseInt(quantityString);
    }

    public boolean isProductInCart(String productId) {
        waitForShopTable();
        By removeProductLocator = By.cssSelector(removeProductButtonCssSelector.replace("<product_id>", productId));
        int productRecords = driver.findElements(removeProductLocator).size();
        boolean presenceOfProduct = false;
        if (productRecords == 1) {
            presenceOfProduct = true;
        } else if (productRecords > 1) {
            throw new IllegalArgumentException("There is more than one record for the product in cart.");
        }
        return presenceOfProduct;
    }

    public int getNumberOfProducts() {
        waitForShopTable();
        return driver.findElements(cartItemLocator).size();
    }

    private void waitForShopTable() {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
    }

    public CartPage changeQuantity(int quantity) {
        WebElement quantityField = driver.findElement(productQuantityFieldLocator);
        quantityField.clear();
        quantityField.sendKeys(Integer.toString(quantity));
        return new CartPage(driver);
    }

    public CartPage updateCart() {
        WebElement updateButton = driver.findElement(updateCartButtonLocator);
        wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        updateButton.click();
        return new CartPage(driver);
    }

    public CartPage removeProduct(String productId) {
        By removeProductLocator = By.cssSelector(removeProductButtonCssSelector.replace("<product_id>", productId));
        driver.findElement(removeProductLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        return new CartPage(driver);
    }

    public boolean isCartEmpty() {
        int shopTableElements = driver.findElements(shopTableLocator).size();
        if (shopTableElements == 1) {
            return false;
        } else if (shopTableElements == 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Wrong number of shop table elements: there can be only one or none");
        }
    }
}