package com.flightaware.examples.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
public class AirportBoardsResult {

    @JsonProperty("AirportBoardsResult")
    private AirportBoards airportBoardsResult;
}
