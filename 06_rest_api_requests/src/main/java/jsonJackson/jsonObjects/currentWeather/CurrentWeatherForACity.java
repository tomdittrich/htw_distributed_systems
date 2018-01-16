package jsonJackson.jsonObjects.currentWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import jsonJackson.utils.UnixTimeConverter;

import java.util.List;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class CurrentWeatherForACity {
    private Coord coord;
    private List<WeatherItem> weather;
    private String base;
    @JsonProperty("main")
    private MainPart mainPart;
    private int visibility;
    private Wind wind;
    @JsonProperty("sys")
    private SysPart sysPart;
    private Clouds clouds;
    private long dt;
    private long id;
    private String name;
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public MainPart getMainPart() {
        return mainPart;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public long getDt() {
        return dt;
    }

    public SysPart getSysPart() {
        return sysPart;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    @Override
    public String toString() {

        return "CURRENT - " +
                name + System.lineSeparator() +
                "Time: " + UnixTimeConverter.convertUnixTime(getDt(), "GMT+1") + System.lineSeparator()
                + mainPart.toString()
                + System.lineSeparator();
    }
}