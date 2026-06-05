package com.project.blog.repository;

import com.project.blog.dto.BlogResponse;
import com.project.blog.dto.UpdateBlog;
import com.project.blog.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blogs, Long> {

    @Query("""
    SELECT new com.project.blog.dto.BlogResponse(
        b.blogPid,
        u.name,
        b.content,
        b.title,
        b.image,
        b.createdAt,
        b.authorXid
    )
    FROM Blogs b, Users u
    WHERE b.authorXid = u.pid
      AND b.blogPid = :blogPid
      AND b.isDeleted=false
""")
    BlogResponse getBlogByBlogPid(@Param("blogPid") String blogPid);

    @Query("""
        SELECT new com.project.blog.dto.BlogResponse(
            b.blogPid,
            u.name,
            b.content,
            b.title,
            b.image,
            b.createdAt
        )
        FROM Blogs b, Users u
        WHERE b.authorXid = u.pid 
        AND b.isDeleted=false
    """)
    List<BlogResponse> getAllBlogs();

    @Query("""
    SELECT new com.project.blog.dto.BlogResponse(
        b.blogPid,
        u.name,
        b.content,
        b.title,
        b.image,
        b.createdAt
    )
    FROM Blogs b
    JOIN Users u ON b.authorXid = u.pid
    WHERE b.authorXid = :author
      AND b.isDeleted = false
""")
    List<BlogResponse> getAllBlogsOfUser(@Param("author") String author);

    @Modifying
    @Transactional
    @Query("""
    UPDATE Blogs b
    SET b.title = :title,
        b.content = :content,
        b.image = :image
    WHERE b.blogPid = :blogPid
""")
    int updateBlogById(
            @Param("blogPid") String blogPid,
            @Param("title") String title,
            @Param("content") String content,
            @Param("image") String image
    );

    @Modifying
    @Transactional
    @Query("""
    UPDATE Blogs b
    SET b.title = :title,
        b.content = :content
    WHERE b.blogPid = :blogPid
""")
    int updateBlogByIdAndPrevImage(
            @Param("blogPid") String blogPid,
            @Param("title") String title,
            @Param("content") String content
    );

    @Modifying
    @Transactional
    @Query("""
    UPDATE Blogs b
    SET b.isDeleted = true
    WHERE b.blogPid IN :blogPids
""")
    int deleteBlogsByIds(@Param("blogPids") List<String> blogPids);
}

