package com.tenet.exceptions;

public class ArticleNotBelongException extends RuntimeException {
    public ArticleNotBelongException(String message) {
        super(message);
    }
}
