package com.project.blog.dto;

import lombok.Data;

@Data
public class CreateBlog {
    private String title;
    private String content;
    private String image;
    public CreateBlog(){}

    public CreateBlog(String title,String content){
        this.title=title;
        this.content=content;
    }
    public CreateBlog(String title,String content,String image){
        this.title=title;
        this.content=content;
        this.image=image;
    }
}
