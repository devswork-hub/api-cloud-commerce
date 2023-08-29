package com.devworks.cloudcommerce.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private PasswordUtils() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
