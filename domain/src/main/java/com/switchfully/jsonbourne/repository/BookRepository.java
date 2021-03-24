package com.switchfully.jsonbourne.repository;

import com.switchfully.jsonbourne.domain.Book;

import java.util.Collection;

public interface BookRepository {

    Collection<Book> getAllBooks();

}
