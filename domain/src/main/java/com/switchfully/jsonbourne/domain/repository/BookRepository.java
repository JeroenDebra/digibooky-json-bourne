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

    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableSet(books).stream().filter(book -> !book.isDeleted()).collect(Collectors.toList());
    }

    private void fillInList(){
        books.add(new Book("9789024564460","title",new Author("Firstname","Lastname"),"this is the story ....."));
    }

    public Optional<Book> getBookByISBN(String isbn){
        return books.stream().filter(book -> book.getIsbn().equals(isbn) && !book.isDeleted()).findFirst();
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

    public void setBookToDeleted(Book book) {
        book.setIsDeleted();
    }

    public void restoreDeletedBook(Book book) {
        book.setUndeleted();
    }

    public Book updateBook(String id, Book bookWithNewInformation){

        Book bookToUpdate = getBookByID(id).get();
        bookToUpdate.update(bookWithNewInformation);

//        bookToUpdate.setAuthor(bookWithNewInformation.getAuthor());
//        bookToUpdate.setTitle(bookWithNewInformation.getTitle());
//        bookToUpdate.setSummary(bookWithNewInformation.getSummary());
        return bookToUpdate;
    }

}
