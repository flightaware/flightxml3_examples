package com.flightaware.examples.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightaware.examples.beans.FlightxmlError;
import com.flightaware.examples.utils.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by jonathan.cone on 5/8/17.
 */
@Component
public class FlightxmlErrorHandler implements ResponseErrorHandler {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        FlightxmlError error = objectMapper.readValue(response.getBody(), FlightxmlError.class);
        System.err.println(String.format("Error: %s", error.getError()));
    }
}
