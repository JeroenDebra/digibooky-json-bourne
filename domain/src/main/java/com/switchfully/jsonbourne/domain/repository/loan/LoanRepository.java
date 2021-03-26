package com.switchfully.jsonbourne.domain.repository.loan;

import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class LoanRepository {

    private final Set<BookLoan> bookLoans;

    public LoanRepository() {
        bookLoans = new HashSet<>();
    }

    public void LendABook(BookLoan book) {
        bookLoans.add(book);
    }

    public Collection<BookLoan> getLoansForUser(UUID memberId){
        return bookLoans.stream().filter(loan -> loan.getMemberId().equals(memberId)).collect(Collectors.toSet());
    }
}
