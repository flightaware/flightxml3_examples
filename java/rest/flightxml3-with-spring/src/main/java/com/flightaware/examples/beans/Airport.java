package com.flightaware.examples.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
@AllArgsConstructor
public class Airport {

    private String airportCode;
    private String name;
    private float elevation;
    private String city;
    private String state;
    private float longitude;
    private float latitude;
    private String timezone;
    private String countryCode;
    private String wikiUrl;

}
