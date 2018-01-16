package jsonJackson.jsonObjects.forecast5Days;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
@JsonRootName("main")
public class MainPart {
    private double temp;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    private double pressure;
    @JsonProperty("sea_level")
    private double seaLevel;
    @JsonProperty("grnd_level")
    private double grndLevel;
    private int humidity;
    @JsonProperty("temp_kf")
    private double tempKf;

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempKf() {
        return tempKf;
    }

    @Override
    public String toString() {
        return "Forecast Temperature: " + temp
                + " (Temperature between " + tempMax
                + " and " + tempMin + ")"
                + " Humidity: " + humidity
                + " Pressure: " + pressure
                + System.lineSeparator();
    }
}