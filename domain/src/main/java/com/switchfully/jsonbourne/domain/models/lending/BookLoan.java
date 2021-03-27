package com.switchfully.jsonbourne.domain.models.lending;

import java.time.LocalDate;
import java.util.UUID;

public class BookLoan {
    private final UUID id;
    private final UUID memberId;
    private final UUID bookId;
    private final LocalDate returnDate;
    private boolean isReturned;

    public BookLoan(UUID memberId, UUID bookId) {
        this.id = UUID.randomUUID();
        this.memberId = idValidator(memberId, "Userid");
        this.bookId = idValidator(bookId,"Isbn");
        this.returnDate = LocalDate.now().plusWeeks(3);
        this.isReturned = false;
    }

    private UUID idValidator(UUID id,String name) {
        if (id == null) {
            throw new IllegalArgumentException( name + " is not valid");
        }
        return id;
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

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public boolean isReturned() {
        return isReturned;
    }
}
