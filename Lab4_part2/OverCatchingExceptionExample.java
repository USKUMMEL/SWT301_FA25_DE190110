package huynhquanghuy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OverCatchingExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(OverCatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            LOGGER.info("Accessing element at index 10...");
            int value = arr[10];
            LOGGER.info(() -> "Value: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE, "Array index out of bounds: {0}", e.getMessage());
        }
    }
}
