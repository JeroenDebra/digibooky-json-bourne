package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.bookloan.BookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.CreateBookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.ReturnBookLoanDTO;
import com.switchfully.jsonbourne.domain.models.lending.BookLoan;
import com.switchfully.jsonbourne.service.LoanService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    private final LoanService loanService;

    public LoanMapper(LoanService loanService) {
        this.loanService = loanService;
    }

    public BookLoan createBookLoanToBookLoan(CreateBookLoanDTO bookLoan) {
        return new BookLoan(bookLoan.getMemberId(), loanService.getUUIDFromIsbn(bookLoan.getIsbn()));
    }

    public BookLoanDTO bookLoanToBookLoanDTO(BookLoan bookLoan) {
        return new BookLoanDTO().setId(bookLoan.getId())
                .setBookId(bookLoan.getBookId())
                .setMemberId(bookLoan.getMemberId())
                .setReturnDate(bookLoan.getReturnDate());
    }

    public Collection<BookLoanDTO> listBookLoanToListBookLoanDTO(Collection<BookLoan> list) {
        return list.stream().map(c -> new BookLoanDTO().setId(c.getId()).setBookId(c.getBookId()).setMemberId(c.getMemberId()).setReturnDate(c.getReturnDate())).collect(Collectors.toList());
    }

    public double returnBookUpdate(ReturnBookLoanDTO returnBookLoanDTO){
        return loanService.returnBook(returnBookLoanDTO.getLoanId());
    }
}
