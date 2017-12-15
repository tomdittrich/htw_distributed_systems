import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.util.Iterator;

/**
 * XML reader for Ulis booking xml
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class ReaderXmlJdom {

    private SAXBuilder builder;
    private Document doc;
    private XMLOutputter outputter;
    private Element rootElement;

    /**
     * Default Constructor, reading Ulis XML
     *
     * @throws JDOMException
     * @throws IOException
     */
    public ReaderXmlJdom() throws JDOMException, IOException {
        this("src/main/java/uli/uli.xml");
    }

    /**
     * Constructor
     *
     * @param xmlFilePath Path to Ulis XML
     * @throws JDOMException
     * @throws IOException
     */
    public ReaderXmlJdom(String xmlFilePath) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build(xmlFilePath);
        rootElement = doc.getRootElement();
    }

    /**
     * Prints out the whole bookings with details
     */
    public void printAllBookings() {
        System.out.println(getAllBookingsText());
    }

    /**
     * Get out the whole bookings with detauls
     *
     * @return the whole bookings text
     */
    private String getAllBookingsText() {

        String result = "";
        Iterator<?> bookingList = rootElement.getChildren("booking").iterator();

        while (bookingList.hasNext()) {
            Element booking = (Element) bookingList.next();

            result += getBookingText(booking);
            result += getPassengersText(booking);
            result += getFlightsText(booking);
            result += getPriceText(booking);
            result += System.lineSeparator() + System.lineSeparator();
        }

        return result;
    }

    /**
     * Get out the attributes text for a booking
     *
     * @param booking which booking?
     * @return
     */
    private String getBookingText(Element booking) {

        String result = "";

        result += "Booking Id: " + booking.getAttributeValue("bookingId");
        result += " Order Person: " + booking.getAttributeValue("orderPerson") + System.lineSeparator();

        return result;
    }

    /**
     * Get out the text for all passengers
     *
     * @param booking which booking?
     * @return
     */
    private String getPassengersText(Element booking) {

        String result = "";
        Iterator<?> passengerList = booking.getChildren("passengers").iterator();

        while (passengerList.hasNext()) {
            Element passenger = (Element) passengerList.next();
            result += "Name: " + passenger.getChildText("firstName")
                    + " "
                    + passenger.getChildText("lastName");

            result += " Adress: " + passenger.getChildText("address") + " "
                    + passenger.getChildText("postCode") + " "
                    + passenger.getChildText("city") + " "
                    + passenger.getChildText("country");

            result += System.lineSeparator();
        }

        return result;
    }

    /**
     * Get out the text for all flights
     *
     * @param booking which booking?
     * @return
     */
    private String getFlightsText(Element booking) {

        String result = "";
        Iterator<?> flightList = booking.getChildren("flights").iterator();

        while (flightList.hasNext()) {
            Element flight = (Element) flightList.next();
            result += "Flight Number: " + flight.getAttribute("flightNumber").getValue();
            result += " Departure Location: " + flight.getChildText("departureLocation");
            result += " Departure Date: " + flight.getChildText("departureDate");
            result += " Departure Time: " + flight.getChildText("departureTime");

            result += " Destination Location: " + flight.getChildText("destinationLocation");
            result += " Destination Date: " + flight.getChildText("departureDate");
            result += " Destination Time: " + flight.getChildText("departureTime");

            result += " Class: " + flight.getChildText("class");
            result += " Secureflight: " + flight.getChildText("isSecureFlight");

            result += System.lineSeparator();
        }

        return result;
    }

    /**
     * Get out the price text
     *
     * @param booking which booking?
     * @return
     */
    private String getPriceText(Element booking) {

        return "Price: " + booking.getChild("price").getChildText("amount") + " "
                + booking.getChild("price").getChildText("currency");
    }

    /**
     * Get out the whole bookings with details
     *
     * @return the whole text for every booking
     */
    @Override
    public String toString() {
        return getAllBookingsText();
    }
}
