package com.switchfully.jsonbourne.domain;

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
}
