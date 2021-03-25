package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.dto.book.UpDateBookDTO;
import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.api.dto.book.CreateBookDTO;
import com.switchfully.jsonbourne.api.mappers.BookMapper;
import com.switchfully.jsonbourne.service.employeeservice.EmployeeService;
import com.switchfully.jsonbourne.service.bookservice.BookService;
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
    private final EmployeeService employeeService;

    public BookController(BookService bookService, BookMapper bookMapper, EmployeeService employeeService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getAllBooks() {
        logger.info("A user requested to get a list of all the books");
        return bookMapper.listBookToListDTO(bookService.getAllBooks());
    }

    @GetMapping(path = "id/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable String id) {
        logger.info("A user requested a specific book by its ID");
        return bookMapper.bookToDTO(bookService.getBookById(id));
    }

    @GetMapping(path = "isbn/{isbn}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookByISBN(@PathVariable String isbn) {
        logger.info("A user requested a specific book by its ISBN");
        return bookMapper.bookToDTO(bookService.getBookByISBN(isbn));
    }

    @GetMapping(path = "author/{fullAuthorName}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByAuthor(@PathVariable String fullAuthorName){
        logger.info("A user requested a list of books by a specific author");
        return bookMapper.listBookToListDTO(bookService.getBookByAuthor(fullAuthorName));
    }

    @GetMapping(path = "title/{title}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByTitle (@PathVariable String title){
        logger.info("A user requested a list of books with a certain title");
        return bookMapper.listBookToListDTO(bookService.getBooksByTitle(title));
    }

    @DeleteMapping(path = "{librarianId}/{bookId}", produces = "application/json")
    @PutMapping(path = "id/{bookId}", consumes = "application/json", produces = "application/json")
    public BookDTO updateBook(@PathVariable String bookId,@RequestParam String librarianId, @RequestBody UpDateBookDTO upDateBookDTO) {
        return bookMapper.bookToDTO(bookService.updateBook(bookId,librarianId, bookMapper.updateBookDTOToBook(upDateBookDTO)));
    }

    @DeleteMapping(path = "deletebook/{librarianId}/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook (@PathVariable String librarianId, @PathVariable String bookId) {
        logger.info("A librarian tried to delete a specific book");
        return bookService.deleteBookById(librarianId, bookId);
    }

    @PatchMapping(path = "{librarianId}/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String restoreBook (@PathVariable String librarianId, @PathVariable String bookId) {
        logger.info("A librarian tried to restore a specific deleted book");
        return bookService.restoreBookById(librarianId, bookId);
    }

    @PostMapping(path = "{librarianId}",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO,@PathVariable String librarianId){
        logger.info("A librarian tried to register a new book into the database");
        return bookMapper.createBook(librarianId, createBookDTO);
    }
}

