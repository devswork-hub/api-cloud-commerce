package com.devworks.cloudcommerce.common.exceptions;

public class NotFoundException extends RuntimeException   {
    public NotFoundException(String message) {
        super(message);
    }
}