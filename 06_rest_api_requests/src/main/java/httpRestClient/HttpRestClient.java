package httpRestClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @author uli
 * @version 0.2
 */
public class HttpRestClient {

    // YOUR API Key here, register for free on openweathermap.org
    private final String APIKEY = "yourApiKeyHere";
    private final String UNITS = "metric";
    private Client client;
    private WebResource webResource;

    private String cityId;
    private String typeOfRequest;

    private ClientResponse response;

    public ClientResponse getResponse() {
        return response;
    }

    /**
     * @param cityId        ID of requested city
     * @param typeOfRequest Current Weather or Forecast?
     */
    public HttpRestClient(String cityId, String typeOfRequest) {
        this.cityId = cityId;
        this.typeOfRequest = typeOfRequest;
        client = Client.create();
        webResource = client.resource(
                "http://api.openweathermap.org/data/2.5/" + typeOfRequest + "?id="
                        + cityId + "&units=" + UNITS + "&appid=" + APIKEY);
        response = webResource.accept("application/json") // "application/json" seems to be needless
                .get(ClientResponse.class);
    }
}
