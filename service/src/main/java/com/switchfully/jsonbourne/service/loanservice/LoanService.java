package com.switchfully.jsonbourne.service.loanservice;

import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import com.switchfully.jsonbourne.domain.repository.loan.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public BookLoan addBookLoan(BookLoan bookLoan){
        loanRepository.LendABook(bookLoan);
        var book = bookRepository.getBookByID(bookLoan.getBookId().toString());
        book.get().setOnLoan();
        return bookLoan;
    }

    public UUID getUUIDFromisbn(String isbn){
        //getbookByIsbn check if book is availab, give msg that there is no book.
       return bookRepository.getBookByISBN(isbn).get().getId();
    }

    public Collection<BookLoan> getLoansForUser(UUID memberId){
        //check if id is lib
        return loanRepository.getLoansForUser(memberId);
    }
}
