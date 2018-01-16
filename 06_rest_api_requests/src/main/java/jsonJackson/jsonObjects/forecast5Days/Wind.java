package jsonJackson.jsonObjects.forecast5Days;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class Wind {
    private double speed;
    private double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}