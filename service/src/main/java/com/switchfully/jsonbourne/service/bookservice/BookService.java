package com.switchfully.jsonbourne.service.bookservice;

import com.switchfully.jsonbourne.domain.models.book.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getAllBooks();
    Book getBookByISBN(String isbn);
    Book getBookById(String id);
    Collection<Book> getBookByAuthor(String authorName);
    Collection<Book> getBooksByTitle(String title);
    String deleteBookById(String librarianId, String bookId);
    String restoreBookById(String libranianId, String bookId);
    Book updateBook(String bookId,String librarianId,Book bookWithNewInformation);
    Book createBook(Book book);
}
