package com.springboot.crud.exception;

import org.springframework.http.ResponseEntity;

/**
 * @author rohangupta
 */
public class ResponseEntityBuilder {

    public static ResponseEntity<Object> build(ErrorDetails errorDetails) {
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

}