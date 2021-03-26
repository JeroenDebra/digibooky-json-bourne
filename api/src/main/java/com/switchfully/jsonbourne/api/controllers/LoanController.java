package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.bookloan.BookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.CreateBookLoanDTO;
import com.switchfully.jsonbourne.api.mappers.LoanMapper;
import com.switchfully.jsonbourne.service.loanservice.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.theme.AbstractThemeResolver;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final LoanService loanService;
    private final LoanMapper mapper;

    public LoanController(LoanService loanService, LoanMapper mapper) {
        this.loanService = loanService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookLoanDTO lendBook(@RequestBody CreateBookLoanDTO bookLoanDTO){
        return mapper.bookLoanToBookLoanDTO(loanService.addBookLoan(mapper.createBookLoanToBookLoan(bookLoanDTO)));
    }

    @PostMapping(path = "/{memberid}",produces = "application/json",consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getLoansByUser(@RequestBody LoanBookLibarianDTO loanBookLibarianDTO, @PathVariable UUID memberid){
        return mapper.listBookLoanToListBookLoanDTO(loanService.getLoansForUser(loanBookLibarianDTO.getLibarianId().toString(),memberid));
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String returnBook(@RequestBody ReturnBookLoanDTO returnBookLoanDTO){
        logger.info(returnBookLoanDTO.getLoanId() + "is being returned");

        if (mapper.returnBookUpdate(returnBookLoanDTO)) {
            return "Book has been returned too late";
        }
        return "book has been successfully returned";
    }

    @PostMapping(path = "/overdue",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getAllOverdueBookLoans (@RequestBody AuthorizationIdDTO authorizationIdDTO) {
        return mapper.listBookLoanToListBookLoanDTO(loanService.getAllOverdueBookLoans(mapper.mapToStringId(authorizationIdDTO)));
    }
}
