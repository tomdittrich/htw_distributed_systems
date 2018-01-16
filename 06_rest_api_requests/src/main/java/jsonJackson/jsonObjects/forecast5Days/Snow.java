package jsonJackson.jsonObjects.forecast5Days;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Manually added model
 *
 * @author uli
 * @version 0.1
 */
public class Snow {
    @JsonProperty("3h")
    private double H;

    public double getH() {
        return H;
    }

    @Override
    public String toString() {
        return "Snow{" +
                "H=" + H +
                '}';
    }
}