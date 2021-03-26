package com.switchfully.jsonbourne.api.dto.bookloan;


import java.util.UUID;

public class LoanBookLibarianDTO {
    private UUID libarianId;

    public LoanBookLibarianDTO() {
    }

    public UUID getLibarianId() {
        return libarianId;
    }

    public LoanBookLibarianDTO setLibarianId(UUID libarianId) {
        this.libarianId = libarianId;
        return this;
    }
}
