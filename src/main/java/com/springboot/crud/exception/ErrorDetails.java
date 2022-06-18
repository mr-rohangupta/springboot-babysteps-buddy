package com.springboot.crud.exception;

import lombok.Data;

import java.util.Date;

/**
 * @author rohangupta
 */
@Data
public class ErrorDetails {

    private final String message;
    private final String details;
    private final Date timeStamp;

    public ErrorDetails(String message, String details, Date timeStamp) {
        this.message = message;
        this.details = details;
        this.timeStamp = timeStamp;
    }
}