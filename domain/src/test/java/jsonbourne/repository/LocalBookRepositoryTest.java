package jsonbourne.repository;

import com.switchfully.jsonbourne.repository.LocalBookRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class LocalBookRepositoryTest {

    @Test
    void getAllBooksGivesACollectionOfBooks() {
        LocalBookRepository repository = new LocalBookRepository();
        assertTrue(repository.getAllBooks() instanceof Collection);
    }
}