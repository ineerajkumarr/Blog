package com.project.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "m_users")
@Data
public class Users {
    @Id
    private String pid;

    private String name;
    private String email;
    private String password;
    public Users() {
    }

    public Users(String pid,String name,String email,String password){
        this.pid=pid;
        this.name=name;
        this.email=email;
        this.password=password;
    }

}
