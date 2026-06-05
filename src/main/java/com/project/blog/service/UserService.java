package com.project.blog.service;

import com.project.blog.dto.LoginToken;
import com.project.blog.dto.NewPasswordDTO;
import com.project.blog.dto.OtpDTO;
import com.project.blog.dto.RegisterUser;
import com.project.blog.exceptions.CustomException;
import com.project.blog.exceptions.IncorrectPasswordException;
import com.project.blog.exceptions.UserAlreadyExistsException;
import com.project.blog.exceptions.UserDoesNotExistsException;
import com.project.blog.model.OTP;
import com.project.blog.model.Users;
import com.project.blog.repository.OtpRepository;
import com.project.blog.repository.UserRepository;
import com.project.blog.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ForgotPasswordService forgetPasswordService;
    @Autowired
    private OtpRepository otpRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Users findUser(String email){
        Users currentUser=repository.findByEmail(email);
        if(currentUser==null){
            throw new UserDoesNotExistsException("User with this email doesn't exists.");
        }
        System.out.println("currUser "+currentUser.getName());
        return currentUser;
    }

    public LoginToken login(String email, String password) {
        Users currentUser=repository.findByEmail(email);
        if(currentUser==null){
            throw new UserDoesNotExistsException("User with this email doesn't exists.");
        }else{
            boolean correctPassword=encoder.matches(password,currentUser.getPassword());
            if(correctPassword){
                String token= JwtUtil.generateToken(currentUser);
                return new LoginToken(token,currentUser.getPid(),currentUser.getName(),currentUser.getEmail());
            }else{
                throw new IncorrectPasswordException( "Incorrect Password.");
            }
        }
    }

    public void signup(RegisterUser user) {
        Users currentUser=repository.findByEmail(user.getEmail());
        if(currentUser!=null){
            throw new UserAlreadyExistsException("User with this email already exists. Try with a different one.");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        repository.save(convertDTO(UUID.randomUUID().toString(),user));
    }

    public boolean forgetPassword(String email){
        System.out.println("here in forgot passwd service");
        Users currentUser=findUser(email);
        if(currentUser!=null){
            System.out.println("goinf for extn service");
            forgetPasswordService.sendForgotPasswordOtp(email);
            return true;
        }
        return false;
    }

    public boolean verifyOtp(OtpDTO otpData){
        OTP otpFromDB=otpRepository.findTopByEmailOrderByCreatedAtDesc(otpData.getEmail());
        System.out.println(otpFromDB.getOtp()+","+otpFromDB.getEmail());
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isAfter(otpFromDB.getExpiresAt())) {
            throw new CustomException("OTP expired", 400);
        }
        if(!otpData.getOtp().equals(otpFromDB.getOtp())){
            throw new CustomException("OTP not correct",400);
        }
        return true;

    }

    public void updatePassword(NewPasswordDTO newPassword){
        try{
            System.out.println("passwd"+newPassword.getNewPassword());
            String hashedPassword=new BCryptPasswordEncoder().encode(newPassword.getNewPassword());
            repository.updatePasswordByEmail(newPassword.getEmail(),hashedPassword);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public Users convertDTO(String pid,RegisterUser user){
        Users regdUser=new Users(pid,user.getName(),user.getEmail(),user.getPassword());
        return regdUser;
    }
}
