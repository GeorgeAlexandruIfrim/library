package com.georgeifrim.library.service;

import com.georgeifrim.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    Book findById(Long id);

    Page<Book> listAllBooks(Pageable pageable);

    List<Book> listBooksByIds(List<Long> ids);

    Book updateBook(Book book);
    void deleteBook(Long id);
    Book patchAuthor(Long id, String author);

    Book patchTitle(Long id, String title);
}
