package com.tenet.mapper;

import com.tenet.dto.request.ArticleRequest;
import com.tenet.dto.response.ArticleResponse;
import com.tenet.model.Article;

import java.time.LocalDateTime;

public class ArticleMapper {
    public static ArticleResponse mapArticleToArticleResponse(Article article) {
        ArticleResponse response = new ArticleResponse();
        response.setArticleId(article.getId());
        response.setContent(article.getContent());
        response.setTitle(article.getTitle());
        response.setAuthor(article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName());
        response.setPublishedDate(article.getPublishedAt());
        return response;
    }

    public static Article mapArticleRequestToArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setContent(articleRequest.getContent());
        article.setTitle(articleRequest.getTitle());
        article.setPublishedAt(LocalDateTime.now());
        return article;
    }
}
