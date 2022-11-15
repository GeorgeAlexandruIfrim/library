package com.georgeifrim.library.service.impl;

import com.georgeifrim.library.exception.BookNotFoundException;
import com.georgeifrim.library.model.Book;
import com.georgeifrim.library.repository.BookRepository;
import com.georgeifrim.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Book saveBook(Book book) {
        long count = bookRepository.countBooksByAuthorAndTitle(book.getAuthor(), book.getTitle());
        if(count != 0){
            throw new BookNotFoundException("Cartea deja exista !");
        }
        return bookRepository.save(book);

    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
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
        }else{
            throw new BookNotFoundException(book.getId());
        }
    }

    @Override
    public void deleteBook(Long id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }else{
            throw new BookNotFoundException(id);
        }

    }

    @Override
    public Book patchAuthor(Long id, String author) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if(existingBook.isEmpty()){
            throw new BookNotFoundException(id);
        }else{
            Book returnedBook = existingBook.get();
            returnedBook.setAuthor(author);
            return returnedBook;
        }
    }
    @Override
    public Book patchTitle(Long id, String title) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if(existingBook.isEmpty()){
            throw new BookNotFoundException(id);
        }else{
            Book returnedBook = existingBook.get();
            returnedBook.setTitle(title);
            return returnedBook;
        }
    }
}