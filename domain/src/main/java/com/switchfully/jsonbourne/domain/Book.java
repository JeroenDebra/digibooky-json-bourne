package com.switchfully.jsonbourne.domain;

import java.util.Objects;
import java.util.UUID;

public class Book {

    private final UUID id;
    private String isbn;
    private String title;
    private Author author;
    private String summary;

    public Book(String isbn, String title, Author author,String summary){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        id = UUID.randomUUID();
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
