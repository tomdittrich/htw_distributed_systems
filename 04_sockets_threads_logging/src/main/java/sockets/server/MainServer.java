package sockets.server;

import utils.StoredCsvData;

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
        Server theServer = new Server(SERVER_PORT, new StoredCsvData(FILE_LOCATION));

        System.out.println("Starting Server... ");
        try {
            theServer.work();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
