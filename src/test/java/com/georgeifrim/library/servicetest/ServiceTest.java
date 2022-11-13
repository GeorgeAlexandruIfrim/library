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

    @DisplayName("Saving a Book in DB if ")
    @Test
    public void saveBookIfNotDuplicate(){
        // Given
        Book saved = new Book("Gigi", "Muschi");
        saved.setId(1L);

        Book toSave = new Book("Gigi", "Muschi");

        when(bookRepository.save(toSave)).thenReturn(saved);
        when(bookRepository.countBooksByAuthorAndTitle("Gigi", "Muschi")).thenReturn(0L);

        // When
        Book actual = bookService.saveBook(toSave);

        //Then
        assertThat(actual).isEqualTo(saved);
    }

    @Test
    public void doNotSaveBookIfDuplicate(){
        // Given
        Book saved = new Book("Gigi", "Muschi");
        saved.setId(1L);

        Book toSave = new Book("Gigi", "Muschi");

        when(bookRepository.save(toSave)).thenReturn(saved);
        when(bookRepository.countBooksByAuthorAndTitle("Gigi", "Muschi")).thenReturn(1L);

        // When
//        try {
//            bookService.saveBook(toSave);
//            fail("should/ve thrown BookNotFoundException");
//        }catch (BookNotFoundException e){
//            assertThat(e.getMessage()).isEqualTo("Cartea deja exista !");
//        }
        assertThatThrownBy(() -> bookService.saveBook(toSave))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Cartea deja exista !");
    }



}
