package com.devworks.cloudcommerce.common.exceptions;

public class AuthenticationException extends RuntimeException  {
    public AuthenticationException(String message) {
        super(message);
    }
}