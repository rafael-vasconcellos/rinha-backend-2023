package com.example.rinha.GlobalExceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.sql.SQLTransientConnectionException;

import org.hibernate.exception.ConstraintViolationException;

import com.example.rinha.Pessoa.Exceptions.*;


@ControllerAdvice
public class GlobalExceptionHandlers { 
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) { 
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleJakartaConstraintViolationException(jakarta.validation.ConstraintViolationException e) { 
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) { 
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleHibernateConstraintViolationException(ConstraintViolationException e) { 
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(StackProcessingException.class)
    public ResponseEntity<?> handleStackProcessingException(StackProcessingException e) { 
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(SQLTransientConnectionException.class)
    public ResponseEntity<?> handleConnectionTimeoutException(SQLTransientConnectionException e) {
        return ResponseEntity
            .status(529)
            .build();
    }
    
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<?> handleInternalServerError(InternalServerError e) { 
        System.out.println(RED + "\n\n\n\n\n" + "ERROR: " + e.getMessage() + "\n\n\n\n\n" + RESET);
        return ResponseEntity.internalServerError().build();
    }
}
