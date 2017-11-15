package server;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Stored csv data
 *
 * @author Uli
 * @version 0.1
 */
public class StoredCsvData implements StoredData {

    /**
     * Location of file (file name)
     */
    private final String fileLocation;

    /**
     * Constructor
     *
     * @param fileLocation file name (of csv file)
     */
    public StoredCsvData(String fileLocation) {
        super();
        this.fileLocation = fileLocation;
    }

    @Override
    public String getData(String request) {
        int columnNumber = this.getColumnNumberOfRequest(request);

        if (columnNumber == -1) {
            return "ERROR: No data available for that day. Wrong input: " + request;
        } else if (columnNumber == -2) {
            return "ERROR: File not found on server. Internal error. Sorry for that.";
        } else {
            String columnData = getColumnOfCsvFile(columnNumber);
            if (columnData.startsWith("ERROR"))
                return columnData + request;
            else if (!isValidColumnData(columnData)) {
                return "ERROR: Not enough valid data available on server for that day: " + request;
            } else
                return columnData;
        }
    }

    /**
     * Gets matching column out of csv file
     *
     * @param columnNumber the requested column number
     * @return data of requested column (or ERROR message)
     */
    private String getColumnOfCsvFile(int columnNumber) {
        StringBuilder column = new StringBuilder();
        CSVReader reader;

        try {
            reader = openCsvFileForReading(fileLocation);
        } catch (FileNotFoundException e) {
            return "ERROR: File not found on server. Internal error. Sorry for that.";
        }

        try {
            String[] nextLine;
            int iteration = 0;
            while ((nextLine = reader.readNext()) != null) {
                // skip header line
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // nextLine[] is an array of values from the line
                column.append(nextLine[columnNumber]).append(",");
            }
        } catch (Exception e) {
            return "ERROR: Not enough valid data available on server for that day: ";
        }

        return column.toString();
    }

    /**
     * Opens csv file for Reading
     *
     * @param fileName of csv file
     * @return opened "ready-to-read" file handler
     * @throws FileNotFoundException if file is not found
     */
    private CSVReader openCsvFileForReading(String fileName) throws FileNotFoundException {
        return new CSVReader(new FileReader(fileName));
    }


    /**
     * Checks if column data is valid
     *
     * @param uncheckedData input data to check
     * @return true if column data consists of 24 comma separated doubles (temperatures)
     */
    private boolean isValidColumnData(String uncheckedData) {
        try {
            String[] temp = uncheckedData.split(",");
            double[] temperaturesArray = Arrays.stream(temp)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            return temperaturesArray.length == 24;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Find matching column number for a request in csv file
     *
     * @param request format is YYYY-MM-DD
     * @return column number or -1 if day not found and -2 if FileNotFoundError
     */
    private int getColumnNumberOfRequest(String request) {
        CSVReader reader;
        try {
            reader = openCsvFileForReading(fileLocation);
        } catch (FileNotFoundException e) {
            return -2;
        }

        try {
            String[] firstLine = reader.readNext();
            // compute column number of matching day in header line
            for (int i = 0; i < firstLine.length; i++) {
                if (firstLine[i].compareTo(request) == 0)
                    return i;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        }
        // if day is not found in for-loop return -1
        return -1;
    }
}
