package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.Book;
import com.switchfully.jsonbourne.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

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
    public Book getBookById(String id) {
        return bookRepository.getAllBooks().stream().filter(book -> book.getId().toString().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }
}
