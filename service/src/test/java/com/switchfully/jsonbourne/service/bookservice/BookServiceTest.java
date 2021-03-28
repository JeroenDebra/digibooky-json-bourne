package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import com.switchfully.jsonbourne.domain.repository.EmployeeRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NoBooksFoundException;
import com.switchfully.jsonbourne.service.BookService;
import com.switchfully.jsonbourne.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {

    private final BookRepository bookRepository = new BookRepository();
    private final BookService bookService =  new BookService(bookRepository,new EmployeeService(new EmployeeRepository()));


    @BeforeEach
        void init () {
        Book book = new Book("9789024564460", "title",new Author("firstname","lastname"),"This is a summary...");
        bookRepository.addBook(book);
        }


    @Test
    void getAllBooksGivesACollectionOfBooks() {
        assertNotNull(bookService.getAllBooks());
    }

    @Test
    void getBookByISBNWillReturnCorrectBook() {
        assertEquals(1,bookService.getBookByISBN("9789024564460").size());
    }

    @Test
    void getBookByISBN_withFakeISBNWillReturnException() {
        assertThrows(NoBooksFoundException.class, () -> bookService.getBookByISBN("FAKEISBNNUMBER"));
    }

    @Test
    void getBookByID_withFakeISBNWillReturnException() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.getBookById("zfzhozh"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void getBooksByTitle_whichReturnCorrectBooks() {
        assertThat(bookService.getBooksByTitle("tit").stream().map(Book::getTitle).collect(Collectors.toList())).contains("title");
    }

    @Test
    void getBooksByAuthorName() {
        assertThat(bookService.getBookByAuthor("first").stream().map(book -> book.getAuthor().getFullname()).collect(Collectors.toList())).contains("firstname lastname");
    }
}