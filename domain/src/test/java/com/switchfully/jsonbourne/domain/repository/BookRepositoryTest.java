package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.models.book.Author;
import com.switchfully.jsonbourne.domain.models.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private BookRepository bookRepository;

    @BeforeEach
    void init(){
        bookRepository = new BookRepository();
    }

    @Test
    void addbookAdds1BookToBookCollection(){
        int bookRepositorySizeBeforeAdd = bookRepository.getAllBooks().size();
        bookRepository.addBook(new Book("978-3-16-148410-0","testbook",new Author("john","locke"),"testbook summary"));

        assertEquals(bookRepositorySizeBeforeAdd+1,
                bookRepository.getAllBooks().size());
    }

    @Test
    void getAllBooksGivesCollectionofBooks(){
            assertThat(bookRepository.getAllBooks() instanceof Collection);
    }

    @Test
    void getBooksByISBNGives1BooksWhichContainsIsbn(){
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);

        assertThat(bookRepository.getBooksByISBN("978-3-16-148410-0").contains(testBook)).isTrue();
    }


    @Test
    void getBooksByISBNGives1BooksWhichContainsPartOfIsbn(){
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        assertThat(bookRepository.getBooksByISBN("978").contains(testBook)).isTrue();
    }

    @Test
    void getBooksByISBNGivesAllBooksWhichContainPartOfISBN(){
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        Book testBook2 = new Book("978-3-598-21500-1", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.addBook(testBook2);
        assertThat(bookRepository.getBooksByISBN("978-3").contains(testBook)).isTrue();
        assertThat(bookRepository.getBooksByISBN("978-3").contains(testBook2)).isTrue();
    }

    @Test
    void getBooksByIDGivesBooksWhichSameId(){
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);

        assertThat(bookRepository.getBookByID(testBook.getId().toString()).get()).isEqualTo(testBook);
    }

    @Test
    void getBooksByAuthorGivesAllBooksWhichContainAuthor(){
        Author testAuthor = new Author("john", "locke");
        Book testBook = new Book("978-3-16-148410-0", "testbook", testAuthor, "testbook summary");
        Book testBook2 = new Book("978-3-598-21500-1", "testbook", testAuthor, "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.addBook(testBook2);
        assertThat(bookRepository.getBookByAuthor(testAuthor.getFullname()).contains(testBook)).isTrue();
        assertThat(bookRepository.getBookByAuthor(testAuthor.getFullname()).contains(testBook2)).isTrue();
    }

    @Test
    void getBooksByAuthorGivesAllBooksWhichContainPartOfAuthorName(){
        Author testAuthor = new Author("john", "locke");

        Book testBook = new Book("978-3-16-148410-0", "testbook", testAuthor, "testbook summary");
        Book testBook2 = new Book("978-3-598-21500-1", "testbook", testAuthor, "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.addBook(testBook2);
        assertThat(bookRepository.getBookByAuthor(testAuthor.getFirstname()).contains(testBook)).isTrue();
        assertThat(bookRepository.getBookByAuthor(testAuthor.getLastname()).contains(testBook2)).isTrue();
    }

    @Test
    void getBooksByTitleGivesAllBooksWhichContainTitle(){

        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        Book testBook2 = new Book("978-3-598-21500-1", "test2book", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.addBook(testBook2);
        assertThat(bookRepository.getBooksByTitle("testbook").contains(testBook)).isTrue();
        assertThat(bookRepository.getBooksByTitle("test2book").contains(testBook2)).isTrue();
    }


    @Test
    void getBooksByTitleGivesAllBooksWhichContainPartOfTitle(){
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        Book testBook2 = new Book("978-3-598-21500-1", "testbook2", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.addBook(testBook2);
        assertThat(bookRepository.getBooksByTitle("tes").contains(testBook)).isTrue();
        assertThat(bookRepository.getBooksByTitle("k2").contains(testBook2)).isTrue();
    }

    @Test
    void getBooksByTitle_doestGiveBooksThatNoNotContainsString(){
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        Book testBook2 = new Book("978-3-598-21500-1", "test2book", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        assertThat(!bookRepository.getBooksByTitle("zzzzz").contains(testBook)).isTrue();
        assertThat(!bookRepository.getBooksByTitle("yyyyy").contains(testBook2)).isTrue();
    }

    @Test
    void setBookToDeletedSetbookToDeteled() {
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.setBookToDeleted(testBook);

        assertThat(testBook.isDeleted()).isTrue();
    }

    @Test
    void setBookToDeletedBookDoesNotSHowUpInGetAllBookList() {
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.setBookToDeleted(testBook);

        assertThat(bookRepository.getAllBooks().contains(testBook)).isFalse();
    }

    @Test
    void restoreBookSetDeletedBookIsDeletedToFalse() {
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);
        bookRepository.setBookToDeleted(testBook);
        bookRepository.restoreDeletedBook(testBook);

        assertThat(testBook.isDeleted()).isFalse();
    }

    @Test
    void getBookByISBNAndIsNotLoanedDoesNotShowLoanedBook() {
        Book testBook = new Book("978-3-16-148410-0", "testbook", new Author("john", "locke"), "testbook summary");
        bookRepository.addBook(testBook);

        assertThat(bookRepository.getBookByISBNAndIsNotLoaned("978-3-16-148410-0").isPresent()).isTrue();
        testBook.setOnLoan();
        assertThat(bookRepository.getBookByISBNAndIsNotLoaned("978-3-16-148410-0").isPresent()).isFalse();
    }
}