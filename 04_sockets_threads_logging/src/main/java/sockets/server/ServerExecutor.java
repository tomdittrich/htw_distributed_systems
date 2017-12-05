package sockets.server;

import utils.Logging;
import utils.StoredData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Executes requests of Server
 *
 * @author Uli
 * @version 0.2
 */
public class ServerExecutor implements Runnable {

    /**
     * Socket of Client
     */
    private Socket clientSocket;

    /**
     * Data store of server
     */
    private StoredData storedData;

    /**
     * Constructor
     *
     * @param storedData   stored data
     * @param clientSocket Socket of Client
     */
    public ServerExecutor(Socket clientSocket, StoredData storedData) {
        this.clientSocket = clientSocket;
        this.storedData = storedData;
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

    @Override
    public void run() {
        try {
            String request;
            String response;

            // log start of thread
            Logging.getWeatherLogger().info("Thread STARTED: " + Thread.currentThread().getId()
                    + System.lineSeparator() + "IP of Client: " + clientSocket.getInetAddress()
                    + System.lineSeparator() + "Port of Client: " + clientSocket.getPort()
                    + System.lineSeparator()
            );

            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream());

            while (true) {
                // get input
                request = clientReader.readLine();

                // client typing exit or shutdown in another way?
                if (request == null || request.equals("exit")) break;

                // wait a random amount of time
                // just for testing if the logging and threading is right
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // process request input and send output back
                response = processRequest(request);
                clientWriter.println(response);

                // flush
                clientWriter.flush();
            }

            // close reader and writer
            clientWriter.close();
            clientReader.close();

            // log finish of thread
            Logging.getWeatherLogger().info("Thread FINISHED: " + Thread.currentThread().getId()
                    + System.lineSeparator() + "IP of Client: " + clientSocket.getInetAddress()
                    + System.lineSeparator() + "Port of Client: " + clientSocket.getPort()
                    + System.lineSeparator()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
