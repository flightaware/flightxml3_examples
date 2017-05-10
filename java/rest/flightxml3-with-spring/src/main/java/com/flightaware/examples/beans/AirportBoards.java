package com.flightaware.examples.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportBoards {

    private String preferredOrder;
    private String airport;
    private Airport airportInfo;
    private TrackAirport arrivals;
    private TrackAirport departures;
    private TrackAirport enroute;
    private TrackAirport scheduled;
}
