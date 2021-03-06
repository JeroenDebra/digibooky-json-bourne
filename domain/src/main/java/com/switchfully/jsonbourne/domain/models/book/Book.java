package com.switchfully.jsonbourne.domain.models.book;

import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidISBNException;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidTitleException;
import com.switchfully.jsonbourne.infrastructure.utils.IsbnUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

public class Book {

    private static final Logger logger = LoggerFactory.getLogger(Book.class);

    private final UUID id;
    private final String isbn;
    private String title;
    private Author author;
    private String summary;
    private boolean isDeleted;
    private boolean isOnLoan;

    public Book(String isbn, String title, Author author,String summary){
        id = UUID.randomUUID();
        this.isbn = isbnValidator(isbn);
        this.title = titleValidator(title);
        this.author = author;
        this.summary = summary;
        this.isDeleted = false;
        this.isOnLoan = false;
    }

    private String isbnValidator(String isbn) {
        if (!IsbnUtils.isValidISBN(isbn)) {
            logger.warn("The user tried to register an invalid ISBN");
            throw new InvalidISBNException("isbn is not valid");
        }
        return isbn;
    }

    private String titleValidator(String title) {
        if (title == null || title.isBlank()) {
            logger.warn("The user tried to register an invalid title");
            throw new InvalidTitleException("title is not valid");
        }
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public UUID getId(){return id;}

    public String getSummary() {
        return summary;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }
    
    public Book setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isOnLoan(){return isOnLoan;}

    public Book setIsDeleted() {
        if (!isDeleted) {
            this.isDeleted = true;
        }
        return this;
    }

    public Book setUndeleted() {
        if (isDeleted) {
            this.isDeleted = false;
        }
        return this;
    }

    public void setOnLoan(){
        if(!isOnLoan){
            this.isOnLoan = true;
        }
    }

    public void returnBook(){
        if(isOnLoan){
            this.isOnLoan = false;
        }
    }

    public Book update(Book bookWithNewInformation) {
        return this.setTitle(bookWithNewInformation.getTitle()).setAuthor(bookWithNewInformation.getAuthor()).setSummary(bookWithNewInformation.getSummary());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
