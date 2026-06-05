package com.project.blog.repository;

import com.project.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    @Modifying
    @Transactional
    @Query("""
    UPDATE Users u
    SET u.password=:password
    WHERE u.email = :email
""")
    int updatePasswordByEmail(@Param("email") String email,@Param("password") String password);
}
