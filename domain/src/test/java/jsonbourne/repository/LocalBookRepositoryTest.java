package jsonbourne.repository;

import com.switchfully.jsonbourne.repository.LocalBookRepository;
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
}