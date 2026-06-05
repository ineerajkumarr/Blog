package com.project.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_blogs")
@Data
public class Blogs {
    @Id
    @Column(name = "blog_pid")
    private String blogPid;
    @Column(name = "author_xid")
    private String authorXid;
    private String content;
    private String title;
    private String image;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Blogs(String blogPid, String authorXid, String title, String content, String image) {
        this.blogPid = blogPid;
        this.authorXid = authorXid;
        this.content = content;
        this.title = title;
        this.image = image;
        this.isDeleted=false;
    }
    public Blogs(String blogPid, String authorXid, String title, String content) {
        this.blogPid = blogPid;
        this.authorXid = authorXid;
        this.content = content;
        this.title = title;
        this.isDeleted=false;
    }
    public Blogs() {
        // required by JPA
    }

}
