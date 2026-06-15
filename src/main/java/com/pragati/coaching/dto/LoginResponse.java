package com.pragati.coaching.dto;

public record LoginResponse(
    String token,
    String tokenType,
    String username,
    String fullName,
    String role,
    Long userId
) {}
