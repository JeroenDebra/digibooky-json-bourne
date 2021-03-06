package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.bookloan.BookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.CreateBookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.LoanBookLibarianDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.ReturnBookLoanDTO;
import com.switchfully.jsonbourne.api.dto.employee.AuthorizationIdDTO;
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
    public BookLoanDTO lendBook(@RequestBody CreateBookLoanDTO bookLoanDTO) {
        logger.info("A user with id " + bookLoanDTO.getMemberId() + " is requesting to add a book by ISBN " + bookLoanDTO.getIsbn() + " to his loans");
        return loanMapper.bookLoanToBookLoanDTO(loanService.addBookLoan(loanMapper.createBookLoanToBookLoan(bookLoanDTO)));
    }

    @PostMapping(path = "/{memberId}", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getLoansByUser(@RequestBody LoanBookLibarianDTO loanBookLibarianDTO, @PathVariable UUID memberId) {
        logger.info("A user with id " + loanBookLibarianDTO.getLibarianId() + " requests a list of all the books user " + memberId + " has in loan");
        return loanMapper.listBookLoanToListBookLoanDTO(loanService.getLoansForUser(loanBookLibarianDTO.getLibarianId().toString(), memberId));
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String returnBook(@RequestBody ReturnBookLoanDTO returnBookLoanDTO) {
        logger.info(returnBookLoanDTO.getLoanId() + " is being returned");
        String result = "Book has been successfully returned ";
        if (loanMapper.returnBookUpdate(returnBookLoanDTO) > 0) {
            return result + "with a fine off " + loanMapper.returnBookUpdate(returnBookLoanDTO) + " EUR";
        }
        return result;
    }

    @PostMapping(path = "/overdue", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getAllOverdueBookLoans(@RequestBody AuthorizationIdDTO authorizationIdDTO) {
        logger.info("A user with id " + authorizationIdDTO + " requests a list of all overdue books");
        return loanMapper.listBookLoanToListBookLoanDTO(loanService.getAllOverdueBookLoans(employeeMapper.mapToStringId(authorizationIdDTO)));
    }

    @PostMapping(path = "/overview", consumes = "application/json", produces = "application/json", params = {"bookId"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getLendingHistoryOfBook(@RequestParam String bookId, @RequestBody AuthorizationIdDTO authorizationIdDTO) {
        return loanMapper.listBookLoanToListBookLoanDTO(loanService.getLoansOfBook(bookId, employeeMapper.mapToStringId(authorizationIdDTO)));
    }
}
