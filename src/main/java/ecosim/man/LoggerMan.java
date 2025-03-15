package ecosim.man;


import static ecosim.common.io.ConsoleIO.eprint;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import ecosim.common.io.FileIO;


public class LoggerMan {
    private static final Logger LOGGER = Logger.getLogger("ecosim");
    private static final String LOG_FILE_DIR = "src/main/java/ecosim/logs";
    private static final String LOG_FILE_PATH = LOG_FILE_DIR + "/ecosim.log";

    private LoggerMan() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    static {
        initializeLogger();
    }

    private static void initializeLogger() {
        try {
            if (!FileIO.mkdir(LOG_FILE_DIR)) {
                throw new IOException("Failed to create log directory");
            }

            // Remove any default handlers i.e the console logger
            for (Handler handler : LOGGER.getHandlers()) {
                LOGGER.removeHandler(handler);
            }

            // Disable use of parent handlers to prevent logging to console
            LOGGER.setUseParentHandlers(false);

            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new PrettyFormatter());
            LOGGER.addHandler(fileHandler);

            // Set logger level
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            eprint("Logger setup failed: " + e.getMessage());
        }
    }

    public static void log(Level level, String message, Object... args) {
        LOGGER.log(level, message, args);
    }

    private static class PrettyFormatter extends Formatter {
        private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());

        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();

            sb.append("[").append(record.getLevel()).append("] ");
            sb.append("(").append(DATE_FORMATTER.format(Instant.ofEpochMilli(record.getMillis()))).append(") ");
            sb.append(":: ").append(record.getSourceClassName()).append(".").append(record.getSourceMethodName())
                .append(" ");
            sb.append(":: ").append(formatMessage(record)).append(System.lineSeparator());

            return sb.toString();
        }

    }

}
