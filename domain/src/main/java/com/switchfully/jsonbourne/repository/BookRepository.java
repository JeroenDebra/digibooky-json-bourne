package com.switchfully.jsonbourne.repository;

import com.switchfully.jsonbourne.domain.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Collection<Book> getAllBooks();
    Optional<Book> getBookByISBN(String isbn);
    Optional<Book> getBookByID(String id);

}
