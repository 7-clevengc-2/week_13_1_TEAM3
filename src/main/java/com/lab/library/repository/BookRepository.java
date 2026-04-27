package com.lab.library.repository;

import com.lab.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public void save(Book book) {
        Objects.requireNonNull(book, "book cannot be null");
        if (findByIsbn(book.getIsbn()).isPresent()) {
            throw new IllegalArgumentException("Book with ISBN already exists: " + book.getIsbn());
        }
        books.add(book);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> findByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            return Optional.empty();
        }
        return books.stream()
                .filter(b -> Objects.equals(b.getIsbn(), isbn))
                .findFirst();
    }

    public List<Book> findCheckedOut() {
        return books.stream()
                .filter(Book::isCheckedOut)
                .toList();
    }

    public List<Book> findAvailable() {
        return books.stream()
                .filter(b -> !b.isCheckedOut())
                .toList();
    }

    public void clear() {
        books.clear();
    }
}
