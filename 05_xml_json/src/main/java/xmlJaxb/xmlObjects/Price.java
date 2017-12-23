package xmlJaxb.xmlObjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Price class for XML price elements
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Price {

    @XmlElement(name = "amount")
    private double amount;

    @XmlElement(name = "currency")
    private String currency;

    /**
     * Outputs the price and amount
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
