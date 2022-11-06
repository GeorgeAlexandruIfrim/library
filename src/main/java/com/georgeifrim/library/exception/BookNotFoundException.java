package com.georgeifrim.library.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super("Book not found!");
    }
}
