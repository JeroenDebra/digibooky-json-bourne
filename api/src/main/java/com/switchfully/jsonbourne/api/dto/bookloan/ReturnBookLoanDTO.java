package com.switchfully.jsonbourne.api.dto.bookloan;

import java.util.UUID;

public class ReturnBookLoanDTO {

    private String loanId;


    public ReturnBookLoanDTO() {
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
}
