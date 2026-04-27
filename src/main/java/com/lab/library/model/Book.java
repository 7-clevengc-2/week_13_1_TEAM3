package com.lab.library.model;


import java.util.Objects;

public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private boolean checkedOut;

    public Book(String isbn, String title, String author) {
        this.isbn = requireNonBlank(isbn, "isbn");
        this.title = requireNonBlank(title, "title");
        this.author = requireNonBlank(author, "author");
        this.checkedOut = false;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isCheckedOut() { return checkedOut; }

    public void checkOut() { this.checkedOut = true; }
    public void returnBook() { this.checkedOut = false; }

    @Override
    public String toString() {
        return String.format("Book{isbn='%s', title='%s', author='%s', checkedOut=%s}",
                isbn, title, author, checkedOut);
    }

    private static String requireNonBlank(String value, String fieldName) {
        String nonNull = Objects.requireNonNull(value, fieldName + " cannot be null");
        if (nonNull.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return nonNull;
    }
}
