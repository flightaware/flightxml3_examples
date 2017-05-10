package com.flightaware.flightxml.examples;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by jonathan.cone on 5/10/17.
 */
public class JaxRsExample {

    public static void main(String[] args) {
        // Setup basic preemptive authentication feature
        HttpAuthenticationFeature basicAuthFeature = HttpAuthenticationFeature.basic("yourUserName", "yourApiKey");

        // Create the client and make the FlightXML3 request to the FindFlight method
        WebTarget findFlightWebTarget = ClientBuilder.newClient().register(basicAuthFeature).target(
                UriBuilder.fromUri("https://flightxml.flightaware.com/json/FlightXML3/FindFlight")
                        .queryParam("origin", "KIAH")
                        .queryParam("destination", "KJFK")
                        .queryParam("include_ex_data", true)
                        .build()
        );

        try {
            // Make the call to FlightXML
            Response response = findFlightWebTarget.request().get();

            // Print out the response code
            System.out.println(String.format("Response code: %d", response.getStatus()));

            // For this trivial example we are just printing out the JSON response to stdOut, but you could use a parser
            // like Jackson to decode the JSON response into a DTO.
            System.out.println(response.readEntity(String.class));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
