package com.georgeifrim.library.controller;

import com.georgeifrim.library.model.Book;
import com.georgeifrim.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("{id}")
    public Book findById(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @GetMapping
    public Page<Book> listAllBooks(@PageableDefault Pageable pageable) {
        return bookService.listAllBooks(pageable);
    }

    @GetMapping("/ids")
    public List<Book> listBooksByIds(@RequestParam List<Long> ids) {
        return bookService.listBooksByIds(ids);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }

    @PatchMapping("/changeAuthor")
    public Book updateAuthor(@RequestBody Book book){
       return bookService.patchAuthor(book.getId(), book.getAuthor());
    }
    @PatchMapping("/changeTitle")
    public Book updateTitle(@RequestBody Book book){
        return bookService.patchTitle(book.getId(), book.getTitle());
    }



}
