package com.patrullaje.exception;

/**
 * * * @author Valentina
 */
public class BusinessException extends
        RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
