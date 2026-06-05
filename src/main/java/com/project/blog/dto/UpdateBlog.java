package com.project.blog.dto;

import lombok.Data;

@Data
public class UpdateBlog {
    private String blogPid;
    private String title;
    private String content;

    public UpdateBlog(String blogPid,String title, String content, String image) {
        this.blogPid=blogPid;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    private String image;

}
