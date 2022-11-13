package com.georgeifrim.library.service;

import com.georgeifrim.library.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    Article saveArticle(Article article);
    Article findById(Long id);
    Page<Article> listAllArticles(Pageable pageable);
    List<Article> listArticlesByIds(List<Long> ids);
    Article updateArticle(Article article);
    void deleteArticle(Long id);
    Article patchAuthor(Long id, String author);
    Article patchTitle(Long id, String title);
}
