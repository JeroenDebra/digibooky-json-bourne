package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.domain.models.book.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final Set<Book> books = new HashSet<>();

    public BookRepository() {
        fillInList();
    }

    private void fillInList() {
    }

    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableSet(books).stream().filter(book -> !book.isDeleted()).collect(Collectors.toList());
    }

    public Collection<Book> getBooksByISBN(String isbn) {
        return books.stream().filter(book -> book.getIsbn().contains(isbn) && !book.isDeleted()).collect(Collectors.toList());
    }

    public Optional<Book> getBookByID(String id) {
        return books.stream().filter(book -> book.getId().toString().equals(id) && !book.isDeleted()).findFirst();
    }

    public Collection<Book> getBookByAuthor(String authorName) {
        return books.stream().filter(book -> book.getAuthor().getFullname().toLowerCase().contains(authorName.toLowerCase()) && !book.isDeleted()).collect(Collectors.toList());
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    public Collection<Book> getBooksByTitle(String title) {
        return books.stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()) && !book.isDeleted()).collect(Collectors.toList());
    }

    public Optional<Book> getDeletedBookByID(String id) {
        return books.stream().filter(book -> book.getId().toString().equals(id) && book.isDeleted()).findFirst();
    }

    public Book setBookToDeleted(Book book) {
        return book.setIsDeleted();
    }

    public Book restoreDeletedBook(Book book) {
        return book.setUndeleted();
    }

    public Book updateBook(String id, Book bookWithNewInformation) {
        return getBookByID(id).get().update(bookWithNewInformation);
    }

    public Optional<Book> getBookByISBNAndIsNotLoaned(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn) && !book.isDeleted() && !book.isOnLoan()).findFirst();
    }

}
