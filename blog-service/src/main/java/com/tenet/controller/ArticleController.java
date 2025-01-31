package com.tenet.controller;

import com.tenet.dto.request.ArticleRequest;
import com.tenet.dto.response.ArticleResponse;
import com.tenet.mapper.ArticleMapper;
import com.tenet.model.Article;
import com.tenet.service.ArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.tenet.mapper.ArticleMapper.mapArticleRequestToArticle;
import static com.tenet.mapper.ArticleMapper.mapArticleToArticleResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        return articleService.getAllArticles()
                .stream()
                .map(ArticleMapper::mapArticleToArticleResponse)
                .toList();
    }

    @Secured("ADMIN")
    @GetMapping("/{id}")
    public ArticleResponse getArticleById(@PathVariable Long id) {
        return mapArticleToArticleResponse(articleService.getArticleById(id));
    }

    @PostMapping
    public ArticleResponse createArticle(@Valid @RequestBody ArticleRequest article) {
        return mapArticleToArticleResponse(articleService.createArticle(mapArticleRequestToArticle(article)));
    }

    @PutMapping("/{id}")
    public ArticleResponse updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleRequest article) {
        return mapArticleToArticleResponse(articleService.updateArticle(id, mapArticleRequestToArticle(article)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/before/{date}")
    public List<ArticleResponse> getArticlesPublishedBefore(@PathVariable String date) {
        return articleService.getArticlesPublishedBefore(LocalDateTime.parse(date))
                .stream()
                .map(ArticleMapper::mapArticleToArticleResponse)
                .toList();
    }
}
