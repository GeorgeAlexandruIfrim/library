package com.georgeifrim.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book with id:" + id +" was not found!");
    }
    public BookNotFoundException(String msg) {
        super(msg);
    }
}
