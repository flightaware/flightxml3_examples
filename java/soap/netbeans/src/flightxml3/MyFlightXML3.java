/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightxml3;

import com.flightaware.flightxml.soap.flightxml3.AirportBoardsRequest;
import com.flightaware.flightxml.soap.flightxml3.AirportBoardsStruct;
import com.flightaware.flightxml.soap.flightxml3.FlightXML3;
import com.flightaware.flightxml.soap.flightxml3.FlightXML3Soap;
import com.flightaware.flightxml.soap.flightxml3.WeatherConditionsArrayStruct;
import com.flightaware.flightxml.soap.flightxml3.WeatherConditionsRequest;
import com.flightaware.flightxml.soap.flightxml3.WeatherConditionsStruct;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author jonathan.cone
 */
public class MyFlightXML3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String username = "YOUR_USERNAME";
        String apiKey = "YOUR_API_KEY";
        
        // The java.net.Authenticator class is used to enable authentication and provide access to a store of usernames and passwords
        // which are then used in the respective authentication schemes. 
        //  When authentication is required, the system will invoke one of the requestPasswordAuthentication() methods
        //  which in turn will call the getPasswordAuthentication() method of the registered object. 
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, apiKey.toCharArray());
            }
        });
        
        // Connect and authenticate
        FlightXML3 locator = new FlightXML3();
        
        /*
        * if you don't have wsdl already loaded:
        * String WSDL_URL = "https://flightxml.flightaware.com/soap/FlightXML3/wsdl";
        * URL wsdl_url = null;
        * try {
        *   wsdl_url = new URL(WSDL_URL);
        * } catch (MalformedURLException ex) {
        *   Logger.getLogger(MyFlightXML.class.getName()).log(Level.SEVERE, null, ex);
        * }
        * FlightXML3 locator = new FlightXML3(wsdl_url);
        */
        
        // get the stub that provides the methods
        FlightXML3Soap df = locator.getFlightXML3Soap();
        
        ///////////////////////////////////////////////////////////////////////////
        // Get the list of enroute aircraft
        ///////////////////////////////////////////////////////////////////////////
        System.out.println("\n-------------- PRINT ENROUTE AIRCRAFT TO KIAH --------------\n");
        
        // set request parameters
        AirportBoardsRequest boardsRequest = new AirportBoardsRequest();
        boardsRequest.setAirportCode("KIAH");
        boardsRequest.setIncludeExData(false);
        boardsRequest.setType("enroute");
        boardsRequest.setHowMany(10);
        
        try {
            // Make the request
            AirportBoardsStruct struct = df.airportBoards(boardsRequest).getAirportBoardsResult();

            // Iterate over the flights and print out some information about each one
            struct.getEnroute().getFlights().forEach(f -> {
                System.out.println(String.format("%s (%s)\t%s (%s)", f.getIdent(), f.getAircrafttype(), f.getOrigin().getAirportName(), f.getOrigin().getCode()));
            });
        } catch (Exception e) {
            System.err.println(e);
        }
        
        ///////////////////////////////////////////////////////////////////////////
        // Get the weather for KIAH
        ///////////////////////////////////////////////////////////////////////////
        System.out.println("\n-------------- PRINT CURRENT WEATHER FOR KIAH --------------\n");
        WeatherConditionsRequest weatherRequest = new WeatherConditionsRequest();
        weatherRequest.setAirportCode("KIAH");
        weatherRequest.setTemperatureUnits("F");
        weatherRequest.setHowMany(5);
        
        try {
            // Make the request
            WeatherConditionsArrayStruct struct = df.weatherConditions(weatherRequest).getWeatherConditionsResult();
            // Retrieve and print out the first (latest) weather info
            WeatherConditionsStruct weather = struct.getConditions().get(0);
            System.out.println(String.format("Tempearture: %d°F, Dewpoint: %d°F, Humidity: %d%%", weather.getTempAir(), weather.getTempDewpoint(), weather.getTempRelhum()));
            System.out.println(String.format("Winds: %d at %d kts", weather.getWindDirection(), weather.getWindSpeed()));
            System.out.println(String.format("Clouds: %d ft", weather.getCloudAltitude()));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
