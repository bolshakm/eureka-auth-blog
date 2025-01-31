package com.tenet.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponse {
    private long articleId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime publishedDate;
}
