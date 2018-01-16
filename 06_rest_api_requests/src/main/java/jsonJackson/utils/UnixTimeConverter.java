package jsonJackson.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Converts unix 1970 time to a readable time
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 */
public final class UnixTimeConverter {

    /**
     * Converts unix 1970 time to a readable time.
     *
     * @param unixTime unix time without milliseconds
     * @param timeZone timezone, e.g. "GMT+1"
     * @return date in "yyyy-MM-dd HH:mm:ss timezone"
     */
    public static String convertUnixTime(long unixTime, String timeZone) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormatter.setTimeZone(TimeZone.getTimeZone(timeZone));

        return dateFormatter.format(date);
    }
}
