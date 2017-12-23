package jsonJackson.jsonObjects;

/**
 * Price class for a single JSON price element
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Price {

    private double amount;

    private String currency;

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * Outputs the price details for one price
     *
     * @return the whole price-string
     */
    @Override
    public String toString() {
        return "Price: " + amount + " "
                + currency
                + System.lineSeparator();
    }
}
