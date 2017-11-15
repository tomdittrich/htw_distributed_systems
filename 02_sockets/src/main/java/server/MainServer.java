package server;

import java.io.IOException;

/**
 * Main running app for the weather server
 *
 * @author Uli
 * @version 0.1
 */
public class MainServer {
    public static void main(String[] args) {
        final int SERVER_PORT = 5173;
        final String FILE_LOCATION = "test.csv";

        final int MAX_CLIENT_REQUESTS = 3;

        Server theServer = new Server(SERVER_PORT, new StoredCsvData(FILE_LOCATION));
        int iterator = 1;
        while (iterator <= (MAX_CLIENT_REQUESTS)) {
            System.out.println("Starting Server... (" + iterator + "/" + MAX_CLIENT_REQUESTS + ")");
            try {
                theServer.work();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("...processed client request: " + iterator + "/" + MAX_CLIENT_REQUESTS);
            System.out.println();
            iterator++;
        }
        System.out.println("Server stopped.");
    }
}
