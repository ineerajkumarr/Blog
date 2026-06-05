package com.project.blog.dto;

import lombok.Data;

@Data
public class LoginToken {
    private String token;
    private String pid;
    private String name;
    private String email;
    public LoginToken(){};

    public LoginToken(String token,String pid, String name, String email) {
        this.token = token;
        this.pid=pid;
        this.name = name;
        this.email = email;
    }
}
