package com.devworks.cloudcommerce.common.utils;

public class Regex {
    private Regex() {
        throw new IllegalStateException("Utility class");
    }

    public static String camelToSnake(String input) {
        return input.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}
