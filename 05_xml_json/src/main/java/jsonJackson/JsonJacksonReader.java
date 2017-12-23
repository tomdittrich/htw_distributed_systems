package jsonJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsonJackson.jsonObjects.Booking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JSON jackson reader for flight bookings
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class JsonJacksonReader {

    private byte[] jsonData;
    private ObjectMapper objectMapper;
    private Booking booking;

    /**
     * Default constructor, reading Ulis JSON
     *
     * @throws IOException
     */
    public JsonJacksonReader() throws IOException {
        this("src/main/java/uli/dataexchange/uli.json", true);

    }

    /**
     * Default constructor
     *
     * @param jsonFilePath Path to JSON
     * @param failOnUnknownProperties failing when unknown properties? set true
     * @throws IOException
     */
    public JsonJacksonReader(String jsonFilePath, boolean failOnUnknownProperties) throws IOException {
        jsonData = Files.readAllBytes(Paths.get(jsonFilePath));
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
        booking = objectMapper.readValue(jsonData, Booking.class);
    }

    /**
     * Returns the whole bookings with details
     *
     * @return the whole bookings-string
     */
    @Override
    public String toString() {
        return booking.toString();
    }
}
