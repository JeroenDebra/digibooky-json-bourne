package com.switchfully.jsonbourne.repository;

import com.switchfully.jsonbourne.domain.Author;
import com.switchfully.jsonbourne.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class LocalBookRepository implements BookRepository {

    private final Set<Book> books = new HashSet<>();

    public LocalBookRepository() {
        fillInList();
    }

    @Override
    public Collection<Book> getAllBooks() {
        return books;
    }

    private void fillInList(){
        books.add(new Book("9789024564460","title",new Author("firstname","lastname"),"this is the story ....."));
    }

    @Override
    public Optional<Book> getBookByISBN(String isbn){
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
    }

    @Override
    public Optional<Book> getBookByID(String id) {
        return books.stream().filter(book -> book.getId().toString().equals(id)).findFirst();
    }
}
