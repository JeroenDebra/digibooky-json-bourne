package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.Book;
import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.repository.BookRepository;
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
        Optional<Book> book = bookRepository.getBookByISBN(isbn);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        return book.get();
    }

    @Override
    public Book getBookById(String id) {
        Optional<Book> book = bookRepository.getBookByID(id);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        return book.get();
    }
}
