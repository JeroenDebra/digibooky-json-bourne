package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.domain.Author;
import com.switchfully.jsonbourne.domain.domain.Book;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Collection<Book> getAllBooks();
    Optional<Book> getBookByISBN(String isbn);
    Optional<Book> getBookByID(String id);
    Collection<Book> getBookByAuthor(String authorName);

}
