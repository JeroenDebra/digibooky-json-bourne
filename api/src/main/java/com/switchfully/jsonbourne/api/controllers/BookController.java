package com.switchfully.jsonbourne.api.controllers;


import com.switchfully.jsonbourne.api.dto.BookDTO;
import com.switchfully.jsonbourne.api.mappers.BookMapper;
import com.switchfully.jsonbourne.domain.domain.Author;
import com.switchfully.jsonbourne.service.bookservice.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
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
        return bookMapper.bookToBookDTOWithSummary(bookService.getBookById(id));
    }

    @GetMapping(path = "isbn/{isbn}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO findByIsbn(@PathVariable String isbn) {
        return bookMapper.bookToDTO(bookService.getBookByISBN(isbn));
    }


    @GetMapping(path = "author/{fullAuthorName}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByAuthor(@PathVariable String fullAuthorName){
        return bookMapper.listBookToListDTO(bookService.getBookByAuthor(fullAuthorName));
    }
}

