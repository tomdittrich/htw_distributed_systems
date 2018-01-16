package jsonJackson.jsonObjects.forecast5Days;

import jsonJackson.utils.ListEntriesToString;

import java.util.List;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class Forecast5Days {
    private String cod;
    private double message;
    private int cnt;
    private List<MeasurePoint> list;
    private City city;

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<MeasurePoint> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {

        ListEntriesToString measurePointList = new ListEntriesToString(list);

        return "FORECAST FOR 5 DAYS (every 3h) - " + city + System.lineSeparator() +
                measurePointList.toString();
    }
}