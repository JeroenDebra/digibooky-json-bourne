package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.dto.book.UpDateBookDTO;
import com.switchfully.jsonbourne.api.dto.book.CreateBookDTO;
import com.switchfully.jsonbourne.api.mappers.BookMapper;
import com.switchfully.jsonbourne.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getAllBooks() {
        logger.info("A user requested to get a list of all the books");
        return bookMapper.listBookToListDTO(bookService.getAllBooks());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable String id) {
        logger.info("A user requested a specific book by its ID");
        return bookMapper.bookToDTO(bookService.getBookById(id));
    }

    @GetMapping(produces = "application/json", params = {"isbn"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookByISBN(@RequestParam String isbn) {
        logger.info("A user requested a specific book by its ISBN");
        return bookMapper.bookToDTO(bookService.getBookByISBN(isbn));
    }

    @GetMapping(produces = "application/json", params = {"fullAuthorName"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByAuthor(@RequestParam String fullAuthorName) {
        logger.info("A user requested a list of books by a specific author");
        return bookMapper.listBookToListDTO(bookService.getBookByAuthor(fullAuthorName));
    }

    @GetMapping(produces = "application/json", params = {"title"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByTitle(@RequestParam String title) {
        logger.info("A user requested a list of books with a certain title");
        return bookMapper.listBookToListDTO(bookService.getBooksByTitle(title));
    }

    @PutMapping(path = "{bookId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBook(@PathVariable String bookId, @RequestParam String librarianId, @RequestBody UpDateBookDTO upDateBookDTO) {
        logger.info("A librarian tried to update a specific book");
        return bookMapper.bookToDTO(bookService.updateBook(bookId, librarianId, bookMapper.updateBookDTOToBook(upDateBookDTO)));
    }

    @DeleteMapping(path = "{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook(@RequestParam String librarianId, @PathVariable String bookId) {
        logger.info("A librarian tried to delete a specific book");
        return bookService.deleteBookById(librarianId, bookId);
    }

    @PostMapping(path = "/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String restoreBook(@RequestParam String librarianId, @PathVariable String bookId) {
        logger.info("A librarian tried to restore a specific deleted book");
        return bookService.restoreBookById(librarianId, bookId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO, @RequestParam String librarianId) {
        logger.info("A librarian tried to register a new book into the database");
        return bookMapper.createBook(librarianId, createBookDTO);
    }
}

