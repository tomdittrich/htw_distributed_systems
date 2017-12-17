import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Json reader for Ulis booking json
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 */
public class ReaderJson {

    JSONParser parser;
    JSONObject jsonRootObject;


    public ReaderJson() throws IOException, ParseException {
        this("src/main/java/uli/uli.json");
    }

    public ReaderJson(String jsonFilePath) throws IOException, ParseException {
        parser = new JSONParser();
        jsonRootObject = (JSONObject) parser.parse(new FileReader(jsonFilePath));

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
        JSONArray bookings = (JSONArray) jsonRootObject.get("bookings");

        for (Object currentArrayObject : bookings) {
            JSONObject booking = (JSONObject) currentArrayObject;
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
    private String getBookingText(JSONObject booking) {
        String result = "";

        result += "Booking Id: " + booking.get("-bookingId");
        result += " Order Person: " + booking.get("-orderPerson") + System.lineSeparator();

        return result;
    }

    /**
     * Get out the text for all passengers
     *
     * @param booking which booking?
     * @return
     */
    private String getPassengersText(JSONObject booking) {

        String result = "";
        JSONArray passengerList = (JSONArray) booking.get("passengers");

        for (Object currentArrayObhect : passengerList) {
            JSONObject passenger = (JSONObject) currentArrayObhect;

            result += "Name: " + passenger.get("firstName")
                    + " "
                    + passenger.get("lastName");

            result += " Adress: " + passenger.get("address") + " "
                    + passenger.get("postCode") + " "
                    + passenger.get("city") + " "
                    + passenger.get("country");

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
    private String getFlightsText(JSONObject booking) {

        String result = "";
        JSONArray flightList = (JSONArray) booking.get("flights");

        for (Object currentArrayObhect : flightList) {
            JSONObject flight = (JSONObject) currentArrayObhect;

            result += "Flight Number: " + flight.get("-flightNumber");
            result += " Departure Location: " + flight.get("departureLocation");
            result += " Departure Date: " + flight.get("departureDate");
            result += " Departure Time: " + flight.get("departureTime");

            result += " Destination Location: " + flight.get("destinationLocation");
            result += " Destination Date: " + flight.get("departureDate");
            result += " Destination Time: " + flight.get("departureTime");

            result += " Class: " + flight.get("class");
            result += " Secureflight: " + flight.get("isSecureFlight");

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
    private String getPriceText(JSONObject booking) {

        String result = "";
        JSONArray price = (JSONArray) booking.get("price");

        // can't find a way to cast direct in a price object
        for (Object currentArrayObhect : price) {
            JSONObject actualPrice = (JSONObject) currentArrayObhect;

            result += "Price: " + actualPrice.get("amount") + " "
                    + actualPrice.get("currency");
        }

        return result;
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
