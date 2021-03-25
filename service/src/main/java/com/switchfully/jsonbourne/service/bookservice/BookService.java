package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.models.book.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getAllBooks();
    Book getBookByISBN(String isbn);
    Book getBookById(String id);
    Collection<Book> getBookByAuthor(String authorName);
    Collection<Book> getBooksByTitle(String title);
    String deleteBookById(String id);
    String restoreBookById(String bookId);
}
