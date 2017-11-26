package sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * Receive weather dates from a server
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 31.10.17
 */

public class Client {

    private String serverIP;
    private int serverPort;

    private Socket serverSocket;
    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    private String date;
    private double[] temperaturesArray;

    /**
     * Default constructor, with a predefined local server
     */
    public Client() {
        this("localhost", 5173);
    }

    /**
     * Constructor
     *
     * @param serverIP   Server IP
     * @param serverPort Server Port
     */
    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    /**
     * Prints out the whole temperatures of a day
     * with additional statistics (Minimum, Maximum and Average)
     *
     * @param date the date of the day
     * @throws IOException
     */
    public void printWholeWeatherDataForADate(String date) throws IOException {
        this.date = date;
        String rawData = getDataFromServer(date);

        if (rawData.startsWith("ERROR")) {
            System.out.println(rawData);
        } else {
            convertToArray(rawData);

            printWeatherData();
            printWeatherDataStatistic();
        }
    }

    /**
     * Connects to the server
     *
     * @throws IOException
     */
    public void connectToServer() throws IOException {
        serverSocket = new Socket(serverIP, serverPort);
        serverWriter = new PrintWriter(serverSocket.getOutputStream());
        serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
    }

    /**
     * Closes all open connections
     *
     * @throws IOException
     */
    public void closeConnectionToServer() throws IOException {
        serverSocket.close();
        serverWriter.close();
        serverReader.close();
    }

    /**
     * Get the data (weather/error) from the server
     *
     * @param date the date of the day
     * @return the raw data
     * @throws IOException
     */
    private String getDataFromServer(String date) throws IOException {
        serverWriter.println(date);
        serverWriter.flush();

        return serverReader.readLine();
    }

    /**
     * Converts the raw weather data into an array
     *
     * @param rawData the raw weather data package
     */
    private void convertToArray(String rawData) {
        String[] temp = rawData.split(",");

        temperaturesArray = Arrays.stream(temp)
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    /**
     * Prints out the weather for a day
     */
    private void printWeatherData() {
        System.out.println(toString());
    }

    /**
     * Prints out the additional statistic for a day
     */
    private void printWeatherDataStatistic() {
        double min = calculateMinimumTemperature();
        double max = calculateMaximumTemperature();
        double avg = calculateAverageTemperature();

        System.out.printf("Minimum temperature: %05.2f °C" + System.lineSeparator(), min);
        System.out.printf("Maximum temperature: %05.2f °C" + System.lineSeparator(), max);
        System.out.printf("Average temperature: %05.2f °C" + System.lineSeparator(), avg);
    }

    /**
     * Calculates the minimum temperature of a day
     *
     * @return the minimum
     */
    private double calculateMinimumTemperature() {
        double min = temperaturesArray[0];

        for (double value : temperaturesArray) {
            if (value < min) min = value;
        }
        return min;
    }

    /**
     * Calculates the maximum temperature of a day
     *
     * @return the maximum
     */
    private double calculateMaximumTemperature() {
        double max = temperaturesArray[0];

        for (double value : temperaturesArray) {
            if (value > max) max = value;
        }
        return max;
    }

    /**
     * Calculates the average temperature of a day
     *
     * @return the average
     */
    private double calculateAverageTemperature() {
        double avg = 0;

        for (double value : temperaturesArray) {
            avg += value;
        }
        return (avg / temperaturesArray.length);
    }

    /**
     * Returns the weather for a day, without statistic
     *
     * @return the temperatures for every hour
     */
    @Override
    public String toString() {
        int hour = 0;
        String output;
        String newLine = System.getProperty("line.separator");

        output = "The weather for: " + this.date + newLine;

        for (double value : temperaturesArray) {
            output += String.format("Hour: %02d Temperature: %05.2f °C", hour, value);
            output += newLine;
            hour++;
        }

        return output;
    }

}