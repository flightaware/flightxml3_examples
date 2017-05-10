package com.flightaware.examples.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
@AllArgsConstructor
public class AirportDisplay {

    private String code;
    private String city;
    private String alternateIdent;
    private String airportName;

}
