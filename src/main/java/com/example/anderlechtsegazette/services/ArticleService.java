package com.example.anderlechtsegazette.services;

import com.example.anderlechtsegazette.models.Article;
import com.example.anderlechtsegazette.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getLatestArticles() {
        return articleRepository.findTop10ByOrderByIdDesc();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
