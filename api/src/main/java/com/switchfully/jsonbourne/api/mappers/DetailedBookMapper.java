package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.book.DetailedBookDTO;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.service.BookService;
import com.switchfully.jsonbourne.service.LoanService;
import org.springframework.stereotype.Component;

@Component
public class DetailedBookMapper {

    private final BookService bookService;
    private final LoanService loanService;

    public DetailedBookMapper(BookService bookService, LoanService loanService) {
        this.bookService = bookService;
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
        /*if (book.isOnLoan()) {
            detailedBookDTO.setMemberFirstName(loanService.getMemberThatLoanedABook(book.getId().toString()).get().getPersonalInformation().getFirstName())
                    .setMemberLastName(loanService.getMemberThatLoanedABook(book.getId().toString()).get().getPersonalInformation().getLastName());
        } else { */
            detailedBookDTO.setMemberFirstName("");
            detailedBookDTO.setMemberLastName("");
     /*   } */
        return detailedBookDTO;
    }
}