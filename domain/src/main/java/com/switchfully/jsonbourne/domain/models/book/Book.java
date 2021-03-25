package com.switchfully.jsonbourne.domain.models.book;

import java.util.Objects;
import java.util.UUID;

public class Book {

    private final UUID id;
    private String isbn;
    private String title;
    private Author author;
    private String summary;
    private boolean isDeleted;

    public Book(String isbn, String title, Author author,String summary){
        id = UUID.randomUUID();
        this.isbn = isbnValidator(isbn);
        this.title = titleValidator(title);
        this.author = author;
        this.summary = summary;
        this.isDeleted = false;
    }

    private String isbnValidator(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("isbn is not valid");
        }
        return isbn;
    }

    private String titleValidator(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title is not valid");
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
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void update(Book bookWithNewInformation) {
        setSummary(bookWithNewInformation.getSummary());
        setTitle(bookWithNewInformation.getTitle());
        setAuthor(bookWithNewInformation.getAuthor());
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted() {
        if (!isDeleted) {
            this.isDeleted = true;
        }
    }

    public void setUndeleted() {
        if (isDeleted) {
            this.isDeleted = false;
        }
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
