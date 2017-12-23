package xmlJaxb;

import xmlJaxb.xmlObjects.Bookings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Main app for XML reading (flight bookings)
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */

// inspired by : https://www.javatpoint.com/jaxb-tutorial
public class XmlJaxbReader {
    public static void main(String[] args) {

        try {

            File file = new File("src/main/java/uli/dataexchange/uli.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Bookings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Bookings bookings = (Bookings) jaxbUnmarshaller.unmarshal(file);
            System.out.println(bookings);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
