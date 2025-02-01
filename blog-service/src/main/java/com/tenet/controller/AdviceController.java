package com.tenet.controller;

import com.tenet.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    private static final Logger LOG = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex) {
        String message = ex.getMessage();
        LOG.error(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse
                .builder()
                .message(message)
                .build());
    }
}
