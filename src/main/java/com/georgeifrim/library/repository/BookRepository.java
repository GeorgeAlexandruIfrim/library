package com.georgeifrim.library.repository;

import com.georgeifrim.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
