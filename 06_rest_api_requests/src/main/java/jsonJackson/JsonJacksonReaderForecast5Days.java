package jsonJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jsonJackson.jsonObjects.forecast5Days.Forecast5Days;

import java.io.IOException;

/**
 * JSON jackson reader for OpenWeather Data: Forecast 5 days
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @author uli
 * @version 0.9
 */
public class JsonJacksonReaderForecast5Days implements JsonJacksonReader {

    private String jsonInString;
    private ObjectMapper objectMapper;
    private Forecast5Days forecast5Days;

    /**
     * Default constructor
     *
     * @throws IOException if something went wrong
     */
    public JsonJacksonReaderForecast5Days() throws IOException {
        this("", true);

    }

    /**
     * Default constructor
     *
     * @param jsonInString            JSON data String
     * @param failOnUnknownProperties failing when unknown properties? set true
     * @throws IOException if something went wrong
     */
    public JsonJacksonReaderForecast5Days(String jsonInString, boolean failOnUnknownProperties) throws IOException {
        this.jsonInString = jsonInString;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
        try {
            forecast5Days = objectMapper.readValue(jsonInString, Forecast5Days.class);
        } catch (UnrecognizedPropertyException e) {
            //simulation of logging
            System.out.println("(WARNING: ignore of Unrecognized Property in json file. '"
                    + e.getPropertyName() + "' and maybe others will be ignored.)" + System.lineSeparator());

            //force ignoring of unknown/ unrecognized properties
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //convert json string to object again
            forecast5Days = objectMapper.readValue(jsonInString, Forecast5Days.class);
        }
    }

    /**
     * Returns the whole foreCast5Days data with details
     *
     * @return the whole foreCast5Days-string
     */
    @Override
    public String toString() {
        return forecast5Days.toString();
    }
}
