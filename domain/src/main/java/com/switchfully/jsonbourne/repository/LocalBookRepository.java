package com.switchfully.jsonbourne.repository;

import com.switchfully.jsonbourne.domain.Book;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class LocalBookRepository implements BookRepository {

    private final Set<Book> books = new HashSet<>();

    public LocalBookRepository() {}

    @Override
    public Collection<Book> getAllBooks() {
        return books;
    }
}
