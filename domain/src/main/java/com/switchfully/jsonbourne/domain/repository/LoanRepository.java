package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class LoanRepository {

    private final Set<BookLoan> bookLoans;

    public LoanRepository() {
        bookLoans = new HashSet<>();
    }

    public Set<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void LendABook(BookLoan book) {
        bookLoans.add(book);
    }

    public Collection<BookLoan> getLoansForUser(UUID memberId){
        return bookLoans.stream().filter(loan -> loan.getMemberId().equals(memberId)).collect(Collectors.toSet());
    }

    public Collection<BookLoan> getAllOverdueBookLoans() {
        return bookLoans.stream().filter(bookLoan -> bookLoan.getReturnDate().isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public Optional<BookLoan> getOpenBookLoanFromUser(String loanId){
       return bookLoans.stream().filter(bookLoan -> !bookLoan.isReturned())
                .filter(bookLoan -> bookLoan.getId().toString().equals(loanId))
                .findFirst();
    }
}
