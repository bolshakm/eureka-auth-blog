package com.tenet.service;

import com.tenet.exceptions.ArticleNotFoundException;
import com.tenet.model.Article;
import com.tenet.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + id));
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Article article = getArticleById(id);
        article.setTitle(updatedArticle.getTitle());
        article.setContent(updatedArticle.getContent());
        article.setAuthor(updatedArticle.getAuthor());
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ArticleNotFoundException("Article not found with id: " + id);
        }
        articleRepository.deleteById(id);
    }

    public List<Article> getArticlesPublishedBefore(LocalDateTime date) {
        return articleRepository.findArticlesPublishedBefore(date);
    }
}
