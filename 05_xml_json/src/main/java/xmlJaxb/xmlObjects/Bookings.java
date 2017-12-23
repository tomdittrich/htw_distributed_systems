package xmlJaxb.xmlObjects;

import utils.ListEntriesToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Bookings class for XML bookings elements (root element)
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
@XmlRootElement
public class Bookings {

    @XmlElement(name = "booking")
    private List<Booking> booking;

    /**
     * Outputs the whole bookings with details
     *
     * @return the whole bookings-string
     */
    @Override
    public String toString() {
        ListEntriesToString bookingList = new ListEntriesToString(booking);

        return bookingList.toString();
    }
}
