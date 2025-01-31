package com.tenet.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String author;
}
