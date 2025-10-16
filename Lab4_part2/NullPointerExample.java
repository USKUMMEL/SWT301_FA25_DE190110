package huynhquanghuy;

import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger LOGGER = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = (args.length > 0) ? args[0] : null;

        if (text != null && !text.isEmpty()) {
            LOGGER.info("Text is not empty");
        } else {
            LOGGER.warning("Text is null or empty");
        }
    }
}
