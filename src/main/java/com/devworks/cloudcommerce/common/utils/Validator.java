package com.devworks.cloudcommerce.common.utils;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern UUID_REGEX_PATTERN =
        Pattern.compile("\"^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$\"");

    private Validator() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static boolean checkIsUUID(String value) {
        if (value == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(value).matches();
    }

    public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
