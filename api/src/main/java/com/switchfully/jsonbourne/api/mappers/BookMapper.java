package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.dto.book.CreateBookDTO;
import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.service.bookservice.BookService;
import com.switchfully.jsonbourne.service.bookservice.DefaultBookService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final DefaultBookService service;

    public BookMapper(DefaultBookService service) {
        this.service = service;
    }

    public BookDTO bookToDTO(Book book){
        return new BookDTO()
                .setUuid(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthorFirstName(book.getAuthor().getFirstname())
                .setAuthorLastName(book.getAuthor().getLastname())
                .setSummary(book.getSummary());
    }

    public List<BookDTO> listBookToListDTO (Collection<Book> listOfBooks) {
        return listOfBooks.stream().map(this::bookToDTO).collect(Collectors.toList());
    }

    public BookDTO createBook(CreateBookDTO createBookDTO) {
        return bookToDTO(service.createBook(new Book(createBookDTO.getIsbn(),createBookDTO.getTitle(),new Author(createBookDTO.getAuthorFirstName(),createBookDTO.getAuthorLastName()),createBookDTO.getSummary())));
    }
}
