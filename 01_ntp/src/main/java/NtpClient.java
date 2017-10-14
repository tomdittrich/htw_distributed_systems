/**
 * Client to connect to a NTP server and get the time
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 14.10.17
 */

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class NtpClient {

    private String server;

    private NTPUDPClient timeClient;
    private InetAddress inetAddress;
    private TimeInfo timeInfo;
    private Date time;

    /**
     * Default Constructor, with a predefined NTP Server
     */
    public NtpClient() {
        this("0.pool.ntp.org");
    }

    /**
     * Constructor
     *
     * @param server NTP Server
     */
    public NtpClient(String server) {
        this.server = server;
        timeClient = new NTPUDPClient();
        time = new Date();
    }

    /**
     * Get the current time from the server
     *
     * @return the time
     * @throws IOException Any connection Problems?
     */
    public String getTime() throws IOException {
        inetAddress = InetAddress.getByName(server);
        timeInfo = timeClient.getTime(inetAddress);
        time.setTime(timeInfo.getMessage().getTransmitTimeStamp().getTime());

        return time.toString();
    }

    /**
     * Change the NTP server
     *
     * @param server NTP Server
     */
    public void changeServer(String server) {
        this.server = server;
    }

}
