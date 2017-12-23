package xmlJaxb.xmlObjects;

import utils.ListEntriesToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Booking class for XML booking elements
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Booking {

    @XmlAttribute(name = "orderPerson")
    private String orderPerson;

    @XmlAttribute(name = "bookingId")
    private String bookingId;

    @XmlElement(name = "passengers")
    private List<Passengers> passengers;

    @XmlElement(name = "flights")
    private List<Flights> flights;

    @XmlElement(name = "price")
    private Price price;

    /**
     * Outputs the whole booking with details
     *
     * @return the whole booking-string
     */
    @Override
    public String toString() {
        ListEntriesToString passengersList = new ListEntriesToString(passengers);
        ListEntriesToString flightsList = new ListEntriesToString(flights);

        return "Order Person: " + orderPerson + " "
                + "BookingId: " + bookingId + System.lineSeparator()
                + passengersList
                + flightsList
                + price
                + System.lineSeparator();
    }
}
