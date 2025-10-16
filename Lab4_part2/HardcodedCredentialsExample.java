package huynhquanghuy;

import java.util.logging.Logger;

public class HardcodedCredentialsExample {

    private static final Logger LOGGER = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        final String USERNAME = "admin";
        final String PASSWORD = "123456";

        if (USERNAME.equals("admin") && PASSWORD.equals("123456")) {
            LOGGER.info("Access granted");
        } else {
            LOGGER.warning("Access denied");
        }
    }
}
