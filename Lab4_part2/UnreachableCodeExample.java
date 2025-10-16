package huynhquanghuy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger LOGGER = Logger.getLogger(UnreachableCodeExample.class.getName());

    public static int getNumber() {
        int number = 42;
        LOGGER.info("Returning number: " + number);
        return number;
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "The number is: {0}", getNumber());
    }
}
