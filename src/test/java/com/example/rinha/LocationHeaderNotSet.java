package com.example.rinha;

import org.springframework.http.HttpStatusCode;

public record LocationHeaderNotSet<T>( 
    String error,
    String headers,
    HttpStatusCode statusCode,
    T body
) {}
