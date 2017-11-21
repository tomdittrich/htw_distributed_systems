package rmi.server;

import org.apache.commons.lang3.time.DateUtils;
import rmi.client.WeatherClient;
import sockets.server.Server;
import utils.MeasurePoint;
import utils.StoredData;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Send requested weather dates to a client per RMI
 *
 * @author Uli
 * @version 0.3
 */
public class ServerImpl extends Server implements WeatherServer {

    /**
     * ServerImpl stores weather data of .csv file in this ArrayList at startup
     */
    private ArrayList<MeasurePoint> storedMeasurePoints;

    /**
     * List of clients registered for update push notifications
     */
    private ArrayList<WeatherClient> clientRegister = new ArrayList<>();

    /**
     * Constructor
     *
     * @param port       number of port
     * @param storedData stored data of server
     */
    public ServerImpl(int port, StoredData storedData) {
        super(port, storedData);
        try {
            storedMeasurePoints = this.initializeStoredMeasurePointArray(this.getStoredData().getDaysOutOfFile());
        } catch (ParseException e) {
            System.err.println("Parse exception: Can't initialize list of weather measure points. " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Converts String responses for an list of day requests to an ArrayList of MeasurePoints.
     * (Just a helper function to get data out of .csv once at start)
     *
     * @param days list with day requests
     * @return proper ArrayList with MeasurePoints to start with
     * @throws ParseException if parsing fails (should not happen)
     */
    private ArrayList<MeasurePoint> initializeStoredMeasurePointArray(ArrayList<String> days) throws ParseException {
        ArrayList<MeasurePoint> tempStoredMeasurePoints = new ArrayList<>();
        String[] temp;
        double[] temperaturesArray;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.GERMAN);
        Date date;

        for (String day : days) {
            String response = this.getStoredData().getData(day);
            if (response.startsWith("ERROR")) continue;
            temp = response.split(",");
            temperaturesArray = Arrays.stream(temp)
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            int hour = 0;
            for (double temperature : temperaturesArray) {
                date = format.parse(day + "-" + hour);
                tempStoredMeasurePoints.add(new MeasurePoint(date, ((float) temperature)));
                hour++;
            }
        }
        return tempStoredMeasurePoints;
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Connection established...";
    }

    @Override
    public List<MeasurePoint> getTemperatures(Date date) throws RemoteException {
        ArrayList<MeasurePoint> tempMeasurePoints = new ArrayList<>();
        int counter = 0;
        for (MeasurePoint measure : this.storedMeasurePoints) {
            if (DateUtils.isSameDay(date, measure._timeStamp)) {
                tempMeasurePoints.add(measure);
                counter++;
            }
        }
        if (counter == 24)
            return tempMeasurePoints;
        else
            throw new RemoteException("Didn't find exact 24 entries for requested day on server, sorry.");
    }

    @Override
    public boolean register(WeatherClient weatherClient) throws RemoteException {
        return clientRegister.add(weatherClient);
    }

    @Override
    public boolean deregister(WeatherClient weatherClient) throws RemoteException {
        return clientRegister.remove(weatherClient);
    }

    /**
     * Add new weather data or replace old temperature entry if measure point already exists
     *
     * @param newMeasurePoint with new weather data
     * @return true if successful
     */
    private boolean addOrReplaceMeasurePoint(MeasurePoint newMeasurePoint) {
        for (MeasurePoint existingMeasurePoint : this.storedMeasurePoints) {
            if (existingMeasurePoint._timeStamp.compareTo(newMeasurePoint._timeStamp) == 0) {
                existingMeasurePoint._temperature = newMeasurePoint._temperature;
                return true;
            }
        }
        return storedMeasurePoints.add(newMeasurePoint);
    }

    /**
     * Checks if 24 entries are available for day of measure point
     *
     * @param checkMeasurePoint measure point of day to check
     * @return true if valid
     */
    private boolean dayDataIsValid(MeasurePoint checkMeasurePoint) {
        int counter = 0;
        for (MeasurePoint oldMeasure : this.storedMeasurePoints) {
            if (DateUtils.isSameDay(checkMeasurePoint._timeStamp, oldMeasure._timeStamp)) {
                counter++;
            }
        }
        return (counter == 24);
    }

    /**
     * User adds new entry or replaces old one
     * (Side effect: registered clients get informed if 24 entries are available
     * for day of updated measure point)
     *
     * @param newMeasurePoint with new weather data
     * @return true if successful
     */
    private boolean userAddsOrReplacesEntry(MeasurePoint newMeasurePoint) {
        if (this.addOrReplaceMeasurePoint(newMeasurePoint) && dayDataIsValid(newMeasurePoint)) {
            for (WeatherClient client : clientRegister) {
                try {
                    client.updateTemperature(newMeasurePoint);
                } catch (RemoteException e) {
                    return false;
                }
            }
            return true;
        } else
            return false;
    }

    /**
     * Simple CLI menu
     *
     * @throws ParseException if date parsing fails
     */
    public void useCLI() throws ParseException {
        boolean exit = false;
        float temperature;
        Date date;
        Scanner scanner = new Scanner(System.in);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.GERMAN);
        format.setLenient(false);

        while (!exit) {
            switch (scanner.next()) {
                case "exit":
                    exit = true;
                    break;
                case "update":
                    try {
                        date = format.parse(scanner.next());
                    } catch (ParseException e) {
                        System.out.println("Invalid date. (Please use \"yyyy-MM-dd-HH\")");
                        break;
                    }
                    temperature = Float.parseFloat(scanner.next());
                    if (userAddsOrReplacesEntry(new MeasurePoint(date, temperature))) {
                        System.out.println("Measure point updated successfully. Informing clients...");
                    } else
                        System.out.println("Measure point updated successfully.");
                    break;
                default:
                    System.out.println("Invalid command ...");
                    break;
            }
        }
    }
}
