package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.dto.book.UpDateBookDTO;
import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.api.dto.book.CreateBookDTO;
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

    public BookController(BookService bookService, BookMapper bookMapper, EmployeeService employeeService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.employeeService = employeeService;
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
    public Collection<BookDTO> getBooksByAuthor(@PathVariable String fullAuthorName) {
        return bookMapper.listBookToListDTO(bookService.getBookByAuthor(fullAuthorName));
    }

    @GetMapping(path = "title/{title}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getBooksByTitle(@PathVariable String title){
        return bookMapper.listBookToListDTO(bookService.getBooksByTitle(title));
    }

    @PutMapping(path = "id/{id}", consumes = "application/json", produces = "application/json")
    public BookDTO updateBook(@PathVariable String bookId, @RequestBody UpDateBookDTO upDateBookDTO) {
        return bookMapper.bookToDTO(bookService.updateBook(bookId, bookMapper.updateBookDTOToBook(upDateBookDTO)));

    }

    @PostMapping(consumes = "application/json")

    @DeleteMapping(path = "deletebook/{librarianId}/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook (@PathVariable String librarianId, @PathVariable String bookId) {
        return bookService.deleteBookById(librarianId, bookId);
    }

    @PutMapping(path = "restorebook/{librarianId}/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String restoreBook (@PathVariable String librarianId, @PathVariable String bookId) {
        return bookService.restoreBookById(librarianId, bookId);
    }

    @PostMapping(path = "/{librarianId}",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO,@PathVariable String librarianId){
        employeeService.isAdmin(librarianId);
        return bookMapper.createBook(createBookDTO);
    }
}

