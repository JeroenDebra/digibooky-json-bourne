package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.BookRepository;
import com.switchfully.jsonbourne.domain.repository.LoanRepository;
import com.switchfully.jsonbourne.domain.repository.MemberRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.NoBooksForLoan;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.switchfully.jsonbourne.infrastructure.exceptions.LoanNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final EmployeeService employeeService;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, EmployeeService employeeService, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.employeeService = employeeService;
        this.memberRepository = memberRepository;
    }

    public BookLoan addBookLoan(BookLoan bookLoan) {
        loanRepository.LendABook(bookLoan);
        var book = bookRepository.getBookByID(bookLoan.getBookId().toString());
        book.get().setOnLoan();
        return bookLoan;
    }

    public UUID getUUIDFromisbn(String isbn) {
        var bookToLoan = bookRepository.getBookByISBNAndIsNotLoaned(isbn);
        if (bookToLoan.isEmpty()) {
            logger.warn("This user tried to loan a book that was not pressent.");
            throw new NoBooksForLoan("There are no book for loan with this isbn.");
        }
        return bookToLoan.get().getId();
    }

    public Collection<BookLoan> getLoansForUser(String librarianId, UUID memberId) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to see the loans for a user without the right permissions");
            throw new NotAuthorizedException("user has no permission to see the loan info");
        }
        return loanRepository.getLoansForUser(memberId);
    }

    public Collection<BookLoan> getAllOverdueBookLoans(String librarianId) {
        if (!employeeService.isLibrarian(librarianId)) {
            logger.warn("This user tried to get a list of overdue books without the right permissions");
            throw new NotAuthorizedException("user has no permission to restore books");
        }
        return loanRepository.getAllOverdueBookLoans();
    }

    public boolean returnBook(String loanId) {
        Optional<BookLoan> bookLoan = loanRepository.getOpenBookLoanFromUser(loanId);
        if (bookLoan.isEmpty()) {
            logger.warn("This user tried to return a book that was on loan");
            throw new LoanNotFoundException("Your loan could not be found:" + loanId);
        }
        bookLoan.get().setReturned(true);
        return isBookReturnedLate(bookLoan.get());
    }

    private boolean isBookReturnedLate(BookLoan bookLoan) {
        return bookLoan.getReturnDate().isBefore(LocalDate.now());
    }

    public Optional<Member> getMemberThatLoanedABook(String bookId) {
        return memberRepository.getMemberById(loanRepository.getBookLoans().stream().filter(bookLoan -> bookLoan.getBookId().toString().equals(bookId)).findFirst().get().getMemberId().toString());
    }
}
