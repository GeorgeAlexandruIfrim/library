package com.georgeifrim.library.repository;

import com.georgeifrim.library.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
