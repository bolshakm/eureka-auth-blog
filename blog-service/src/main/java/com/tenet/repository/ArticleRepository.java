package com.tenet.repository;

import com.tenet.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.publishedAt < :date")
    List<Article> findArticlesPublishedBefore(@Param("date") LocalDateTime date);
}