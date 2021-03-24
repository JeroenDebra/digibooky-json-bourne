package com.switchfully.jsonbourne.domain;

import java.util.Objects;
import java.util.UUID;

public class Book {

    private final UUID id;
    private String isbn;
    private String title;
    private Author author;

    public Book(String isbn, String title, Author author){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
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
