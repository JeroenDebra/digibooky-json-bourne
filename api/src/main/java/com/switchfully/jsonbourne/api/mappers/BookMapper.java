package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.dto.book.UpDateBookDTO;
import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.api.dto.book.CreateBookDTO;
import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.service.bookservice.DefaultBookService;
import com.switchfully.jsonbourne.service.bookservice.BookService;
import com.switchfully.jsonbourne.service.bookservice.DefaultBookService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final DefaultBookService defaultBookService;

    public BookMapper(DefaultBookService defaultBookService) {
        this.defaultBookService = defaultBookService;
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

    public Book updateBookDTOToBook(UpDateBookDTO upDateBookDTO){
        return new Book("lalala", upDateBookDTO.getTitle(), new Author(upDateBookDTO.getAuthorFirstName(), upDateBookDTO.getAuthorLastName()), upDateBookDTO.getSummary());
    }

    public BookDTO createBook(String librarianId, CreateBookDTO createBookDTO) {
        return bookToDTO(defaultBookService.createBook(librarianId, new Book(createBookDTO.getIsbn(),createBookDTO.getTitle(),new Author(createBookDTO.getAuthorFirstName(),createBookDTO.getAuthorLastName()),createBookDTO.getSummary())));
    }
}
