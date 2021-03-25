package com.switchfully.jsonbourne.api.dto.book;

import java.util.UUID;

public class BookDTO {

    private UUID uuid;
    private String isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;

    public BookDTO() {
    }

    public UUID getUuid() {
        return uuid;
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

    public BookDTO setUuid(UUID uuid) {
        this.uuid = uuid;
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
