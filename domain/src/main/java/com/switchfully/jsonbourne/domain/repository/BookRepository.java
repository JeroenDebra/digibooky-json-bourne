package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.domain.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Collection<Book> getAllBooks();
    Optional<Book> getBookByISBN(String isbn);
    Optional<Book> getBookByID(String id);

}
