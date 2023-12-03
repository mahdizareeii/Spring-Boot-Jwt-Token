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
        return ResponseEntity.ok(service.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse<JwtAuthenticationResponse>> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(service.signIn(request));
    }

    @PostMapping("refreshToken")
    public ResponseEntity<BaseResponse<JwtAuthenticationResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }

}
