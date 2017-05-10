package com.flightaware.examples.utils;

import org.springframework.http.HttpStatus;

/**
 * Created by jonathan.cone on 5/8/17.
 */
public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
