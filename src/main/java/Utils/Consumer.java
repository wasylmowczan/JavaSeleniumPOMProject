package Utils;

import java.util.Properties;

public class Consumer {
    private final String name;
    private final String lastName;

    public Consumer(Properties properties) {
        name = properties.getProperty("customer.name");
        lastName = properties.getProperty("customer.lastName");
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
