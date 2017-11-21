package rmi.server;

import rmi.client.WeatherClient;
import utils.MeasurePoint;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Weather Server interface
 *
 * @author Uli
 * @version 0.1
 */
public interface WeatherServer extends Remote {

    /**
     * Test method ("hello world")
     *
     * @return any message
     * @throws RemoteException as required
     */
    String sayHello() throws RemoteException;

    /**
     * Get temperatures of requested day from weatherServer
     *
     * @param date request date (only day info is used)
     * @return list of measurePoints of requested day
     * @throws RemoteException as required (for example if not exact 24 entries for day are found)
     */
    List<MeasurePoint> getTemperatures(Date date) throws RemoteException;

    /**
     * Add weatherClient to list on Server
     *
     * @param weatherClient which is to be registered (has to be "stub", see: UnicastRemoteObject)
     * @return true if successful
     * @throws RemoteException as required
     */
    boolean register(WeatherClient weatherClient) throws RemoteException;

    /**
     * Delete weatherClient from list on Server
     *
     * @param weatherClient which is to be unregistered (has to be "stub", see: UnicastRemoteObject)
     * @return true if successful
     * @throws RemoteException as required
     */
    boolean deregister(WeatherClient weatherClient) throws RemoteException;

}
