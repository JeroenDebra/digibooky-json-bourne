package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.bookloan.BookLoanDTO;
import com.switchfully.jsonbourne.api.dto.bookloan.CreateBookLoanDTO;
import com.switchfully.jsonbourne.api.mappers.LoanMapper;
import com.switchfully.jsonbourne.service.loanservice.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

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

    @GetMapping(path = "/{memberid}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookLoanDTO> getLoansByUser(@PathVariable UUID memberid){
        //body => lib id,
        return mapper.listBookLoanToListBookLoanDTO(loanService.getLoansForUser(memberid));
    }
}
