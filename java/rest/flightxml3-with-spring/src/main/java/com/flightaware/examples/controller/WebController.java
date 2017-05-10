package com.flightaware.examples.controller;

import com.flightaware.examples.beans.AirportBoards;
import com.flightaware.examples.beans.AirportBoardsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by jonathan.cone on 5/8/17.
 */
@RestController
public class WebController {

    @Autowired
    @Qualifier("flightxmlRestTemplate")
    RestTemplate restTemplate;

    @RequestMapping(path = "/airportBoards")
    public ResponseEntity<AirportBoards> getAirportBoards(@RequestParam String airportCode) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("https://flightaware.flightaware.com/json/FlightXML3/AirportBoards")
                .queryParam("airport_code", airportCode)
                .queryParam("include_ex_data", true)
                .queryParam("filter", "all")
                .queryParam("type", "all")
                .queryParam("howMany", 15)
                .queryParam("offset", 0);
        try {
            AirportBoardsResult result = restTemplate.getForObject(uriBuilder.toUriString(), AirportBoardsResult.class);
            return ResponseEntity.ok(result.getAirportBoardsResult());
        } catch (Exception e) {
            return null;
        }
    }
}
