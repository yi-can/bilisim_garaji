package com.example.springcase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.springcase.dto.AuthRequest;
import com.example.springcase.respose.AuthResponse;
import com.example.springcase.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String accessToken = jwtUtil.generateAccessToken(request.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String username = jwtUtil.extractUsername(request.getRefreshToken());

        if (!jwtUtil.validateToken(request.getRefreshToken(), username)) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }

        String newAccessToken = jwtUtil.generateAccessToken(username);
        return ResponseEntity.ok(new AuthResponse(newAccessToken, request.getRefreshToken()));
    }
}

