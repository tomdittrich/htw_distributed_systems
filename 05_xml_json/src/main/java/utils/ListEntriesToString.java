package utils;

import java.util.List;

/**
 * Iterates a list of entries and returns the toString-method
 * for every single element within the list
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 */
public class ListEntriesToString<T> {

    private List<T> list;

    public ListEntriesToString(List<T> list) {
        this.list = list;
    }

    /**
     * Calling every toString() for every element and appending it
     * to a whole one string
     *
     * @return the combined string
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        for (T entryInList : list) {
            resultString.append(entryInList);
        }
        return resultString.toString();
    }
}
