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

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isOnLoan(){return isOnLoan;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
