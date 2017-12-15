import org.jdom2.JDOMException;

import java.io.IOException;

/**
 * Main app for XML reading (flight bookings)
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class ReaderXmlJdomApp {
    public static void main(String[] args) {

        try {
            ReaderXmlJdom xmlReader = new ReaderXmlJdom();
            System.out.println(xmlReader);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
