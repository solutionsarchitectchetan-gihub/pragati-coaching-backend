package com.pragati.coaching.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Email @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String phone;

    @Column(unique = true, nullable = false)
    private String rollNumber;

    @NotBlank
    private String course;

    private String batch;           // e.g. "2024-A", "Morning Batch"
    private String standard;        // e.g. "Class 10", "Class 12", "Dropper"
    private String address;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    private LocalDate joinDate;
    private Double feesPaid;
    private Double totalFees;

    private String parentName;
    private String parentPhone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt  = LocalDateTime.now();
        this.updatedAt  = LocalDateTime.now();
        if (this.joinDate == null) this.joinDate = LocalDate.now();
        if (this.status   == null) this.status   = StudentStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum StudentStatus {
        ACTIVE, INACTIVE, PASSED_OUT, DROPPED
    }
}
