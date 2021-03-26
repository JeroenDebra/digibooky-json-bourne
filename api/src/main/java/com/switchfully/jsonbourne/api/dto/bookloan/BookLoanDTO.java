package com.switchfully.jsonbourne.api.dto.bookloan;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookLoanDTO {
    private UUID id;
    private UUID memberId;
    private UUID bookId;
    private LocalDate returnDate;

    public BookLoanDTO() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BookLoanDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public BookLoanDTO setMemberId(UUID memberId) {
        this.memberId = memberId;
        return this;
    }

    public BookLoanDTO setBookId(UUID bookId) {
        this.bookId = bookId;
        return this;
    }

    public BookLoanDTO setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }
}
