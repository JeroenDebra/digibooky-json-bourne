package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.models.Book;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.domain.repository.LocalBookRepository;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBookServiceTest {

    @Test
    void getAllBooksGivesACollectionOfBooks() {
        assertNotNull(new DefaultBookService(new LocalBookRepository()).getAllBooks());
    }

    @Test
    void getBookByISBNWillReturnCorrectBook() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository());
        assertEquals("9789024564460",defaultBookService.getBookByISBN("9789024564460").getIsbn());
    }

    @Test
    void getBookByISBN_withFakeISBNWillReturnException() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository());
        Exception exception = assertThrows(BookNotFoundException.class, () -> defaultBookService.getBookByISBN("97"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBookByID_withFakeISBNWillReturnException() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository());
        Exception exception = assertThrows(BookNotFoundException.class, () -> defaultBookService.getBookById("zfzhozh"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBooksByTitle_whichReturnCorrectBooks() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository());
        assertTrue(defaultBookService.getBooksByTitle("tit").stream().map(Book::getTitle).collect(Collectors.toList()).contains("title"));
    }

    @Test
    void getBooksByAuthorName() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository());
        assertTrue(defaultBookService.getBookByAuthor("first").stream().map(book -> book.getAuthor().getFullname()).collect(Collectors.toList()).contains("Firstname Lastname"));
    }
}