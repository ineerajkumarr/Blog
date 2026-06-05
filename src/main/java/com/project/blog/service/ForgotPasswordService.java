package com.project.blog.service;

import com.project.blog.exceptions.UserDoesNotExistsException;
import com.project.blog.model.OTP;
import com.project.blog.repository.OtpRepository;
import com.project.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ForgotPasswordService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public void sendForgotPasswordOtp(String email) {
        System.out.println("inside 3rd exten service");

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        OTP otpEntity = new OTP();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        otpEntity.setVerified(false);

        System.out.println("b4 saving otp");
        otpRepository.save(otpEntity);
        System.out.println("after saving otp");

        try {
            System.out.println("sending mail..");
            emailService.sendOtpEmail(email, otp);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}