package com.project.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_password_reset_otp")
@Data
public class OTP {
    @Id
    private long id;

    private String email;
    private String otp;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    private boolean verified;

    @Column(name = "created_at",insertable = false,updatable = false)
    private LocalDateTime createdAt;

    public OTP(){};

    public OTP(String email, String otp, LocalDateTime expiresAt, boolean verified) {
        this.email = email;
        this.otp = otp;
        this.expiresAt = expiresAt;
        this.verified = verified;
    }
}
