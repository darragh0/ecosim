package ecosim;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggerManager {
    private static final Logger LOGGER = Logger.getLogger("ecosim");
    private static final String LOG_FILE_DIR = "src/main/java/ecosim/logs";
    private static final String LOG_FILE_PATH = LOG_FILE_DIR + "/ecosim.log";

    static {
        initializeLogger();
    }

    private LoggerManager() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    private static void initializeLogger() {
        try {
            Files.createDirectories(Paths.get(LOG_FILE_DIR));

            // Remove any default handlers i.e the console logger
            for (Handler handler : LOGGER.getHandlers()) {
                LOGGER.removeHandler(handler);
            }
            // Disable use of parent handlers to prevent logging to console
            LOGGER.setUseParentHandlers(false);

            // file handler setup
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            // Set logger level
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }
    }

    public static void log(Level level, String message, Object... args) {
        LOGGER.log(level, message, args);
    }

}
