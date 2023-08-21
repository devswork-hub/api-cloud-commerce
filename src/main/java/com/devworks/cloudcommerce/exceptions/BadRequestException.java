package com.devworks.cloudcommerce.exceptions;

public class BadRequestException extends RuntimeException  {
    public BadRequestException(String message) {
        super(message);
    }
}