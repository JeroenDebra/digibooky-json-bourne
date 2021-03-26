package com.switchfully.jsonbourne.api.dto.bookloan;

import java.util.UUID;

public class CreateBookLoanDTO {
    private UUID memberId;
    private String isbn;

    public CreateBookLoanDTO() {
    }

    public UUID getMemberId() {
        return memberId;
    }

    public String getIsbn() {
        return isbn;
    }

    public CreateBookLoanDTO setMemberId(UUID memberId) {
        this.memberId = memberId;
        return this;
    }

    public CreateBookLoanDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
}
