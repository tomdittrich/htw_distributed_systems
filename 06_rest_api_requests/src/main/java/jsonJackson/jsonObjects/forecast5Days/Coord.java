package jsonJackson.jsonObjects.forecast5Days;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class Coord {
    private double lat;
    private double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}