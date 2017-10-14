import java.io.IOException;

/**
 * App for timeserver/NTP excercise
 * Prints out the current time from a specific NTP server
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 14.10.17
 */
public class App {
    public static void main(String[] args) {
        NtpClient client = new NtpClient();

        try {
            System.out.println(client.getTime());
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
