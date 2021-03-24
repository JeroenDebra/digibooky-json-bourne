package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.Book;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Collection<Book> getAllBooks();
    Book findByISBN(String isbn) throws Exception;
    Book getBookById(String id);

}
