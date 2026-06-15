package com.pragati.coaching.dto;

import com.pragati.coaching.model.Student.StudentStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record StudentDto(
    Long id,
    @NotBlank String fullName,
    @Email @NotBlank String email,
    @NotBlank String phone,
    String rollNumber,
    @NotBlank String course,
    String batch,
    String standard,
    String address,
    StudentStatus status,
    LocalDate joinDate,
    Double feesPaid,
    Double totalFees,
    String parentName,
    String parentPhone
) {}
