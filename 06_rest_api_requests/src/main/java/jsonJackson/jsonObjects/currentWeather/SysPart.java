package jsonJackson.jsonObjects.currentWeather;


import com.fasterxml.jackson.annotation.JsonRootName;
import jsonJackson.utils.UnixTimeConverter;

/**
 * Manually added model
 *
 * @author uli
 * @version 0.1
 */
@JsonRootName("sys")
public class SysPart {
    private int type;
    private int id;
    private double message;
    private String country;
    private long sunrise;
    private long sunset;

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    @Override
    public String toString() {
        return "Country: " + country
                + " Sunrise: " + UnixTimeConverter.convertUnixTime(getSunrise(), "GMT+1")
                + " Sunset: " + UnixTimeConverter.convertUnixTime(getSunset(), "GMT+1")
                + System.lineSeparator();
    }

}
