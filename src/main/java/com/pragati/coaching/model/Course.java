package com.pragati.coaching.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String duration;          // e.g. "1 Year", "6 Months"
    private String subjects;          // comma separated
    private Double fees;
    private String eligibility;       // e.g. "Class 10 Pass"
    private String schedule;          // e.g. "Mon-Sat 8AM-10AM"
    private String instructor;
    private boolean active = true;
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
