package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getAllBooks();
    Book getBookByISBN(String isbn);
    Book getBookById(String id);

}
