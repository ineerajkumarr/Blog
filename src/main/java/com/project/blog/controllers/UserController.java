package com.project.blog.controllers;

import com.project.blog.dto.*;
import com.project.blog.model.Users;
import com.project.blog.service.ForgotPasswordService;
import com.project.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/find")
    public ApiResponse getUser(@RequestBody ForgetPassword email) {
        System.out.println("here only");
        Users user= userService.findUser(email.getEmail());
        return new ApiResponse(true,"Got details Successfully",user);
    }

//    public ApiResponse forgetPassword(@RequestBody ForgetPassword email){
//        Users currentUser= userService.findUser(email.getEmail());
//        if(currentUser!=null){
//
//        }
//    }

    @PostMapping("/forgot-password")
    public ApiResponse forgotPassword(@RequestBody ForgetPassword email) {
        System.out.println("here in forgot passworrd controller");
        if(userService.forgetPassword(email.getEmail())) {
            return new ApiResponse(true, "OTP sent to email");
        }
        return new ApiResponse<>(false,"Some error occurred");
    }

    @PostMapping("/verify-otp")
    public ApiResponse verifyOTP(@RequestBody OtpDTO otpData){
        boolean result=userService.verifyOtp(otpData);
        if(!result){
            return new ApiResponse<>(false,"OTP error");
        }
        return new ApiResponse(true,"OTP Validated");
    }

    @PostMapping("/update-password")
    public ApiResponse updatePassword(@RequestBody NewPasswordDTO newPasswordData){
        userService.updatePassword(newPasswordData);
        return new ApiResponse(true,"Password Updated");
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody User user) {
        System.out.println("here only");
        LoginToken data= userService.login(user.getEmail(),user.getPassword());
        return new ApiResponse(true,"Logged in successfully",data);
    }

    @PostMapping("/signup")
    public ApiResponse signup(@RequestBody RegisterUser user){
        System.out.println("here in signup");
        userService.signup(user);
        return new ApiResponse(true,"User Registered Successfully");
    }

}
