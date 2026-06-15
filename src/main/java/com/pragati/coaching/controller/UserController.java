package com.pragati.coaching.controller;

import com.pragati.coaching.dto.ApiResponse;
import com.pragati.coaching.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        return ResponseEntity.ok(ApiResponse.ok(repo.findAll()));
    }
}
