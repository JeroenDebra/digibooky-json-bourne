package com.switchfully.jsonbourne.api.dto.book;

import java.util.UUID;

public class DetailedBookDTO {

    private UUID uuid;
    private String isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;
    private boolean isOnLoan;
    private String memberFirstName;
    private String memberLastName;


    public DetailedBookDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public DetailedBookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public DetailedBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public DetailedBookDTO setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public DetailedBookDTO setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public DetailedBookDTO setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public DetailedBookDTO setOnLoan(boolean onLoan) {
        isOnLoan = onLoan;
        return this;
    }

    public DetailedBookDTO setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
        return this;
    }

    public DetailedBookDTO setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
        return this;
    }
}
