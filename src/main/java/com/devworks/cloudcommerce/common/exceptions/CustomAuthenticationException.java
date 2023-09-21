package com.devworks.cloudcommerce.common.exceptions;

public class CustomAuthenticationException extends RuntimeException  {
    public CustomAuthenticationException(String message) {
        super(message);
    }
}