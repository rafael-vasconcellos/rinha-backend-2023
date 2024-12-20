package com.example.rinha;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;

public class LocationHeaderNotSet<T> { 
    String error;
    String headers;
    HttpStatusCode statusCode;
    T body;

    public LocationHeaderNotSet(String error, String headers, HttpStatusCode statusCode, Optional<T> body) {
        this.error = error;
        this.headers = headers;
        this.statusCode = statusCode;
        this.body = body.get();
    }
}
