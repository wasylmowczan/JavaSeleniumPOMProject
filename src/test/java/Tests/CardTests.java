package Tests;

import PageObjects.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTests {
    WebDriver driver;

    @BeforeEach
    public void testSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.navigate().to("https://fakestore.testelka.pl");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

    @Test
    private void addProductToCart() {
        String productId = "386";
        String productURL = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
        ProductPage productPage = new ProductPage(driver);
        int productAmount = productPage.goTo(productURL).addToCard().viewCard().getProductAmount(productId);
        assertTrue(productAmount == 1,
                "Remove button was not found for a product with "+ productId +" (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }
}
