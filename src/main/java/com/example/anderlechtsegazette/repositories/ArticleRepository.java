package com.example.anderlechtsegazette.repositories;

import com.example.anderlechtsegazette.models.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findTop10ByOrderByIdDesc();
}
