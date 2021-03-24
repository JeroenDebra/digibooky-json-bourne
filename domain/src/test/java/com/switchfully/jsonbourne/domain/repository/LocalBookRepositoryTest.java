package com.switchfully.jsonbourne.domain.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class LocalBookRepositoryTest {
    private LocalBookRepository localBookRepository;

    @BeforeEach
    void init(){
        localBookRepository = new LocalBookRepository();
    }

    @Test
    void getAllBooksGivesACollectionOfBooks() {
        assertTrue(localBookRepository.getAllBooks() instanceof Collection);
    }

    @Test
    void findByISBN() {
        assertEquals(("9789024564460"),localBookRepository.getBookByISBN("9789024564460").get().getIsbn());
    }

    @Test
    void getBookByISBNFakeIsbnGivesEmptyOptional() {
        assertTrue(localBookRepository.getBookByISBN("fakeisbnNumber").isEmpty());
    }

    @Test
    void getBookByAuthorGivesCorrectBooksIfFullname(){
        assertEquals(1,localBookRepository.getBookByAuthor("firstname lastname").size());
    }

    @Test
    void getBookByAuthorGivesCorrectBooksIfLastname(){
        assertEquals(1,localBookRepository.getBookByAuthor("lastname").size());
    }

    @Test
    void getBookByAuthorGivesCorrectBooksIfFirstname(){
        assertEquals(1,localBookRepository.getBookByAuthor("firstname").size());
    }

    @Test
    void getBookByAuthorGivesEmptyListIfAuthorIsNotFound(){
        assertEquals(0,localBookRepository.getBookByAuthor("fakename fakelastname").size());
    }
}