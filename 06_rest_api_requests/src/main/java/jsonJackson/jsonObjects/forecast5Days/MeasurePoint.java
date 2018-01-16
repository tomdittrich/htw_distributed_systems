package jsonJackson.jsonObjects.forecast5Days;

import com.fasterxml.jackson.annotation.JsonProperty;
import jsonJackson.utils.UnixTimeConverter;

import java.util.List;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class MeasurePoint {
    private int dt;
    @JsonProperty("main")
    private MainPart mainPart;
    private List<WeatherItem> weather;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    @JsonProperty("sys")
    private SysPart sysPart;
    @JsonProperty("dt_txt")
    private String dtTxt;

    public int getDt() {
        return dt;
    }

    public MainPart getMainPart() {
        return mainPart;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public SysPart getSysPart() {
        return sysPart;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    @Override
    public String toString() {
        return "Time: " + UnixTimeConverter.convertUnixTime(getDt(), "GMT+1") +
                System.lineSeparator() + mainPart + System.lineSeparator();
    }
}