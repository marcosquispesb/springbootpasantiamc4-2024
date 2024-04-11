package com.example.springbootdemo.rest.exceptions;

/**
 * NotDataFoundException
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class OperationException extends RuntimeException {

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
