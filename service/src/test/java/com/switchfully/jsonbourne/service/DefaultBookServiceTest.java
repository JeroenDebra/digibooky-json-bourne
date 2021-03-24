package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.repository.LocalBookRepository;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBookServiceTest {

    @Test
    void getAllBooksGivesACollectionOfBooks() {
        assertTrue(new DefaultBookService(new LocalBookRepository()).getAllBooks() instanceof Collection);
    }
}