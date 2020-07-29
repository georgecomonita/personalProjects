package com.enered.course.exception;

public class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
