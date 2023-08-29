package com.devworks.cloudcommerce.common.utils;

public class Converter {
    private Converter() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static String fromCamelToSnake(String input) {
        return input.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}
