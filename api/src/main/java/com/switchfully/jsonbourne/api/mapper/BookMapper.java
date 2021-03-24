package com.switchfully.jsonbourne.api.mapper;

import com.switchfully.jsonbourne.api.dto.BookDTO;
import com.switchfully.jsonbourne.domain.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDTO bookToDTO(Book book){
        return new BookDTO()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthorFirstName(book.getAuthor().getFirstname())
                .setAuthorLastName(book.getAuthor().getLastname());
    }

    public List<BookDTO> listBookToListDTO (Collection<Book> listOfBooks) {
        return listOfBooks.stream().map(this::bookToDTO).collect(Collectors.toList());
    }

    public BookDTO bookToBookDTOWithSummary(Book book){
        return new BookDTO()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthorFirstName(book.getAuthor().getFirstname())
                .setAuthorLastName(book.getAuthor().getLastname())
                .setSummary(book.getSummary());
    }

}
