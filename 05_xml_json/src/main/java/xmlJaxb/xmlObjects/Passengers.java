package xmlJaxb.xmlObjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Passenger class for XML passenger elements
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Passengers {

    @XmlElement(name = "lastName")
    private String lastName;

    @XmlElement(name = "firstName")
    private String firstName;

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "postCode")
    private int postCode;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "country")
    private String country;

    /**
     * Outputs the passenger details for one passenger
     *
     * @return the whole passenger-string
     */
    @Override
    public String toString() {
        return "Name: " + firstName + " "
                + lastName
                + " Adress: " + address + " "
                + postCode + " "
                + city + " "
                + country
                + System.lineSeparator();
    }
}
