package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.bookloan.BookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.CreateBookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.LoanBookLibarianDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.ReturnBookLoanDTO;
import com.switchfully.jsonbourne.api.dto.member.AuthorizationIdDTO;
import com.switchfully.jsonbourne.api.mappers.EmployeeMapper;
import com.switchfully.jsonbourne.api.mappers.LoanMapper;
import com.switchfully.jsonbourne.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final LoanService loanService;
    private final LoanMapper loanMapper;
    private final EmployeeMapper employeeMapper;

    public LoanController(LoanService loanService, LoanMapper loanMapper, EmployeeMapper employeeMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookLoanDTO lendBook(@RequestBody CreateBookLoanDTO bookLoanDTO){
        logger.info("A user is requesting to add a book by ISBN " + bookLoanDTO.getIsbn() + " to his loans");
        return loanMapper.bookLoanToBookLoanDTO(loanService.addBookLoan(loanMapper.createBookLoanToBookLoan(bookLoanDTO)));
    }

    @PostMapping(path = "/{memberId}",produces = "application/json",consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getLoansByUser(@RequestBody LoanBookLibarianDTO loanBookLibarianDTO, @PathVariable UUID memberId){
        logger.info("A librarian requests a list of all the books user " + memberId.toString() + " has in loan");
        return loanMapper.listBookLoanToListBookLoanDTO(loanService.getLoansForUser(loanBookLibarianDTO.getLibarianId().toString(), memberId));
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String returnBook(@RequestBody ReturnBookLoanDTO returnBookLoanDTO){
        logger.info(returnBookLoanDTO.getLoanId() + " is being returned");
        if (loanMapper.returnBookUpdate(returnBookLoanDTO)) {
            return "Book has been returned too late";
        }
        return "book has been successfully returned";
    }

    @PostMapping(path = "/overdue",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getAllOverdueBookLoans (@RequestBody AuthorizationIdDTO authorizationIdDTO) {
        logger.info("A librarian requests a list of all overdue books");
        return loanMapper.listBookLoanToListBookLoanDTO(loanService.getAllOverdueBookLoans(employeeMapper.mapToStringId(authorizationIdDTO)));
    }
}
