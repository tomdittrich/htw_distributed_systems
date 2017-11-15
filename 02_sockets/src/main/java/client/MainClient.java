package client;

import java.io.IOException;

/**
 * Main running app for the weather client
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 31.10.17
 */
public class MainClient {
    public static void main(String[] args) {

        final String SERVER_IP = "192.168.1.7";
        final int SERVER_PORT = 5173;

        Client theClient = new Client(SERVER_IP, SERVER_PORT);
        try {
            System.out.printf("Client created\nConnecting to %s:%d\n", SERVER_IP, SERVER_PORT);
            theClient.connectToServer();
            System.out.println("Connection established");
            theClient.printWholeWeatherDataForADate("2017-04-01");
            theClient.closeConnectionToServer();
        } catch (IOException e) {
            System.out.println("Connection problem (Server offline, wrong adress ...)");
        }
    }
}
