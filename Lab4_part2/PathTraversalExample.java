    package huynhquanghuy;
    import java.io.*;
    import java.util.logging.Logger;

    public class PathTraversalExample {
        private static final Logger LOGGER = Logger.getLogger(PathTraversalExample.class.getName());

        public static void main(String[] args) {
            if (args.length == 0) {
                LOGGER.warning("No file specified.");
                return;
            }

            String fileName = args[0];
            File file = new File(fileName);

            if (file.exists()) {
                LOGGER.info("Reading file: " + file.getAbsolutePath());
            } else {
                LOGGER.warning("File not found: " + fileName);
            }
        }
    }
