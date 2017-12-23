package jsonJackson.jsonObjects;

/**
 * Passenger class for a single JSON passenger element
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class Passengers {

    private String lastName;

    private String firstName;

    private String address;

    private int postCode;

    private String city;

    private String country;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    /**
     * Outputs the passenger details for one passenger
     *
     * @return the whole passenger-string
     */
    @Override
    public String toString() {
        return "Name: " + firstName + " "
                + lastName
                + " Adress: " + address + " "
                + postCode + " "
                + city + " "
                + country
                + System.lineSeparator();
    }
}
