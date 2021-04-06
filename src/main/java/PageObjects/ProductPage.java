package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    private By viewCartButton = By.cssSelector(".woocommerce-message>.button");


    public ProductPage goTo(String productURL) {
        driver.navigate().to(productURL);
        return this;
    }

    public ProductPage addToCard() {
        WebElement addButton = driver.findElement(addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addButton.click();
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        return this;
    }

    public CartPage viewCard() {
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        return new CartPage(driver);
    }
}
