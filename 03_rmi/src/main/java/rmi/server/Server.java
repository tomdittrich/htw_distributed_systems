package rmi.server;


import utils.StoredData;


/**
 * Send requested dates to a client
 *
 * @author Uli
 * @version 0.1
 */
public abstract class Server {

    /**
     * Port of server
     */
    private int port = 5173;

    /**
     * Data store of server
     */
    private StoredData storedData;

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
     * Getter
     *
     * @return data store of Server
     */
    public StoredData getStoredData() {
        return storedData;
    }

    /**
     * Getter
     *
     * @return Port of server
     */
    public int getPort() {
        return port;
    }

    /**
     * Server starts and waits for client requests
     *
     * @throws Exception if problems occur
     */
    public abstract void work() throws Exception;

    /**
     * Shutdown of the server
     *
     * @throws Exception if problems occur
     */
    abstract void shutdownServer() throws Exception;

}
