package com.example.rinha.GlobalExceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.hibernate.exception.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandlers { 
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleHibernateConstraintViolationException(ConstraintViolationException e) { 
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handle(HttpMessageNotReadableException e) { 
        return ResponseEntity.badRequest().build();
    }
}
