package com.project.blog.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String toEmail, String otp) {
        System.out.println("inside mail service");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("piexrxr@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP");
        message.setText(
                "Your OTP for password reset is: " + otp + "\n\n" +
                        "This OTP is valid for 10 minutes.\n\n" +
                        "If you did not request this, ignore this email."
        );

        try{
            System.out.println("b4 sending mail");
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e);
        }
    }
}
