package com.lab.library.service;

import com.lab.library.model.Book;
import com.lab.library.repository.BookRepository;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

   
    public void addBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        repository.save(book);
    }

  
    public boolean checkOut(String isbn) {
        Optional<Book> found = repository.findByIsbn(isbn);
        if (found.isEmpty()) {
            return false;
        }
        Book book = found.get();
        if (book.isCheckedOut()) {
            return false;
        }
        book.checkOut();
        return true;
    }

  
    public boolean returnBook(String isbn) {
        Optional<Book> found = repository.findByIsbn(isbn);
        if (found.isEmpty()) {
            return false;
        }
        Book book = found.get();
        if (!book.isCheckedOut()) {
            return false;
        }
        book.returnBook();
        return true;
    }

  
    public List<Book> searchByTitle(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        String normalizedQuery = query.toLowerCase(Locale.ROOT);
        return repository.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase(Locale.ROOT).contains(normalizedQuery))
                .toList();
    }


    public List<Book> getCheckedOutBooks() {
        return repository.findCheckedOut();
    }

 
    public List<Book> getAvailableBooks() {
        return repository.findAvailable();
    }
}
