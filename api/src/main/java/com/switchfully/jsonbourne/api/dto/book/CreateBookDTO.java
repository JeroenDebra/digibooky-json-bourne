package com.switchfully.jsonbourne.api.dto.book;

import org.springframework.stereotype.Component;

@Component
public class CreateBookDTO {
    private String isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;

    public CreateBookDTO() {
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

    public CreateBookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public CreateBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateBookDTO setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public CreateBookDTO setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public CreateBookDTO setSummary(String summary){
        this.summary = summary;
        return this;
    }
}
