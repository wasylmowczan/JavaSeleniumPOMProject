package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
    WebDriverWait wait;
    //Niektóre lokatory się różnią od tych na filmie, ze względy na zmiany wartości atrybutów w aplikacji
    private final By firstNameFieldLocator = By.cssSelector("#billing_first_name");
    private final By lastNameFieldLocator = By.cssSelector("#billing_last_name");
    private final By countryCodeArrowLocator = By.cssSelector(".select2-selection__arrow");
    private final String countryCodeCssSelector = "li[id*='-<country_code>']";
    private final By addressFieldLocator = By.cssSelector("#billing_address_1");
    private final By postalCodeFieldLocator = By.cssSelector("#billing_postcode");
    private final By cityFieldLocator = By.cssSelector("#billing_city");
    private final By phoneFieldLocator = By.cssSelector("#billing_phone");
    private final By emailFieldLocator = By.cssSelector("#billing_email");
    private final By loadingIconLocator = By.cssSelector(".blockOverlay");
    private final By cardNumberFrameLocator = By.cssSelector("#stripe-card-element iframe");
    private final By cardNumberFieldLocator = By.cssSelector("[name='cardnumber']");
    private final By expirationDateFrameLocator = By.cssSelector("#stripe-exp-element iframe");
    private final By expirationDateFieldLocator = By.cssSelector("[name='exp-date']");
    private final By cvcFrameLocator = By.cssSelector("#stripe-cvc-element iframe");
    private final By cvcFieldLocator = By.cssSelector("[name='cvc']");
    private final By termsCheckboxLocator = By.cssSelector("#terms");
    private final By summaryOrderNumberLocator = By.cssSelector(".order>strong");
    private final By orderButtonLocator = By.cssSelector("#place_order");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public CheckoutPage typeName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameFieldLocator)).sendKeys(name);
        return this;
    }

    public CheckoutPage typeLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameFieldLocator)).sendKeys(lastName);
        return this;
    }

    public CheckoutPage chooseCountry(String countryCode) {
        wait.until(ExpectedConditions.elementToBeClickable(countryCodeArrowLocator)).click();
        By countryCodeLocator = By.cssSelector(countryCodeCssSelector.replace("<country_code>", countryCode));
        wait.until(ExpectedConditions.elementToBeClickable(countryCodeLocator)).click();
        return this;
    }

    public CheckoutPage typeAddress(String address) {
        wait.until(ExpectedConditions.elementToBeClickable(addressFieldLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addressFieldLocator)).sendKeys(address);
        return this;
    }

    public CheckoutPage typePostalCode(String postalCode) {
        wait.until(ExpectedConditions.elementToBeClickable(postalCodeFieldLocator)).sendKeys(postalCode);
        return this;
    }

    public CheckoutPage typeCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(cityFieldLocator)).sendKeys("Sopot");
        return this;
    }

    public CheckoutPage typePhone(String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(phoneFieldLocator)).sendKeys(phone);
        return this;
    }

    public CheckoutPage typeEmail(String emailAddress) {
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldLocator)).sendKeys(emailAddress);
        return this;
    }

    public CheckoutPage typeCardNumber(String cardNumber) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
        WebElement cardNumberElement = findElementInFrame(cardNumberFrameLocator, cardNumberFieldLocator);
        slowType(cardNumberElement, cardNumber);
        driver.switchTo().defaultContent();
        return this;
    }

    public CheckoutPage typeCardExpirationDate(String expirationDate) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
        WebElement expirationDateElement = findElementInFrame(expirationDateFrameLocator, expirationDateFieldLocator);
        slowType(expirationDateElement, expirationDate);
        driver.switchTo().defaultContent();
        return this;
    }

    public CheckoutPage typeCvcCode(String cvcCode) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
        WebElement cvcElement = findElementInFrame(cvcFrameLocator, cvcFieldLocator);
        slowType(cvcElement, cvcCode);
        driver.switchTo().defaultContent();
        return this;
    }

    private WebElement findElementInFrame(By frameLocator, By elementLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        return wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    private void slowType(WebElement element, String text) {
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(Character.toString(text.charAt(i)));
        }
    }

    public CheckoutPage selectAcceptTerms() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
        driver.findElement(termsCheckboxLocator).click();
        return this;
    }

    public OrderReceivedPage order() {
        driver.findElement(orderButtonLocator).click();
        return new OrderReceivedPage(driver);
    }
}