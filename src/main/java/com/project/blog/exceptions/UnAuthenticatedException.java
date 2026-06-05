package com.project.blog.exceptions;

public class UnAuthenticatedException extends CustomException{
    public UnAuthenticatedException(String message) {
        super(message,401);
    }
}
