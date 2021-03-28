package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.LoanRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.MemberNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NoBooksForLoan;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import com.switchfully.jsonbourne.infrastructure.utils.FineCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.switchfully.jsonbourne.infrastructure.exceptions.LoanNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;
    private final EmployeeService employeeService;
    private final BookService bookService;
    private final MemberService memberService;

    public LoanService(LoanRepository loanRepository, EmployeeService employeeService, BookService bookService, MemberService memberService) {
        this.loanRepository = loanRepository;
        this.employeeService = employeeService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public BookLoan addBookLoan(BookLoan bookLoan) {
        if(memberService.getMemberById(bookLoan.getMemberId().toString()).isEmpty()) {
            logger.warn("A not known user tried to loan a book.");
            throw new MemberNotFoundException("Member id is not found.");
        }
        loanRepository.LendABook(bookLoan);
        bookService.getBookById(bookLoan.getBookId().toString()).setOnLoan();
        return bookLoan;
    }

    public UUID getUUIDFromIsbn(String isbn) {
        var bookToLoan = bookService.getBookByISBNAndIsNotLoaned(isbn);
        if (bookToLoan.isEmpty()) {
            logger.warn("This user tried to loan a book that was not present.");
            throw new NoBooksForLoan("There are no book for loan with ISBN " + isbn);
        }
        return bookToLoan.get().getId();
    }

    public Collection<BookLoan> getLoansForUser(String librarianId, UUID memberId) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to see the loans for a user without the right permissions");
            throw new NotAuthorizedException("This user with id " + librarianId + " has no permission to see the loan info");
        }
        return loanRepository.getLoansForUser(memberId);
    }

    public Collection<BookLoan> getAllOverdueBookLoans(String librarianId) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user with id " + librarianId + " is requesting a list of all overdue books");
            throw new NotAuthorizedException("This user with id " + librarianId + " has no permission to view all overdue books");
        }
        return loanRepository.getAllOverdueBookLoans();
    }

    public double returnBook(String loanId) {
        Optional<BookLoan> bookLoan = loanRepository.getBookLoanWithId(loanId);
        if (bookLoan.isEmpty()) {
            logger.warn("This user tried to return a book that was on loan");
            throw new LoanNotFoundException("Your loan could not be found:" + loanId);
        }
        bookLoan.get().setReturned(true);
        getBookByLoanId(bookLoan).returnBook();
        getMemberByLoanId(bookLoan).setTotalAmountOfFines(CalculateFine(bookLoan));
        return CalculateFine(bookLoan);
    }

    private Member getMemberByLoanId(Optional<BookLoan> bookLoan) {
        return memberService.getMemberById(bookLoan.get().getMemberId().toString()).get();
    }

    private Book getBookByLoanId(Optional<BookLoan> bookLoan) {
        return bookService.getBookById(bookLoan.get().getBookId().toString());
    }

    private double CalculateFine(Optional<BookLoan> bookLoan) {
        return FineCalculator.CalculateFine(bookLoan.get().getReturnDate());
    }

    public Optional<Member> getMemberThatLoanedABook(String bookId) {
        return memberService.getMemberById(loanRepository.getBookLoans().stream().filter(bookLoan -> bookLoan.getBookId().toString().equals(bookId)).findFirst().get().getMemberId().toString());
    }

    public Collection<BookLoan> getLoansOfBook(String bookId, String librarianId) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("A user with id " + librarianId + " is requesting a history of the loans of a book");
            throw new NotAuthorizedException("You must be a librarian to see loans of a book");
        }
        return loanRepository.getLoansOfBook(bookId);
    }
}
