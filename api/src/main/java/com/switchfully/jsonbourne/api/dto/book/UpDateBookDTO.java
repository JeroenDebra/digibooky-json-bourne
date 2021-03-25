package com.switchfully.jsonbourne.api.dto.book;


public class UpDateBookDTO {

    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;

    public UpDateBookDTO() {
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


    public UpDateBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public UpDateBookDTO setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public UpDateBookDTO setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public UpDateBookDTO setSummary(String summary){
        this.summary = summary;
        return this;
    }
}

