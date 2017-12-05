package utils;

import java.util.ArrayList;

/**
 * Stored data interface
 *
 * @author Uli
 * @version 0.1
 */
public interface StoredData {

    /**
     * Gets data out of stored data
     *
     * @param request of which data is wanted
     * @return requested data as response
     */
    String getData(String request);

    /**
     * Gets list of days in csv file
     *
     * @return found days in csv file
     */
    ArrayList<String> getDaysOutOfFile();
}
