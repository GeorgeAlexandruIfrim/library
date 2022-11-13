package com.georgeifrim.library.repository;

import com.georgeifrim.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT count(*) FROM book WHERE author = ?1 AND title = ?2", nativeQuery = true)
    long countBooksByAuthorAndTitle(String author, String title);

    default Book saveIf(Book book) {
        return save(book);
    }


}
