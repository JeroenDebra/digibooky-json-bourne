package com.switchfully.jsonbourne.api.controllers;


import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.mappers.BookMapper;
import com.switchfully.jsonbourne.service.adminService.EmployeeService;
import com.switchfully.jsonbourne.service.bookservice.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final EmployeeService employeeService;

    public BookController(BookService bookService, BookMapper bookMapper, EmployeeService employeeService){
        this.employeeService = employeeService;
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getAllBooks() {
        return bookMapper.listBookToListDTO(bookService.getAllBooks());
    }

    @GetMapping(path = "id/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable String id) {
        return bookMapper.bookToDTO(bookService.getBookById(id));
    }

    @GetMapping(path = "isbn/{isbn}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookByISBN(@PathVariable String isbn) {
        return bookMapper.bookToDTO(bookService.getBookByISBN(isbn));
    }

    @GetMapping(path = "author/{fullAuthorName}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByAuthor(@PathVariable String fullAuthorName){
        return bookMapper.listBookToListDTO(bookService.getBookByAuthor(fullAuthorName));
    }

    @GetMapping(path = "title/{title}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByTitle (@PathVariable String title){
        return bookMapper.listBookToListDTO(bookService.getBooksByTitle(title));
    }

    @DeleteMapping(path = "deletebook/{librarianId}/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook (@PathVariable String librarianId, @PathVariable String bookId) {
        employeeService.isLibrarian(librarianId);
        return bookService.deleteBookById(bookId);
    }

    @PutMapping(path = "restorebook/{librarianId}/{bookId}", produces = "application/jsnon")
    @ResponseStatus(HttpStatus.OK)
    public String restoreBook (@PathVariable String librarianId, @PathVariable String bookId) {
        employeeService.isLibrarian(librarianId);
        return bookService.restoreBookById(bookId);
    }
}

