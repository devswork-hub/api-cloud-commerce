package com.devworks.cloudcommerce.exceptions;

public class NotFoundException extends RuntimeException   {
    public NotFoundException(String message) {
        super(message);
    }
}