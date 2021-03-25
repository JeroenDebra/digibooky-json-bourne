package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NoBooksFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final EmployeeService employeeService;

    public BookService(BookRepository bookRepository, EmployeeService employeeService) {
        this.bookRepository = bookRepository;
        this.employeeService = employeeService;
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookByISBN(String isbn) {
        return checkIfBookIsEmpty(bookRepository.getBookByISBN(isbn));
    }

    public Book getBookById(String id) {
        return checkIfBookIsEmpty(bookRepository.getBookByID(id));
    }

    public Collection<Book> getBooksByTitle(String title) {
        return checkIfBookListIsEmpty(title, bookRepository.getBooksByTitle(title));
    }

    public Book createBook(String librarianId, Book book) {
        employeeService.isLibrarian(librarianId);
        return bookRepository.addBook(book);
    }

    public Collection<Book> getBookByAuthor(String authorName) {
        return checkIfBookListIsEmpty(authorName, bookRepository.getBookByAuthor(authorName));
    }

    public Book updateBook(String bookId, String librarianId, Book bookWithNewInformation) {
        if (!employeeService.isLibrarian(librarianId)) {
            throw new NotAuthorizedException("You are not a librarian:" + librarianId);
        }
        checkIfBookIsEmpty(bookRepository.getBookByID(bookId));
        return bookRepository.updateBook(bookId, bookWithNewInformation);

    }

    public String deleteBookById(String librarianId, String id) {
        employeeService.isLibrarian(librarianId);
        bookRepository.setBookToDeleted(checkIfBookIsEmpty(bookRepository.getBookByID(id)));
        return "This book with id " + id + " has been deleted.";
    }

    public String restoreBookById(String librarianId, String id) {
        employeeService.isLibrarian(librarianId);
        bookRepository.restoreDeletedBook(checkIfBookIsEmpty(bookRepository.getDeletedBookByID(id)));
        return "This book with id " + id + " has been restored.";
    }

    private Collection<Book> checkIfBookListIsEmpty(String information, Collection<Book> books) {
        if (books.isEmpty()) {
            throw new NoBooksFoundException("No books found with " + information);
        }
        return books;
    }

    private Book checkIfBookIsEmpty(Optional<Book> book) {
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        return book.get();
    }
}
