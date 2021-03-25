package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.repository.admin.LocalEmployeeRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.domain.repository.book.LocalBookRepository;
import com.switchfully.jsonbourne.service.employeeservice.DefaultEmployeeService;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultBookServiceTest {

    private final DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository()));

    @Test
    void getAllBooksGivesACollectionOfBooks() {
        assertNotNull(defaultBookService.getAllBooks());
    }

    @Test
    void getBookByISBNWillReturnCorrectBook() {
        assertEquals("9789024564460",defaultBookService.getBookByISBN("9789024564460").getIsbn());
    }

    @Test
    void getBookByISBN_withFakeISBNWillReturnException() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> defaultBookService.getBookByISBN("97"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBookByID_withFakeISBNWillReturnException() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> defaultBookService.getBookById("zfzhozh"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBooksByTitle_whichReturnCorrectBooks() {
        assertThat(defaultBookService.getBooksByTitle("tit").stream().map(Book::getTitle).collect(Collectors.toList())).contains("title");
    }

    @Test
    void getBooksByAuthorName() {
        assertThat(defaultBookService.getBookByAuthor("first").stream().map(book -> book.getAuthor().getFullname()).collect(Collectors.toList())).contains("Firstname Lastname");
    }
}