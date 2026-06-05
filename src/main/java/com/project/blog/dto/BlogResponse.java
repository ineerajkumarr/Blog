package com.project.blog.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
public class BlogResponse {

    private String blogPid;
    private String name;
    private String content;
    private String title;
    private String image;
    private LocalDateTime createdAt;
    private String authorXid;

    public BlogResponse(String blogPid, String name, String content, String title, String image, LocalDateTime createdAt) {
        this.blogPid = blogPid;
        this.name = name;
        this.content = content;
        this.title = title;
        this.image = image;
        this.createdAt=createdAt;
    }
    public BlogResponse(String blogPid, String name, String content, String title, String image, LocalDateTime createdAt,String authorXid) {
        this.blogPid = blogPid;
        this.name = name;
        this.content = content;
        this.title = title;
        this.image = image;
        this.createdAt=createdAt;
        this.authorXid=authorXid;
    }
}