package com.example.api_blog.controller;

import com.example.api_blog.jwt.JwtService;
import com.example.api_blog.model.entity.Auth;
import com.example.api_blog.model.request.LoginRequest;
import com.example.api_blog.model.request.RegisterRequest;
import com.example.api_blog.model.response.ApiResponse;
import com.example.api_blog.model.response.LoginResponse;
import com.example.api_blog.repository.AuthRepo;
import com.example.api_blog.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class AuthController {

    private final AuthService authService;
    private final AuthRepo authRepo;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Auth>> register(@RequestBody RegisterRequest registerRequest){
        Auth auth = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("register successfully",auth,HttpStatus.OK.value(), LocalDateTime.now())
        );
    }

    @PostMapping("/login")

    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
      LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("login successfully",response,HttpStatus.OK.value(), LocalDateTime.now())
        );
    }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/logout-all")
    public ResponseEntity<ApiResponse<Integer>> logoutAll() { // Changed generic to Integer

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }

        String email = authentication.getName();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "logout successfully",
                        authService.logoutAll(email), // Now returns an int
                        200,
                        java.time.LocalDateTime.now()
                )
        );
    }


}
