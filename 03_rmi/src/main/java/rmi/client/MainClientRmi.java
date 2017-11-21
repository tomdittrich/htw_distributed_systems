package rmi.client;

/**
 * Main running app for the RMI weather client
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class MainClientRmi {
    public static void main(String[] args) {

        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 10999;

        ClientRmi theClient = new ClientRmi(SERVER_IP, SERVER_PORT);
        try {
            theClient.connectToServer();
            theClient.useCLI();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
