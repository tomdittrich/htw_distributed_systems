package jsonJackson.jsonObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Flight class for a single JSON flight element
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Flights {

    @JsonProperty("-flightNumber")
    private String flightNumber;

    private String departureLocation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private Date departureTime;

    private String destinationLocation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date destinationDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private Date destinationTime;

    @JsonProperty("class")
    private String flightClass; // "class" doesn't work

    @JsonProperty("isSecureFlight")
    private boolean secureFlight;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public Date getDestinationDate() {
        return destinationDate;
    }

    public Date getDestinationTime() {
        return destinationTime;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public boolean isSecureFlight() {
        return secureFlight;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public SimpleDateFormat getSimpleTimeFormat() {
        return simpleTimeFormat;
    }

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
                + " Secureflight: " + secureFlight
                + System.lineSeparator();
    }
}
