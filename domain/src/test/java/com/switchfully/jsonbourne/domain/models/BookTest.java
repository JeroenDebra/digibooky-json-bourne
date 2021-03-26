package com.switchfully.jsonbourne.domain.models;

import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidISBNException;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidTitleException;
import com.switchfully.jsonbourne.infrastructure.utils.IsbnUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    private Book tenDigitsISBNBook;
    private Book thirteenDigitsISBNBook;


    @BeforeEach
    void init() {
        tenDigitsISBNBook = new Book("1234567890", "jos peters: part deux", new Author("jos", "peters"), "summary");
        thirteenDigitsISBNBook = new Book("1234567891234", "jos peters: part deux", new Author("jos", "peters"), "summary");
    }

    @Test
    public void creatingABookWithBlankTitleThrowsException() {
        Exception exception = assertThrows(InvalidTitleException.class, () -> book = new Book("9789024564460", "", new Author("jos", "peters"), "summary"));
        assertEquals("title is not valid", exception.getMessage());
    }

    @Test
    public void creatingABookWithNullTitleThrowsException() {
        Exception exception = assertThrows(InvalidTitleException.class, () -> book = new Book("9789024564460", null, new Author("jos", "peters"), "summary"));
        assertEquals("title is not valid", exception.getMessage());
    }

    @Test
    public void creatingABookWithBlankSBNThrowsException() {
        Exception exception = assertThrows(InvalidISBNException.class, () -> book = new Book("", "het geheime dagboek van jos peters", new Author("jos", "peters"), "summary"));
        assertEquals("isbn is not valid", exception.getMessage());
    }

    @Test
    public void creatingABookWithNullISBNThrowsException() {
        Exception exception = assertThrows(InvalidISBNException.class, () -> book = new Book("", "het geheime dagboek van jos peters", new Author("jos", "peters"), "summary"));
        assertEquals("isbn is not valid", exception.getMessage());
    }

    @Test
    public void creatingABookWithLettersInISBNThrowsException() {
        Exception exception = assertThrows(InvalidISBNException.class, () -> book = new Book("abcdef1234565", "het geheime dagboek van jos peters", new Author("jos", "peters"), "summary"));
        assertEquals("isbn is not valid", exception.getMessage());
    }

    @Test
    public void creatingABookWith9DigitsInISBNThrowsException() {
        Exception exception = assertThrows(InvalidISBNException.class, () -> book = new Book("123456789", "het geheime dagboek van jos peters", new Author("jos", "peters"), "summary"));
        assertEquals("isbn is not valid", exception.getMessage());
    }

    @Test
    public void aValidBookHasAValidISBN() {
        assertTrue(IsbnUtils.isValidISBN(tenDigitsISBNBook.getIsbn()));
        assertTrue(IsbnUtils.isValidISBN(thirteenDigitsISBNBook.getIsbn()));
    }

}