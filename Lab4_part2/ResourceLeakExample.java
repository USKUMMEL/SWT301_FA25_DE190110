package huynhquanghuy;
import java.io.*;
import java.util.logging.Logger;

public class ResourceLeakExample {

    private static final Logger LOGGER = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
            }
        } catch (IOException e) {
            LOGGER.severe("Error reading file: " + e.getMessage());
        }
    }
}
