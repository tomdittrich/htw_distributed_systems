package jsonJackson.jsonObjects;

import utils.ListEntriesToString;

import java.util.List;

/**
 * Booking master-class for all JSON booking elements
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Booking {

    private List<Bookings> bookings;

    public List<Bookings> getBookings() {
        return bookings;
    }

    /**
     * Returns the whole bookings with details
     *
     * @return the whole bookings-string
     */
    @Override
    public String toString() {

        ListEntriesToString bookingList = new ListEntriesToString(bookings);

        return bookingList.toString();
    }
}

