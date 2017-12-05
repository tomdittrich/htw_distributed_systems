package sockets.server;

import utils.Logging;
import utils.StoredData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Send requested dates to a client
 *
 * @author Uli
 * @version 0.3
 */
public class Server {

    /**
     * Port of server
     */
    private int port = 5173;

    /**
     * Data store of server
     */
    private StoredData storedData;

    /**
     * Socket of Server
     */
    private ServerSocket serverSocket;

    /**
     * Socket of Client
     */
    private Socket clientSocket;

    /**
     * Logging of Server
     */
    Logging Logging = new Logging(); // used as "trigger"

    /**
     * Constructor
     */
    public Server() {
        super();
        this.port = 5173;
    }

    /**
     * Constructor
     *
     * @param port       number of port
     * @param storedData stored data of server
     */
    public Server(int port, StoredData storedData) {
        super();
        this.port = port;
        this.storedData = storedData;
    }

    /**
     * Server starts and waits continuously for 1st client to connect.
     * Process of request and response to client will be done in ServerExecutor-Thread.
     *
     * Do shutdown of server manually for now!
     *
     * @throws IOException from ServerSocket class
     */
    public void work() throws IOException {
        serverSocket = new ServerSocket(port);

        while (true) {
            // wait for first/ next client to come and process it's request in ServerExecutor-Thread:
            clientSocket = serverSocket.accept();
            new Thread(
                    new ServerExecutor(clientSocket, storedData)
            ).start();
            // delete info (which is already processed in another Thread) and start over:
            clientSocket = null;
        }
    }
}
