package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.book.BookDTO;
import com.switchfully.jsonbourne.api.dto.book.UpDateBookDTO;
import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.api.dto.book.CreateBookDTO;
import com.switchfully.jsonbourne.domain.models.book.Book;
import com.switchfully.jsonbourne.service.BookService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final BookService bookService;

    public BookMapper(BookService bookService) {
        this.bookService = bookService;
    }

    public BookDTO bookToDTO(Book book){
        return new BookDTO()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthorFirstName(book.getAuthor().getFirstname())
                .setAuthorLastName(book.getAuthor().getLastname())
                .setSummary(book.getSummary());
    }

    public List<BookDTO> listBookToListDTO(Collection<Book> listOfBooks) {
        return listOfBooks.stream().map(this::bookToDTO).collect(Collectors.toList());
    }

    private BookDTO bookToBookDTOWithoutSummary(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthorFirstName(book.getAuthor().getFirstname())
                .setAuthorLastName(book.getAuthor().getLastname());
    }

    public List<BookDTO> listBookToListDTOWithoutSummary(Collection<Book> listofBooks) {
        return listofBooks.stream().map(this::bookToBookDTOWithoutSummary).collect(Collectors.toList());
    }

    public Book updateBookDTOToBook(Book bookById,UpDateBookDTO upDateBookDTO){
        bookById.setTitle(upDateBookDTO.getTitle())
        .setAuthor(new Author(upDateBookDTO.getAuthorFirstName(), upDateBookDTO.getAuthorLastName()))
        .setSummary(upDateBookDTO.getSummary());
        return bookById;
    }

    public BookDTO createBook(String librarianId, CreateBookDTO createBookDTO) {
        return bookToDTO(bookService.createBook(librarianId, new Book(createBookDTO.getIsbn(),createBookDTO.getTitle(),new Author(createBookDTO.getAuthorFirstName(),createBookDTO.getAuthorLastName()),createBookDTO.getSummary())));
    }
}
