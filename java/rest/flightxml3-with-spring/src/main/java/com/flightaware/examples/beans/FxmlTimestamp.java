package com.flightaware.examples.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
@AllArgsConstructor
public class FxmlTimestamp {

    private long epoch;
    @JsonProperty("tz")
    private String timezone;
    @JsonProperty("dow")
    private String dayOfWeek;
    private String time;
    @JsonProperty("date")
    private String localDate;
    private long localtime;
}
