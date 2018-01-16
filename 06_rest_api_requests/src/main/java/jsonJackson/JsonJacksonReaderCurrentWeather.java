package jsonJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jsonJackson.jsonObjects.currentWeather.CurrentWeatherForACity;

import java.io.IOException;

/**
 * JSON jackson reader for OpenWeather Data: Current Weather
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @author uli
 * @version 0.9
 */
public class JsonJacksonReaderCurrentWeather implements JsonJacksonReader {

    private String jsonInString;
    private ObjectMapper objectMapper;
    private CurrentWeatherForACity currentWeather;

    /**
     * Default constructor
     *
     * @throws IOException if something went wrong
     */
    public JsonJacksonReaderCurrentWeather() throws IOException {
        this("", true);

    }

    /**
     * Default constructor
     *
     * @param jsonInString            JSON data String
     * @param failOnUnknownProperties failing when unknown properties? set true
     * @throws IOException if something went wrong
     */
    public JsonJacksonReaderCurrentWeather(String jsonInString, boolean failOnUnknownProperties) throws IOException {
        this.jsonInString = jsonInString;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
        try {
            currentWeather = objectMapper.readValue(jsonInString, CurrentWeatherForACity.class);
        } catch (UnrecognizedPropertyException e) {
            //simulation of logging
            System.out.println("(WARNING: ignore of Unrecognized Property in json file. '"
                    + e.getPropertyName() + "' and maybe others will be ignored.)" + System.lineSeparator());

            //force ignoring of unknown/ unrecognized properties
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //convert json string to object again
            currentWeather = objectMapper.readValue(jsonInString, CurrentWeatherForACity.class);
        }
    }

    /**
     * Returns the whole current weather with details
     *
     * @return the whole current weather-string
     */
    @Override
    public String toString() {
        return currentWeather.toString();
    }
}
