package rmi.server;

import org.apache.commons.lang3.time.DateUtils;
import rmi.client.WeatherClient;
import utils.MeasurePoint;
import utils.StoredData;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
public class ServerRmi extends Server implements WeatherServer {

    /**
     * ServerRmi stores weather data of .csv file in this ArrayList at startup
     */
    private ArrayList<MeasurePoint> storedMeasurePoints;

    /**
     * List of clients registered for update push notifications
     */
    private ArrayList<WeatherClient> clientRegister = new ArrayList<>();

    /**
     * Registry
     */
    private Registry registry;

    /**
     * Registry name
     */
    private String registryName;

    /**
     * Constructor
     *
     * @param port       number of port
     * @param storedData stored data of server
     */
    public ServerRmi(int port, StoredData storedData, String registryName) {
        super(port, storedData);
        this.registryName = registryName;
        try {
            storedMeasurePoints = this.initializeStoredMeasurePointArray(this.getStoredData().getDaysOutOfFile());
        } catch (ParseException e) {
            System.err.println("Parse exception: Can't initialize list of weather measure points. " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Starts the Server
     *
     * @throws RemoteException       as required
     * @throws AlreadyBoundException if already bound
     */
    @Override
    public void work() throws RemoteException, AlreadyBoundException {
        WeatherServer stub = (WeatherServer) UnicastRemoteObject.exportObject(this, 4711);
        registry = LocateRegistry.createRegistry(getPort());
        registry.bind(registryName, stub);
        System.out.println("Server ready");
    }

    /**
     * Shutdown of Server
     *
     * @throws RemoteException   as required
     * @throws NotBoundException if not bound
     */
    @Override
    public void shutdownServer() throws RemoteException, NotBoundException {
        registry.unbind(registryName);
        UnicastRemoteObject.unexportObject(this, false);
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
        else // TODO: improve Exceptions (use different exceptions on different occasions!!!)
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
    public void useCLI() throws ParseException, RemoteException, NotBoundException {
        boolean exit = false;
        float temperature;
        Date date;
        Scanner scanner = new Scanner(System.in);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.GERMAN);
        format.setLenient(false);

        while (!exit) {
            switch (scanner.next()) {
                case "exit":
                    shutdownServer();
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
