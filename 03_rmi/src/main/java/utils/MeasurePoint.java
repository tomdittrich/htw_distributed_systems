package utils;

import java.io.Serializable;
import java.util.Date;

/**
 * MeasurePoint class
 *
 * @author Ulrich Overdieck s0556056@htw-berlin.de
 * @version 0.1
 */
public class MeasurePoint implements Serializable {

    /**
     * Measurement date
     */
    public Date _timeStamp;

    /**
     * Temperature entry
     */
    public float _temperature;

    /**
     * Constructor
     *
     * @param timeStamp   date of measurement
     * @param temperature temperature at date
     */
    public MeasurePoint(Date timeStamp, float temperature) {
        super();
        this._timeStamp = timeStamp;
        this._temperature = temperature;
    }

    @Override
    public String toString() {
        return "MeasurePoint{" +
                "_timeStamp=" + _timeStamp +
                ", _temperature=" + _temperature +
                '}';
    }
}
