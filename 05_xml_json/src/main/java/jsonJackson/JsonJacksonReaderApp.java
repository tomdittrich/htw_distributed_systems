package jsonJackson;

import java.io.IOException;

/**
 * Main app for JSON reading (flight bookings)
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class JsonJacksonReaderApp {
    public static void main(String[] args) {

        try {
            JsonJacksonReader reader = new JsonJacksonReader();
            System.out.println(reader.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
