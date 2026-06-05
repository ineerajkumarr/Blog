package com.project.blog.exceptions;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException(String message) {
        super(message,401);
    }
}
