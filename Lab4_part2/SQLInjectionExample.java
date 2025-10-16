package huynhquanghuy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInjectionExample {

    private static final Logger LOGGER = Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        // Chỉ log nếu INFO được bật, tránh vi phạm S2629
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(String.format("Executing query: %s", query));
        }
    }
}