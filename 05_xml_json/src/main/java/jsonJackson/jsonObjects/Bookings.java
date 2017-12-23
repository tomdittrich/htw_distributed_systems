package jsonJackson.jsonObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import utils.ListEntriesToString;

import java.util.List;

/**
 * Booking class for a single JSON booking element
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Bookings {

    @JsonProperty("-orderPerson")
    private String orderPerson;

    @JsonProperty("-bookingId")
    private String bookingId;

    private List<Flights> flights;

    private List<Passengers> passengers;

    private List<Price> price;

    public String getOrderPerson() {
        return orderPerson;
    }

    public String getBookingId() {
        return bookingId;
    }

    public List<Flights> getFlights() {
        return flights;
    }

    public List<Passengers> getPassengers() {
        return passengers;
    }

    public List<Price> getPrice() {
        return price;
    }

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
                + price.get(0) // cause there is only one price in the json
                + System.lineSeparator();
    }
}
