package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.repository.admin.EmployeeRepository;
import com.switchfully.jsonbourne.domain.repository.admin.LocalEmployeeRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.domain.repository.book.LocalBookRepository;
import com.switchfully.jsonbourne.service.adminService.DefaultEmployeeService;
import com.switchfully.jsonbourne.service.adminService.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBookServiceTest {

    @Test
    void getAllBooksGivesACollectionOfBooks() {
        assertNotNull(new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository())).getAllBooks());
    }

    @Test
    void getBookByISBNWillReturnCorrectBook() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository()));
        assertEquals("9789024564460",defaultBookService.getBookByISBN("9789024564460").getIsbn());
    }

    @Test
    void getBookByISBN_withFakeISBNWillReturnException() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository()));
        Exception exception = assertThrows(BookNotFoundException.class, () -> defaultBookService.getBookByISBN("97"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBookByID_withFakeISBNWillReturnException() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository()));
        Exception exception = assertThrows(BookNotFoundException.class, () -> defaultBookService.getBookById("zfzhozh"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBooksByTitle_whichReturnCorrectBooks() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository()));
        assertTrue(defaultBookService.getBooksByTitle("tit").stream().map(Book::getTitle).collect(Collectors.toList()).contains("title"));
    }

    @Test
    void getBooksByAuthorName() {
        DefaultBookService defaultBookService = new DefaultBookService(new LocalBookRepository(), new DefaultEmployeeService(new LocalEmployeeRepository()));
        assertTrue(defaultBookService.getBookByAuthor("first").stream().map(book -> book.getAuthor().getFullname()).collect(Collectors.toList()).contains("Firstname Lastname"));
    }
}