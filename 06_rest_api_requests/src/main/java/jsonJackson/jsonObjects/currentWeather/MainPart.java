package jsonJackson.jsonObjects.currentWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
@JsonRootName("main")
public class MainPart {
    private double temp;
    private int pressure;
    private int humidity;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    @Override
    public String toString() {
        return "Actual Temperature: " + temp
                + " (Temperature between " + tempMax
                + " and " + tempMin + ")"
                + " Humidity: " + humidity
                + " Pressure: " + pressure
                + System.lineSeparator();
    }
}