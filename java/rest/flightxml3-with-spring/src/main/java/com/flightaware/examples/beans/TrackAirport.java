package com.flightaware.examples.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
public class TrackAirport {

    @JsonProperty("num_flights")
    private int numFlights;

    @JsonProperty("next_offset")
    private int nextOffset;

    private List<FlightInfo> flights;

    @JsonProperty("tz")
    private String timezone;
}
