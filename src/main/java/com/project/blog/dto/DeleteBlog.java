package com.project.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteBlog {
    private List<String> blogPids;
}
