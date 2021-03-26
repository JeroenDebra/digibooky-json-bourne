package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.book.DetailedBookDTO;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.service.LoanService;
import org.springframework.stereotype.Component;

@Component
public class DetailedBookMapper {

    private final LoanService loanService;

    public DetailedBookMapper(LoanService loanService) {
        this.loanService = loanService;
    }

    public DetailedBookDTO BookToDetailedDTO(Book book) {
        DetailedBookDTO detailedBookDTO = new DetailedBookDTO();
        detailedBookDTO.setUuid(book.getId())
                       .setIsbn(book.getIsbn())
                       .setTitle(book.getTitle())
                       .setAuthorFirstName(book.getAuthor().getFirstname())
                       .setAuthorLastName(book.getAuthor().getLastname())
                       .setSummary(book.getSummary())
                       .setOnLoan(book.isOnLoan());
        if (book.isOnLoan()) {
            extracted(book, detailedBookDTO);
        }
        return detailedBookDTO;
    }

    private void extracted(Book book, DetailedBookDTO detailedBookDTO) {
        detailedBookDTO.setMemberFirstName(loanService.getMemberThatLoanedABook(book.getId().toString()).get().getPersonalInformation().getFirstName());
        detailedBookDTO.setMemberLastName(loanService.getMemberThatLoanedABook(book.getId().toString()).get().getPersonalInformation().getLastName());
    }
}