import java.io.*;
import java.util.Iterator;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 15.12.17
 */
public class ReaderXmlJdom {

    private SAXBuilder builder;
    private Document doc;
    private XMLOutputter outputter;
    private Element rootElement;
    private Iterator<?> bookingList;

    public ReaderXmlJdom() throws JDOMException, IOException {
        this("src/main/java/data.xml");
    }

    public ReaderXmlJdom(String xmlFilePath) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build(xmlFilePath);
        rootElement = doc.getRootElement();
        bookingList = rootElement.getChildren("booking").iterator();
    }

    // TODO: separate methods
    public void work()
    {
        String result = "";
        while(bookingList.hasNext()){
            Element booking = (Element) bookingList.next();
            result += "Booking Id: " + booking.getAttributeValue("bookingId");
            result += " Order Person: " + booking.getAttributeValue("orderPerson") + System.lineSeparator();

            Iterator<?> passengerList = booking.getChildren("passengers").iterator();

            while(passengerList.hasNext()){
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

            Iterator<?> flightList = booking.getChildren("flights").iterator();

            while(flightList.hasNext()){
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

            result += "Price: " + booking.getChild("price").getChildText("amount") + " "
                    + booking.getChild("price").getChildText("currency");

            result += System.lineSeparator() + System.lineSeparator();
        }

        System.out.println(result);
    }
}
