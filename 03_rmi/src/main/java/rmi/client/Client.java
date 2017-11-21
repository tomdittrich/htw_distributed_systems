package rmi.client;

import utils.MeasurePoint;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Receive weather dates from a server. Basic class for further clients with
 * different communication ways.
 * The methods for connecting, disconnecting and get temperatures from server
 * must be implemented separate.
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public abstract class Client {

    String serverIP;
    int serverPort;
    DateFormat dateFormat;
    Date dayDate;
    ArrayList<MeasurePoint> measurePointsForOneDay;

    /**
     * Default Constructor, with a predefined localhost server and port 10999
     */
    public Client() {
        this("127.0.0.1", 10999);
    }

    /**
     * Constructor
     *
     * @param serverIP   server ip
     * @param serverPort server port
     */
    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
    }

    /**
     * Prints out the whole temperatures of a day
     * with additional statistics (Minimum, Maximum and Average)
     *
     * @param date the date of the day
     * @throws Exception
     */
    public void printWholeWeatherForADay(Date date) throws Exception {
        this.dayDate = date;
        this.measurePointsForOneDay = getTemperaturesForADayFromServer(dayDate);
        printWeatherForADay();
        printWeatherStatisticForADay();
    }

    /**
     * Prints out the whole temperatures of a day
     * with additional statistics (minimum, maximum and average)
     *
     * @param date the date of the day
     * @throws Exception
     */
    public void printWholeWeatherForADay(String date) throws Exception {
        printWholeWeatherForADay(convertStringToDayDate(date));
    }

    /**
     * Converts a date-string to a true date
     *
     * @param stringDate the date of the day as string
     * @return the date of the day as date: yyyy-MM-dd
     * @throws ParseException
     */
    private Date convertStringToDayDate(String stringDate) throws ParseException {
        dateFormat.parse(stringDate);
        return dateFormat.parse(stringDate);
    }

    /**
     * Prints out the weather for a day
     */
    private void printWeatherForADay() {
        System.out.println(toString());
    }

    /**
     * Prints out the additional statistic for a day
     */
    void printWeatherStatisticForADay() {
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
        float min = measurePointsForOneDay.get(0)._temperature;

        for (MeasurePoint measurePoint : measurePointsForOneDay) {
            if (measurePoint._temperature < min) min = measurePoint._temperature;
        }
        return min;
    }

    /**
     * Calculates the maximum temperature of a day
     *
     * @return the maximum
     */
    private double calculateMaximumTemperature() {
        float max = measurePointsForOneDay.get(0)._temperature;

        for (MeasurePoint measurePoint : measurePointsForOneDay) {
            if (measurePoint._temperature > max) max = measurePoint._temperature;
        }
        return max;
    }

    /**
     * Calculates the average temperature of a day
     *
     * @return the average
     */
    private double calculateAverageTemperature() {
        float avg = 0;

        for (MeasurePoint measurePoint : measurePointsForOneDay) {
            avg += measurePoint._temperature;
        }
        return (avg / measurePointsForOneDay.size());
    }

    /**
     * Returns the weather for a day, without statistic
     *
     * @return the temperatures for every hour
     */
    @Override
    public String toString() {
        String output;
        String newLine = System.getProperty("line.separator");

        output = "The weather for: " + this.dayDate + newLine;

        for (MeasurePoint measurePoint : measurePointsForOneDay) {
            output += String.format("Hour: %02d Temperature: %05.2f °C", measurePoint._timeStamp.getHours(), measurePoint._temperature);
            output += newLine;
        }

        return output;
    }

    /**
     * Get the temperature data for a day from the server
     *
     * @param date the date of the day
     * @return Arraylist with all 24 temperatures of a day
     * @throws Exception
     */
    abstract ArrayList<MeasurePoint> getTemperaturesForADayFromServer(Date date) throws Exception;

    /**
     * Connects to the server
     *
     * @throws Exception
     */
    public abstract void connectToServer() throws Exception;

    /**
     * Disconnects from the server
     *
     * @throws RemoteException
     */
    public abstract void closeConnectionToServer() throws RemoteException;

}
