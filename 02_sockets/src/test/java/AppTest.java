import client.Client;
import server.Server;
import server.StoredCsvData;

import java.io.IOException;

/**
 * App for simple and unconventional testing
 *
 * @author Uli & Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 31.10.17
 */
public class AppTest {

    public static void main(String[] args) {

        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 5173;
        final String FILE_LOCATION = "test.csv";

        Thread server = new Thread(() -> {
            Server theServer = new Server(SERVER_PORT, new StoredCsvData(FILE_LOCATION));
            System.out.println("Server created");
            try {
                theServer.work();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread clientCorrectRequest = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Client theClient = new Client("127.0.0.1", SERVER_PORT);
            try {
                System.out.printf("Client created\nConnecting to %s:%d\n", SERVER_IP, SERVER_PORT);
                theClient.connectToServer();
                System.out.println("Connection established");
                theClient.printWholeWeatherDataForADate("2017-04-01");
                theClient.closeConnectionToServer();
            } catch (IOException e) {
                System.out.println("Connection problem (server offline, wrong adress ...)");
            }
        });

        Thread clientBadRequest = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Client theClient = new Client("127.0.0.1", SERVER_PORT);
            try {
                System.out.printf("Client created\nConnecting to %s:%d\n", SERVER_IP, SERVER_PORT);
                theClient.connectToServer();
                System.out.println("Connection established");
                theClient.printWholeWeatherDataForADate("2007-04-01");
                theClient.closeConnectionToServer();
            } catch (IOException e) {
                System.out.println("Connection problem (server offline, wrong adress ...)");
            }
        });

        server.start();
        clientCorrectRequest.start();
        //clientBadRequest.start();
    }
}
