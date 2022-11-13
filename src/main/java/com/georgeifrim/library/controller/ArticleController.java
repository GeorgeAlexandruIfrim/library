package com.georgeifrim.library.controller;

import com.georgeifrim.library.model.Article;
import com.georgeifrim.library.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Article saveArticle(@RequestBody Article article){
       return articleService.saveArticle(article);
    }
}
