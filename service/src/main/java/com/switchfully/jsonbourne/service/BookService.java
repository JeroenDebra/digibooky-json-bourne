package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NoBooksFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final EmployeeService employeeService;

    public BookService(BookRepository bookRepository, EmployeeService employeeService) {
        this.bookRepository = bookRepository;
        this.employeeService = employeeService;
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Collection<Book> getBookByISBN(String isbn) {
        return checkIfBookListIsEmpty("isbn: " + isbn, bookRepository.getBooksByISBN(isbn));

    }

    public Book getBookById(String id) {
        return checkIfBookIsEmpty(bookRepository.getBookByID(id));
    }

    public Collection<Book> getBooksByTitle(String title) {
        return checkIfBookListIsEmpty(title, bookRepository.getBooksByTitle(title));
    }

    public Book createBook(String librarianId, Book book) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to add a book without the right permissions");
            throw new NotAuthorizedException("user has no permission to create books");
        }
        return bookRepository.addBook(book);
    }

    public Collection<Book> getBookByAuthor(String authorName) {
        return checkIfBookListIsEmpty(authorName, bookRepository.getBookByAuthor(authorName));
    }

    public Book updateBook(String bookId, String librarianId, Book bookWithNewInformation) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to update a book without the right permissions");
            throw new NotAuthorizedException("user has no permission to update books");
        }
        checkIfBookIsEmpty(bookRepository.getBookByID(bookId));
        return bookRepository.updateBook(bookId, bookWithNewInformation);
    }

    public String deleteBookById(String librarianId, String id) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to delete a book without the right permissions");
            throw new NotAuthorizedException("user has no permission to delete books");
        }
        bookRepository.setBookToDeleted(checkIfBookIsEmpty(bookRepository.getBookByID(id)));
        return "This book with id " + id + " has been deleted.";
    }

    public String restoreBookById(String librarianId, String id) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to restore a deleted book without the right permissions");
            throw new NotAuthorizedException("user has no permission to restore books");
        }
        bookRepository.restoreDeletedBook(checkIfBookIsEmpty(bookRepository.getDeletedBookByID(id)));
        return "This book with id " + id + " has been restored.";
    }

    private Collection<Book> checkIfBookListIsEmpty(String information, Collection<Book> books) {
        if (books.isEmpty()) {
            logger.warn("No books were found with the provide query");
            throw new NoBooksFoundException("No books found with " + information);
        }
        return books;
    }

    private Book checkIfBookIsEmpty(Optional<Book> book) {
        if (book.isEmpty()) {
            logger.warn("No book was found with the provide query");
            throw new BookNotFoundException("Book not found");
        }
        return book.get();
    }
}
