package com.project.blog.exceptions;

public class IncorrectPasswordException extends CustomException {
    public IncorrectPasswordException(String message) {
        super(message,401);
    }
}
