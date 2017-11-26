package rmi.client;

import rmi.server.WeatherServer;
import utils.MeasurePoint;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Receive weather dates from a server.
 * RMI communication.
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class ClientRmi extends Client implements WeatherClient {

    private Registry registry;
    private WeatherServer stub;
    private WeatherClient clientStub;
    private String registryName;

    /**
     * Default Constructor, with a predefined localhost server and port 10999
     */
    public ClientRmi() {
        super();
    }

    /**
     * Constructor
     *
     * @param serverIP server ip
     * @param serverPort server port
     * @param registryName name of registry
     */
    public ClientRmi(String serverIP, int serverPort, String registryName) {
        super(serverIP, serverPort);
        this.registryName = registryName;
    }


        /**
         * Registers the client for update notifications
         *
         * @return registration gone? true
         * @throws RemoteException
         */
    public boolean register() throws RemoteException {
        clientStub = (WeatherClient) UnicastRemoteObject.exportObject(this, 0);
        return stub.register(clientStub);
    }

    /**
     * Deregisters the client from update notifications
     *
     * @return deregistration gone? true
     * @throws RemoteException
     */
    public boolean deregister() throws RemoteException {
        return (stub.deregister(clientStub)
                &&
                UnicastRemoteObject.unexportObject(this,false));
    }

    /**
     * Get the temperature data for a day from the server
     *
     * @param date the date of the day
     * @return Arraylist with all 24 temperatures of a day
     * @throws Exception
     */
    @Override
    ArrayList<MeasurePoint> getTemperaturesForADayFromServer(Date date) throws RemoteException {
        return (ArrayList<MeasurePoint>) stub.getTemperatures(date);
    }

    /**
     * Connects to the server
     *
     * @throws Exception
     */
    @Override
    public void connectToServer() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(serverIP, serverPort);
        stub = (WeatherServer) registry.lookup(registryName);
        System.out.println("Connection established...");
    }

    /**
     * Disconnects from the server
     *
     * @throws RemoteException
     */
    @Override
    public void closeConnectionToServer() throws RemoteException {
        deregister();
    }

    /**
     * Update single measurePoint of weatherClient
     *
     * @param measurePoint with updated temperatures
     * @throws RemoteException as required
     */
    @Override
    public void updateTemperature(MeasurePoint measurePoint) throws RemoteException {
        this.dayDate = measurePoint._timeStamp;
        this.measurePointsForOneDay = getTemperaturesForADayFromServer(measurePoint._timeStamp);
        printUpdatedWeatherForADayWithHighlight(measurePoint);
    }

    /**
     * Prints out the whole temperatures of a day
     * with additional statistics (minimum, maximum and average)
     * and highlights the updated temperature
     *
     * @param measurePoint the new temperature for an hour
     */
    private void printUpdatedWeatherForADayWithHighlight(MeasurePoint measurePoint) {
        String newHour = String.format("%02d", measurePoint._timeStamp.getHours());
        System.out.println(toString().replaceFirst(("Hour: " + newHour), ("* Hour: " + newHour)));
        printWeatherStatisticForADay();
    }

    /**
     * Simple CLI menu
     * Possible commands:
     * get yyyy-MM-dd
     * register
     * deregister
     * exit
     *
     * @throws RemoteException if deregister() of client fails
     */
    public void useCLI() throws RemoteException {
        boolean exit = false;
        Date date;
        Scanner scanner = new Scanner(System.in);
        dateFormat.setLenient(false);

        while (!exit) {
            switch (scanner.next()) {
                case "exit":
                    closeConnectionToServer();
                    exit = true;
                    break;

                case "get":
                    try {
                        date = dateFormat.parse(scanner.next());
                    } catch (ParseException e) {
                        System.out.println("Invalid date. Please use \"yyyy-MM-dd\"");
                        break;
                    }
                    try {
                        printWholeWeatherForADay(date);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;

                case "register":
                    try {
                        register();
                        System.out.println("Registered on the server");
                    } catch (RemoteException e) {
                        System.out.println("Already registered");
                    }
                    break;

                case "deregister":
                    deregister();
                    System.out.println("Deregistered from the server");
                    break;

                default:
                    System.out.println("Invalid command ...");
                    break;
            }
        }
    }
}
