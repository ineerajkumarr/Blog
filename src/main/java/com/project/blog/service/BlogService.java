package com.project.blog.service;

import com.project.blog.dto.CreateBlog;
import com.project.blog.dto.BlogResponse;
import com.project.blog.dto.DeleteBlog;
import com.project.blog.dto.UpdateBlog;
import com.project.blog.exceptions.CustomException;
import com.project.blog.model.Blogs;
import com.project.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    private BlogRepository repository;

    public BlogResponse getBlogById(String blogId){
        return repository.getBlogByBlogPid(blogId);
    }

    public void createBlog(CreateBlog blog,String userId){
        System.out.println("blog details service "+blog.getTitle()+" "+blog.getContent());
        repository.save(convertBlogDTO(blog,userId));
    }
    public Blogs convertBlogDTO(CreateBlog blogInput,String userId){
        String pid=UUID.randomUUID().toString();
        if(blogInput.getImage()!=null){
            System.out.println("blog details convertor "+blogInput.getTitle()+" "+blogInput.getContent());
            return new Blogs(pid,userId,blogInput.getTitle(),blogInput.getContent(),blogInput.getImage());
        }
        System.out.println("blog details convertor "+blogInput.getTitle()+" "+blogInput.getContent());
        return new Blogs(pid,userId,blogInput.getTitle(),blogInput.getContent());
    }

    public List<BlogResponse> getAllBlogs() {
        return repository.getAllBlogs();
    }

    public List<BlogResponse> getBlogsOfUser(String userId) {
        System.out.println("in the service");
        return repository.getAllBlogsOfUser(userId);
    }


    public int updateBlog(UpdateBlog blog, String authorXid) {
        try {
            BlogResponse fetchedBlog = getBlogById(blog.getBlogPid());

            if (fetchedBlog == null) {
                throw new CustomException("Blog not found", 404);
            }

            if (!authorXid.equals(fetchedBlog.getAuthorXid())) {
                throw new CustomException("Unauthorized !! You aren't the author of this blog.", 403);
            }

            if (blog.getImage() != null) {
                return repository.updateBlogById(
                        blog.getBlogPid(),
                        blog.getTitle(),
                        blog.getContent(),
                        blog.getImage()
                );
            }

            return repository.updateBlogByIdAndPrevImage(
                    blog.getBlogPid(),
                    blog.getTitle(),
                    blog.getContent()
            );

        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), 500);
        }
    }

    public int deleteBlog(DeleteBlog blogs){
        int res;
        try{
            res=repository.deleteBlogsByIds(blogs.getBlogPids());
        } catch (Exception e) {
            throw new CustomException(e.getMessage(),500);
        }
        return res;
    }
}
