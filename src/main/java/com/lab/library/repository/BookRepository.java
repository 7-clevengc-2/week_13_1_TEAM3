package com.lab.library.repository;

import com.lab.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public void save(Book book) {
        if (findByIsbn(book.getIsbn()).isPresent()) {
            // Optionally, throw an exception or just return
            return;
        }
        books.add(book);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
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
