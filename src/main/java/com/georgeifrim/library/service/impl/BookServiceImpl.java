package com.georgeifrim.library.service.impl;

import com.georgeifrim.library.exception.BookNotFoundException;
import com.georgeifrim.library.model.Book;
import com.georgeifrim.library.repository.BookRepository;
import com.georgeifrim.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Page<Book> listAllBooks(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> listBooksByIds(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    @Override
    public Book updateBook(Book book) {
        if (bookRepository.existsById(book.getId())) {
            return saveBook(book);
        }
        throw new BookNotFoundException();
    }
}