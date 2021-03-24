package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.domain.Book;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Collection<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Book getBookByISBN(String isbn){
        return checkIfBookIsEmpty(bookRepository.getBookByISBN(isbn));
    }

    @Override
    public Book getBookById(String id) {
        return checkIfBookIsEmpty(bookRepository.getBookByID(id));
    }

    private Book checkIfBookIsEmpty(Optional<Book> book) {
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        return book.get();
    }

}
