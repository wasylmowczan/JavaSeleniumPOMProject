package Utils;

public class TestDataReader extends FileReader {
    private String testDataLocation;
    private Product product;
    private String categoryURL;
    private String[] productPages;
    private Consumer customer;
    private Address address;
    private Contact contact;
    private Card card;
    public TestDataReader(String testDataLocation){
        super(testDataLocation);
        this.testDataLocation = testDataLocation;
    }
    void loadData(){
        product = new Product(properties);
        customer = new Consumer(properties);
        address = new Address(properties);
        contact = new Contact(properties);
        card = new Card(properties);
        categoryURL = properties.getProperty("category.url");
        productPages = properties.getProperty("products.url").split(",");
    }
    public String getTestDataLocation() {
        return testDataLocation;
    }
    public Product getProduct() {
        return product;
    }
    public String getCategoryURL() {
        return categoryURL;
    }
    public String[] getProductPages() {
        return productPages;
    }
    public Consumer getCustomer() {
        return customer;
    }
    public Address getAddress() {
        return address;
    }
    public Contact getContact() {
        return contact;
    }
    public Card getCard() {
        return card;
    }
}
