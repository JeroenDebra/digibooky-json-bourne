package com.switchfully.jsonbourne.infrastructure.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsbnUtilsTest {

    @Test
    void IsbnWith13Numbers() {
        assertTrue(IsbnUtils.isValidISBN("9789022561577"));
    }

    @Test
    void IsbnWith10Numbers() {
        assertTrue(IsbnUtils.isValidISBN("9789022561"));
    }

    @Test
    void IsbnWith15Numbers() {
        assertFalse(IsbnUtils.isValidISBN("978902256157787"));
    }

    @Test
    void IsbnWith12Numbers() {
        assertFalse(IsbnUtils.isValidISBN("97890225615778"));
    }

    @Test
    void IsbnWith4Numbers() {
        assertFalse(IsbnUtils.isValidISBN("9789"));
    }

    @Test
    void IsbnIsEmpty() {
        assertFalse(IsbnUtils.isValidISBN(""));
    }

    @Test
    void IsbnWith12NumbersAnd1Letter() {
        assertFalse(IsbnUtils.isValidISBN("978902256157A"));
    }

    @Test
    void IsbnWithOnlyLetters() {
        assertFalse(IsbnUtils.isValidISBN("abcdefghijklm"));
    }
}