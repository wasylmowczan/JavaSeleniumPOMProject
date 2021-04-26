package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By shopTableLocator = By.cssSelector(".shop_table");
    private By productQuantityFieldLocator = By.cssSelector("div.quantity>input");
    private String removeProductButtonCssSelector = "a[data-product_id='<product_id>']";
    private By cartItemLocator = By.cssSelector(".cart_item");

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
        if (productRecords==1){
            presenceOfProduct = true;
        } else if (productRecords>1){
            throw new IllegalArgumentException("There is more than one record for the product in cart.");
        }
        return presenceOfProduct;
    }

    public int getNumberOfProducts() {
        waitForShopTable();
        return driver.findElements(cartItemLocator).size();
    }

    private void waitForShopTable(){
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
    }

}