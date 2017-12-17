import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Main app for json reading (flight bookings)
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 */
public class ReaderJsonApp {
    public static void main(String[] args) {
        try{
            ReaderJson jsonReader = new ReaderJson();
            System.out.println(jsonReader);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
