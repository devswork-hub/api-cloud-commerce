package com.devworks.cloudcommerce.common.utils;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {
    private HttpUtils() { throw new IllegalStateException("You cannot instantiate a utility class"); }

    public static String getHeaderToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            return null;

        return authorizationHeader.substring(7);
    }
}
