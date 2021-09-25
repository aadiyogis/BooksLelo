package com.futuristic.booksLelo.util;

import org.springframework.stereotype.Component;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Component
public class ValidationUtil {

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNonNull(Object object) {
        return !isNull(object);
    }

    public static boolean isValid(String input) {
        return input != null && !input.isEmpty();
    }
}
