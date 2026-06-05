package com.project.blog.controllers;

import com.project.blog.dto.*;
import com.project.blog.model.Blogs;
import com.project.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService service;

    @GetMapping("/blogs/{blogId}")
    ApiResponse getBlogById(@PathVariable String blogId){
        System.out.println("inside blog contr.");
        System.out.println("blogId "+blogId);
        BlogResponse blog=service.getBlogById(blogId);
        return new ApiResponse(true,"Blog retrieved successful",blog);
    }
    @GetMapping("/blog")
    ApiResponse getBlogsOfUser(Authentication authentication){
        System.out.println("in the controller for getting blogs of user");
        String userId=authentication.getName();
        System.out.println("usedId"+userId);
        List<BlogResponse> blogs=service.getBlogsOfUser(userId);
        return new ApiResponse(true,"Blogs retrieved successfully",blogs);
    }

    @GetMapping("/blogs/all")
    ApiResponse getAllBlogs(){
        List<BlogResponse> blogs=service.getAllBlogs();
        return new ApiResponse(true,"Blog retrieved successful",blogs);
    }
    @PostMapping("/blog/create")
    ApiResponse createBlog(@RequestBody CreateBlog blog, Authentication authentication){
        System.out.println("bolg details in controller : "+blog.getContent());
        String userId=authentication.getName();
        service.createBlog(blog,userId);
        return new ApiResponse(true,"Blog Created successfully");
    }

    @PutMapping("/blog/update")
    ApiResponse updateBlog(@RequestBody UpdateBlog blog,Authentication authentication){
        String authorXid=authentication.getName();
        int res=service.updateBlog(blog,authorXid);
        if(res==0){
            return new ApiResponse(false,"Couldn't update blog");
        }
        return new ApiResponse(true,"Blog updated successfully");
    }

    @DeleteMapping("/blog/delete")
    ApiResponse deleteBlog(@RequestBody DeleteBlog blogs){
        int res=service.deleteBlog(blogs);
        if(res==0){
            return new ApiResponse(false,"Couldn't Delete blog");
        }
        return new ApiResponse(true,"Blog Deleted successfully");
    }
}
