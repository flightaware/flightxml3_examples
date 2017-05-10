package com.flightaware.examples.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jonathan.cone on 5/5/17.
 */
@Data
@AllArgsConstructor
public class FlightInfo {

    private String ident;
    @JsonProperty("faFlightID")
    private String faFlightID;
    private String airline;
    private String flightnumber;
    private String tailnumber;
    private String type;
    private String codeshares;

    private boolean blocked;
    private boolean diverted;
    private boolean cancelled;

    private AirportDisplay origin;
    private AirportDisplay destination;

    private int filedEte;
    private String route;
    private int filedAltitude;
    private String displayFiledAltitude;
    private int filedAirspeedKts;
    private int filedAirspeedMach;

    private FxmlTimestamp filedDepartureTime;
    private FxmlTimestamp estimatedDepartureTime;
    private FxmlTimestamp actualDepartureTime;
    private int departureDelay;

    private FxmlTimestamp filedArrivalTime;
    private FxmlTimestamp estimatedArrivalTime;
    private FxmlTimestamp actualArrivalTime;
    private int arrivalDelay;

    private FxmlTimestamp filedBlockoutTime;
    private FxmlTimestamp estimatedBlockoutTime;
    private FxmlTimestamp actualBlockoutTime;

    private FxmlTimestamp filedBlockinTime;
    private FxmlTimestamp estimatedBlockinTime;
    private FxmlTimestamp actualBlockinTime;


    private String status;
    private int statusCode;
    private int progressPercent;
    private int stateCode;

    @JsonProperty("aircrafttype")
    private String aircraftType;
    private String displayAircrafttype;
    private String fullAircrafttype;

    private String terminalDest;
    private String gateDest;
    private String terminalOrig;
    private String gateOrig;
    private String bagClaim;

    private int seatsCabinFirst;
    private int seatsCabinBusiness;
    private int seatsCabinCoach;

    private int altitude;
    private String displayAltitude;
    private int airspeedKts;

    private int distanceFiled;
    @JsonProperty("inbound_faFlightID")
    private String inboundFaFlightId;
    private boolean datalink;
    private boolean adhoc;
}
