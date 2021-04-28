package Tests;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.OrderReceivedPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentTests extends BaseTest {
    private final String name = "Ola";
    private final String lastName = "Nowak";
    private final String countryCode = "BE";
    private final String address = "Wielicka 2/15";
    private final String postalCode = "30-658as";
    private final String city = "Sopot";
    private final String phone = "6666666";
    private final String emailAddress = "test1@testelka.pl";
    private final String cardNumber = "4242424242424242";
    private final String expirationDate = "0530";
    private final String cvcCode = "123";

    @Test
    public void buyWithoutAccountTest() {
        ProductPage productPage = new ProductPage(driver).goTo("https://fakestore.testelka.pl/product/egipt-el-gouna/");
        productPage.demoNotice.close();
        CartPage cartPage = productPage.addToCart().viewCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        OrderReceivedPage orderReceivedPage = checkoutPage.typeName(name)
                .typeLastName(lastName)
                .chooseCountry(countryCode)
                .typeAddress(address)
                .typePostalCode(postalCode)
                .typeCity(city)
                .typePhone(phone)
                .typeEmail(emailAddress)
                .typeCardNumber(cardNumber)
                .typeCardExpirationDate(expirationDate)
                .typeCvcCode(cvcCode)
                .selectAcceptTerms()
                .order();
        boolean isOrderSuccessful = orderReceivedPage.isOrderSuccessful();
        assertTrue(isOrderSuccessful, "No order successful message found.");
    }
}