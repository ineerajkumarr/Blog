package com.project.blog.dto;

import com.project.blog.model.Users;
import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    public User() {}

}
