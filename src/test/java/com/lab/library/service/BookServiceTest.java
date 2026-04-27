package com.lab.library.service;

import com.lab.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BookServiceTest {

    private BookRepository repo;
    private BookService service;

    @BeforeEach
    void setUp() {
        repo = new BookRepository();
        service = new BookService(repo);
        service.addBook("978-1", "Effective Java", "Bloch");
        service.addBook("978-2", "Clean Code", "Martin");
        service.addBook("978-3", "Design Patterns", "GoF");
    }

    @Test
    void checkOut_availableBook_returnsTrue() {
        assertTrue(service.checkOut("978-1"));
    }

    @Test
    void checkOut_alreadyCheckedOut_returnsFalse() {
        service.checkOut("978-1");
        assertFalse(service.checkOut("978-1"));
    }

    @Test
    void returnBook_checkedOutBook_returnsTrue() {
        service.checkOut("978-2");
        assertTrue(service.returnBook("978-2"));
    }

    @Test
    void searchByTitle_matchingQuery_returnsResults() {
        List<?> results = service.searchByTitle("clean");
        assertEquals(1, results.size());
    }

    @Test
    void searchByTitle_noMatch_returnsEmpty() {
        List<?> results = service.searchByTitle("nonexistent");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchByTitle_nullQuery_returnsEmpty() {
        List<?> results = service.searchByTitle(null);
        assertEquals(0, results.size());
    }

    @Test
    void searchByTitle_blankQuery_returnsEmpty() {
        List<?> results = service.searchByTitle("   ");
        assertEquals(0, results.size());
    }

    @Test
    void checkOut_unknownIsbn_returnsFalse() {
        assertFalse(service.checkOut("missing-isbn"));
    }

    @Test
    void returnBook_unknownIsbn_returnsFalse() {
        assertFalse(service.returnBook("missing-isbn"));
    }

    @Test
    void addBook_duplicateIsbn_throwsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.addBook("978-1", "Other Title", "Other Author")
        );
        assertTrue(exception.getMessage().contains("ISBN"));
    }

}
