package com.project.blog.exceptions;

public class UserDoesNotExistsException extends CustomException {
    public UserDoesNotExistsException(String message) {
        super(message,404);
    }
}

