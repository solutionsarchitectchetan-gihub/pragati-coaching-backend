package com.pragati.coaching.controller;

import com.pragati.coaching.dto.*;
import com.pragati.coaching.repository.UserRepository;
import com.pragati.coaching.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService            jwtService;
    private final UserDetailsService    userDetailsService;
    private final UserRepository        userRepo;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));

        var userDetails = userDetailsService.loadUserByUsername(req.username());
        var user        = userRepo.findByUsername(req.username()).orElseThrow();
        var token       = jwtService.generateToken(userDetails);

        var response = new LoginResponse(
                token, "Bearer",
                user.getUsername(), user.getFullName(),
                user.getRole().getName(), user.getId()
        );
        return ResponseEntity.ok(ApiResponse.ok("Login successful", response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> me(@org.springframework.security.core.annotation.AuthenticationPrincipal
                                              org.springframework.security.core.userdetails.UserDetails principal) {
        var user = userRepo.findByUsername(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(ApiResponse.ok(user));
    }
}
