package com.test.jwt.controller;


import com.test.jwt.dto.*;
import com.test.jwt.entities.User;
import com.test.jwt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<User>> signUp(@RequestBody SignupRequest request) {
        try {
            return ResponseEntity.ok(service.signUp(request));
        } catch (Exception e) {
            final var response = new BaseResponse<User>(null);
            response.setError("signup error: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse<JwtAuthenticationResponse>> signIn(@RequestBody SignInRequest request) {
        try {
            return ResponseEntity.ok(service.signIn(request));
        } catch (Exception e) {
            final var response = new BaseResponse<JwtAuthenticationResponse>(null);
            response.setError("user not found message: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("refreshToken")
    public ResponseEntity<BaseResponse<JwtAuthenticationResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            return ResponseEntity.ok(service.refreshToken(request));
        } catch (Exception e) {
            final var response = new BaseResponse<JwtAuthenticationResponse>(null);
            response.setError("token not found message: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

}
