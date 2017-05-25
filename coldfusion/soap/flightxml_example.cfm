<cfscript>
    stAuth = structNew();
    stAuth.username = "sampleuser";
    stAuth.password = "abc123abc123abc123abc123abc123abc123";
    ws = createObject("webservice", "https://flightxml.flightaware.com/soap/FlightXML3/wsdl", stAuth);

    stAirportBoards = structNew();
    stAirportBoards.airport_code = "KSFO";
    stAirportBoards.include_ex_data = 0;
    stAirportBoards.type = "enroute";
    stAirportBoards.howMany = 10;

    aAirportBoardsStruct = ws.AirportBoards(stAirportBoards);
</cfscript>
<cfset EnrouteFlights = aAirportBoardsStruct.getAirportBoardsResult().getEnroute().getFlights()>

<cfloop from="1" to="#ArrayLen(EnrouteFlights)#" index="i">
    <cfset dtime = DateAdd("s",EnrouteFlights[i].actualdeparturetime.epoch,DateConvert("utc2Local", "January 1 1970 00:00"))>
    <cfoutput>#EnrouteFlights[i].ident# = #EnrouteFlights[i].aircrafttype# departed at #dtime#<br></cfoutput>
</cfloop>