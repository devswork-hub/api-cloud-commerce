package com.devworks.cloudcommerce.common.utils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern UUID_REGEX_PATTERN =
        Pattern.compile("\"^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$\"");

    private Validator() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static boolean isValidUUID(String value) {
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

    public static boolean isValidPath(String input) {
        if (!input.startsWith("/")) return false;

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**");
        Path path = Path.of(input);

        return matcher.matches(path);
    }
}
