package huynhquanghuy;

import java.util.logging.Logger;

public class InterfaceFieldModificationExample {

    private static final Logger LOGGER = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Maximum allowed users: " + AppConfig.MAX_USERS);
    }
}
