package xmlJaxb.xmlObjects;

import utils.DateAdapter;
import utils.TimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Flight class for XML flights elements
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Flights {

    @XmlAttribute(name = "flightNumber")
    private String flightNumber;

    @XmlElement(name = "departureLocation")
    private String departureLocation;

    @XmlElement(name = "departureDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date departureDate;

    @XmlElement(name = "departureTime")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    private Date departureTime;

    @XmlElement(name = "destinationLocation")
    private String destinationLocation;

    @XmlElement(name = "destinationDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date destinationDate;

    @XmlElement(name = "destinationTime")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    private Date destinationTime;

    @XmlElement(name = "class")
    private String flightClass; // "class" doesn't work

    @XmlElement(name = "isSecureFlight")
    private boolean isSecureFlight;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Outputs the flight details for one flight
     *
     * @return the whole flight-string
     */
    @Override
    public String toString() {
        return "Flight Number: " + flightNumber
                + " Departure Location: " + departureLocation
                + " Departure Date: " + simpleDateFormat.format(departureDate)
                + " Departure Time: " + simpleTimeFormat.format(departureTime)
                + " Destination Location: " + destinationLocation
                + " Destination Date: " + simpleDateFormat.format(destinationDate)
                + " Destination Time: " + simpleTimeFormat.format(destinationTime)
                + " Class: " + flightClass
                + " Secureflight: " + isSecureFlight
                + System.lineSeparator();
    }
}
