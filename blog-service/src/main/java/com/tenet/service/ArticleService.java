package com.tenet.service;

import com.tenet.exceptions.ArticleNotBelongException;
import com.tenet.exceptions.ArticleNotFoundException;
import com.tenet.model.Article;
import com.tenet.model.Role;
import com.tenet.model.User;
import com.tenet.repository.ArticleRepository;
import com.tenet.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id, User user) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + id));
        if (!Role.ADMIN.equals(user.getRole()) && !Objects.equals(user, article.getAuthor())) {
            throw new ArticleNotBelongException("Article not belong to user");
        }
        return article;
    }

    public Article createArticle(Article article, User user) {
        article.setAuthor(user);
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article updatedArticle, User user) {
        Article article = getArticleById(id, user);
        article.setTitle(updatedArticle.getTitle());
        article.setContent(updatedArticle.getContent());
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
