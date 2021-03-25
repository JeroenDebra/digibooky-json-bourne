package com.switchfully.jsonbourne.domain.repository.book;

import com.switchfully.jsonbourne.domain.models.book.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Collection<Book> getAllBooks();
    Optional<Book> getBookByISBN(String isbn);
    Optional<Book> getBookByID(String id);
    Collection<Book> getBooksByTitle(String title);
    Collection<Book> getBookByAuthor(String authorName);
    Book updateBook(String id,Book bookWithNewInformation);
    Book addBook(Book book);

}
