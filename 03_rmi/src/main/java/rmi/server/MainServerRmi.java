package rmi.server;

import utils.StoredCsvData;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Main running app for the RMI weather server
 *
 * @author Uli
 * @version 0.1
 */
public class MainServerRmi {
    /**
     * @param args not used
     */
    public static void main(String args[]) {

        final int SERVER_PORT = 10999;
        final String FILE_LOCATION = "test.csv";

        ServerImpl theServer = new ServerImpl(SERVER_PORT, new StoredCsvData(FILE_LOCATION));

        try {
            WeatherServer stub = (WeatherServer) UnicastRemoteObject.exportObject(theServer, 0);

            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            // Bind the remote object's stub in the registry
            registry.bind("Hello WEATHER", stub);

            System.out.println("Server ready");
            theServer.useCLI();
            UnicastRemoteObject.unexportObject(theServer,false);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
