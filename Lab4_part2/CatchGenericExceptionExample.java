package huynhquanghuy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        String s = (args.length > 0) ? args[0] : null;

        if (s == null) {
            LOGGER.warning("Input string is null; skipping length operation.");
            return;
        }

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, "String length: {0}", s.length());
        }
    }
}
