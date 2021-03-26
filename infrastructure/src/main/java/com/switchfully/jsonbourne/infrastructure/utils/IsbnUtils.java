package com.switchfully.jsonbourne.infrastructure.utils;

import java.util.regex.Pattern;

public class IsbnUtils {

    private static final Pattern VALID_ISBN_REGEX = Pattern.compile("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");

    public static boolean isValidISBN (String isbn) {
        return VALID_ISBN_REGEX.matcher(isbn).matches();
    }
}
