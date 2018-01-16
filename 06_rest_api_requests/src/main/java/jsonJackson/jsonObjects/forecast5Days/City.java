package jsonJackson.jsonObjects.forecast5Days;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class City {
    private int id;
    private String name;
    private Coord coord;
    private String country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name;
    }
}