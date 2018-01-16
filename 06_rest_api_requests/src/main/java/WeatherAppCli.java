import com.sun.jersey.api.client.ClientResponse;
import httpRestClient.HttpRestClient;
import jsonJackson.JsonJacksonReader;
import jsonJackson.JsonJacksonReaderCurrentWeather;
import jsonJackson.JsonJacksonReaderForecast5Days;

import java.io.IOException;
import java.util.Scanner;

/**
 * CLI for the main weather app
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 */
public class WeatherAppCli {

    private boolean noExit;
    private Scanner scanner;

    /**
     * Default Constructor
     */
    public WeatherAppCli() {
        this.noExit = true;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the cli until exit
     */
    public void useCli() {
        while (cliCurrentForeCastStep()) ;
    }

    /**
     * Asks for current or forecast weather
     * First menu step within the cli.
     *
     * @return true = no exit out of the menu
     */
    private boolean cliCurrentForeCastStep() {

        System.out.println("Weather Data Client: forecast 5 days or current weather? [forecast, current, exit]");
        switch (scanner.next()) {
            case "exit":
                noExit = false;
                break;
            case "current":
                noExit = cliCitiesStep("weather");
                break;
            case "forecast":
                noExit = cliCitiesStep("forecast");
                break;
            default:
                System.out.println("Wrong input, try again please.");
                break;
        }

        return noExit;
    }

    /**
     * Asks for the city.
     * Second menu step within the cli.
     *
     * @param typeOfRequest current or forecast?
     * @return true = no exit out of the menu
     */
    private boolean cliCitiesStep(String typeOfRequest) {

        System.out.println("Choose a city or type the city ID. [Berlin, Muenchen, Bremen, Dresden, FFO, <cityid>]");
        String entryResult = scanner.next();
        switch (entryResult) {
            case "exit":
                noExit = false;
                break;
            case "Berlin":
                cliGetWeatherForCity(typeOfRequest, "2950159");
                break;
            case "Muenchen":
                cliGetWeatherForCity(typeOfRequest, "2867714");
                break;
            case "Bremen":
                cliGetWeatherForCity(typeOfRequest, "2944388");
                break;
            case "Dresden":
                cliGetWeatherForCity(typeOfRequest, "2935022");
                break;
            case "FFO":
                cliGetWeatherForCity(typeOfRequest, "2925535");
                break;
            default:
                cliGetWeatherForCity(typeOfRequest, entryResult);
                break;
        }

        return noExit;
    }

    /**
     * Asks for the weather
     *
     * @param typeOfRequest current or forecast?
     * @param cityId        the city id
     */
    private void cliGetWeatherForCity(String typeOfRequest, String cityId) {
        HttpRestClient client = new HttpRestClient(cityId, typeOfRequest);
        ClientResponse response = client.getResponse();

        if (responseChecker(response)) {
            JsonJacksonReader jsonJacksonReader;
            String output = response.getEntity(String.class);

            try {
                if (typeOfRequest.equalsIgnoreCase("weather")) {
                    jsonJacksonReader = new JsonJacksonReaderCurrentWeather(output, true);
                } else {
                    jsonJacksonReader = new JsonJacksonReaderForecast5Days(output, true);
                }

                System.out.print(jsonJacksonReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the response is ok. If not, it prints out the http error code.
     *
     * @param responseToCheck the respone
     * @return true = response is fine
     */
    private boolean responseChecker(ClientResponse responseToCheck) {
        if (responseToCheck.getStatus() != 200) {
            System.out.println("Failed : HTTP error code : "
                    + responseToCheck.getStatus());
            return false;
        } else {
            return true;
        }
    }
}
