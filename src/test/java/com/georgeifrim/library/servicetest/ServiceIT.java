package com.georgeifrim.library.servicetest;

import com.georgeifrim.library.model.Book;
import com.georgeifrim.library.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceIT {

    @Autowired
    private BookService bookService;

    @Test
    public void saveBookIfNotDuplicate(){
        // Given
        Book saved = new Book("Gigi", "Muschi");
        saved.setId(1L);

        Book toSave = new Book("Gigi3", "Muschi");

        // When
        Book actual = bookService.saveBook(toSave);

        //Then
//        assertThat(actual).isEqualTo(saved);
    }
}
