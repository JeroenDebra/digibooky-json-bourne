package com.switchfully.jsonbourne.service.loanservice;

import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import com.switchfully.jsonbourne.domain.repository.loan.LoanRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import com.switchfully.jsonbourne.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final EmployeeService employeeService;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, EmployeeService employeeService) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.employeeService = employeeService;
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

    public Collection<BookLoan> getAllOverdueBookLoans(String librarianId) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to get a list of overdue books without the right permissions");
            throw new NotAuthorizedException("user has no permission to restore books");
        }
        return loanRepository.getAllOverdueBookLoans();
    }
}
