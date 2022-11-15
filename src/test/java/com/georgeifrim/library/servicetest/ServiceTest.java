package com.georgeifrim.library.servicetest;

import com.georgeifrim.library.exception.BookNotFoundException;
import com.georgeifrim.library.model.Book;
import com.georgeifrim.library.repository.BookRepository;
import com.georgeifrim.library.service.BookService;
import com.georgeifrim.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

//@SpringBootTest
public class ServiceTest {

    private BookService bookService;
//    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setup(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @DisplayName("Saving a Book that is NOT already in DB")
    @Test
    public void saveBookIfNotDuplicate() {
            // Given
            Book saved = new Book("igi", "uschi");
            saved.setId(1L);
            Book toSave = new Book("Gig", "Musch");
            // When
            when(bookRepository.save(toSave)).thenReturn(saved);
            when(bookRepository.countBooksByAuthorAndTitle(toSave.getAuthor(), toSave.getTitle())).thenReturn(0L);
            //Then
            Book actual = bookService.saveBook(toSave);
            assertThat(actual).isEqualTo(saved);
        }
    @DisplayName("Saving a Book that IS already in DB")
    @Test
    public void doNotSaveBookIfDuplicate() {
        // Given
        Book saved = new Book("Gigi", "Muschi");
        saved.setId(1L);
        Book toSave = new Book("Gigi", "Muschi");
        // When
        when(bookRepository.save(toSave)).thenReturn(saved);
        when(bookRepository.countBooksByAuthorAndTitle("Gigi", "Muschi")).thenReturn(1L);
        //Then
        assertThatThrownBy(() -> bookService.saveBook(toSave))
                    .isInstanceOf(BookNotFoundException.class)
                    .hasMessage("Cartea deja exista !");
    }
    @DisplayName("Finding a Book by ID that already exists in DB")
    @Test
    public void findBookWithExistingId(){
        //Given
        Book toFind = new Book(1L, "Author", "Title");
        Optional<Book> opt = Optional.of(toFind);
        //When
        when(bookRepository.findById(toFind.getId())).thenReturn(opt);
        //Then
        assertThat(bookService.findById(toFind.getId())).isEqualTo(opt.get());
    }
    @DisplayName("Finding a Book by ID that does NOT exist in DB")
    @Test
    public void findBookWithNonExistingId(){
        //Given
        Book toFind = new Book(1L, "Author", "Title");
        //When
        when(bookRepository.findById(toFind.getId())).thenThrow(new BookNotFoundException(toFind.getId()));
        //Then
        assertThatThrownBy(() -> bookService.findById(toFind.getId()))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book with id:" + toFind.getId() +" was not found!");
    }

    @Test
    public void updateBookWithExistingId(){
        //Given
        Book toFind = new Book(1L, "Author", "Title");
        //When
        when(bookRepository.findById(toFind.getId())).thenReturn(Optional.of(toFind));

    }




}
