package com.project.blog.repository;

import com.project.blog.model.OTP;
import com.project.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<OTP, Long> {
    OTP findTopByEmailOrderByCreatedAtDesc(String email);
}
