package rmi.server;

import utils.StoredCsvData;


/**
 * Main running app for the RMI weather server
 *
 * @author Uli
 * @version 0.2
 */
public class MainServerRmi {
    /**
     * @param args not used
     */
    public static void main(String args[]) {

        final int SERVER_PORT = 10999;
        final String FILE_LOCATION = "test.csv";
        final String REGISTRY_NAME = "Hello WEATHER";

        ServerRmi theServer = new ServerRmi(SERVER_PORT, new StoredCsvData(FILE_LOCATION), REGISTRY_NAME);
        try {
            theServer.work();
            theServer.useCLI();
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
