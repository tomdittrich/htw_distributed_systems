package sockets.server;

import utils.StoredData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
     * Getter
     *
     * @return data store of Server
     */
    public StoredData getStoredData() {
        return storedData;
    }

    /**
     * Socket of Server
     */
    private ServerSocket serverSocket;

    /**
     * Socket of Client
     */
    private Socket clientSocket;

    /**
     * BufferedReader to read from Client
     */
    private BufferedReader clientReader;

    /**
     * PrintWriter to write to Client
     */
    private PrintWriter clientWriter;

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
     * Server starts and waits for 1st client to connect, processes request and responses to this client.
     * After that follows a shutdown of server.
     *
     * @throws IOException from ServerSocket class
     */
    public void work() throws IOException {
        serverSocket = new ServerSocket(port);

        // wait for first client to come
        clientSocket = serverSocket.accept();

        //get input
        clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = clientReader.readLine();

        // process request input and send output back
        String response = processRequest(request);
        clientWriter = new PrintWriter(clientSocket.getOutputStream());
        clientWriter.println(response);
        clientWriter.flush();

        // close sockets, reader and writer (shutdown of server)
        shutdownServer();
    }

    /**
     * Shutdown of the server
     *
     * @throws IOException from ServerSocket class
     */
    void shutdownServer() throws IOException {
        clientSocket.close();
        serverSocket.close();
        clientWriter.close();
        clientReader.close();
    }

    /**
     * Tries to process request of client
     *
     * @param request is the day
     * @return requested data as response (or ERROR message)
     */
    private String processRequest(String request) {
        if (this.storedData == null)
            return "ERROR: File not found on server. Internal error. Sorry for that.";
        else
            return this.storedData.getData(request);
    }

}
