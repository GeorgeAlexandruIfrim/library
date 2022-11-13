package com.georgeifrim.library.service.impl;

import com.georgeifrim.library.model.Article;
import com.georgeifrim.library.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public Article saveArticle(Article article) {
        return null;
    }

    @Override
    public Article findById(Long id) {
        return null;
    }

    @Override
    public Page<Article> listAllArticles(Pageable pageable) {
        return null;
    }

    @Override
    public List<Article> listArticlesByIds(List<Long> ids) {
        return null;
    }

    @Override
    public Article updateArticle(Article article) {
        return null;
    }

    @Override
    public void deleteArticle(Long id) {

    }

    @Override
    public Article patchAuthor(Long id, String author) {
        return null;
    }

    @Override
    public Article patchTitle(Long id, String title) {
        return null;
    }
}
