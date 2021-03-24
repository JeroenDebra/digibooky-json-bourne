package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.domain.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getAllBooks();
    Book getBookByISBN(String isbn);
    Book getBookById(String id);

}
