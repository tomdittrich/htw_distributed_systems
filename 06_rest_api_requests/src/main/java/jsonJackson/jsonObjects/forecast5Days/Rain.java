package jsonJackson.jsonObjects.forecast5Days;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
public class Rain {
    @JsonProperty("3h")
    private double H;

    public double getH() {
        return H;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "H=" + H +
                '}';
    }
}