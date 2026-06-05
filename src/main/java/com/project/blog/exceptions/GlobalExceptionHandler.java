package com.project.blog.exceptions;

import com.project.blog.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ApiResponse(false, ex.getMessage()));
    }
}