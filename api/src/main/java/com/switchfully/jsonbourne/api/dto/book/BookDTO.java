package com.switchfully.jsonbourne.api.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private UUID id;
    private String isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;

    public BookDTO() {
    }

    public UUID getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getSummary() {
        return summary;
    }

    public BookDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDTO setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public BookDTO setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public BookDTO setSummary(String summary){
        this.summary = summary;
        return this;
    }
}
