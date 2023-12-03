package com.test.jwt.service;

import com.test.jwt.dto.*;
import com.test.jwt.entities.Role;
import com.test.jwt.entities.User;
import com.test.jwt.repository.UserRepository;
import com.test.jwt.util.TokenUtil;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;

@Service
@Validated
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            TokenUtil tokenUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }

    public BaseResponse<User> signUp(@Valid SignupRequest request) {
        final var user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return new BaseResponse<>(userRepository.save(user));
    }

    public BaseResponse<JwtAuthenticationResponse> signIn(@Valid SignInRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("");
        }
        final var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("")
        );
        final var token = tokenUtil.generateToken(user);
        final var refreshToken = tokenUtil.generateRefreshToken(new HashMap<>(), user);

        final var jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return new BaseResponse<>(jwtAuthenticationResponse);
    }

    public BaseResponse<JwtAuthenticationResponse> refreshToken(RefreshTokenRequest request) {
        final var userEmail = tokenUtil.extractUserName(request.getRefreshToken());
        final var user = userRepository.findByEmail(userEmail).orElseThrow();
        if (tokenUtil.isTokenValid(request.getRefreshToken(), user)) {
            final var token = tokenUtil.generateToken(user);

            final var jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(token);
            jwtAuthenticationResponse.setRefreshToken(request.getRefreshToken());
            return new BaseResponse<>(jwtAuthenticationResponse);
        }
        return null;
    }
}
