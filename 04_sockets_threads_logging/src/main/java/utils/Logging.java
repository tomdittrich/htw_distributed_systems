package utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Creates a logger and a log-file
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 */

public class Logging {
    private static Logger logger;
    private static FileHandler fileHandler;
    private SimpleFormatter formatter;
    private LocalDateTime dateForLogFile = LocalDateTime.now();

    // Path and filename for log-file
    private final String LOGFILEPATH = "log"
            + dateForLogFile.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
            + ".txt";

    public static Logger getWeatherLogger() {
        return logger;
    }

    public Logging() {
        // Name of the logger, in common case it is the package + class name
        // here "utils.Logging"
        logger = Logger.getLogger(Logging.class.getName());
        try {
            fileHandler = new FileHandler(LOGFILEPATH);
        } catch (IOException e) {
            System.out.println("Cannot create logfile.");
        }

        // delete the formater for the default xml output
        formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);

        // adds filehandler
        logger.addHandler(fileHandler);
    }
}
