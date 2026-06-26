package com.pragati.coaching.controller;

import com.pragati.coaching.dto.ApiResponse;
import com.pragati.coaching.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final CourseRepository courseRepo;

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<?>> courses() {
        return ResponseEntity.ok(ApiResponse.ok(courseRepo.findByActiveTrue()));
    }

    @GetMapping("/infrastructure")
    public ResponseEntity<ApiResponse<?>> infrastructure() {
        var infra = List.of(
            Map.of("id", 1, "name", "Smart Classrooms",    "icon", "monitor",
                   "desc", "10 fully equipped smart classrooms with HD projectors, digital boards and seamless Wi-Fi"),
            Map.of("id", 2, "name", "HD Video Lectures",   "icon", "video",
                   "desc", "Recorded HD video lectures available 24×7 on our portal — never miss a class"),
            Map.of("id", 3, "name", "Hi-Fi Audio System",  "icon", "mic",
                   "desc", "Crystal-clear audio with professional microphone setup and noise cancellation in every room"),
            Map.of("id", 4, "name", "Air-Conditioned",     "icon", "wind",
                   "desc", "100% air-conditioned campus — stay comfortable and focused all year round"),
            Map.of("id", 5, "name", "Digital Library",     "icon", "book-open",
                   "desc", "5,000+ books, study materials, previous year papers and online resources"),
            Map.of("id", 6, "name", "Online Test Series",  "icon", "clipboard-list",
                   "desc", "Weekly mock tests with automated grading, detailed performance analytics and AI-based feedback"),
            Map.of("id", 7, "name", "Computer Lab",        "icon", "cpu",
                   "desc", "50-seat computer lab with high-speed internet for online practice and research"),
            Map.of("id", 8, "name", "CCTV Security",       "icon", "shield-check",
                   "desc", "24×7 CCTV surveillance across campus for a safe and secure learning environment")
        );
        return ResponseEntity.ok(ApiResponse.ok(infra));
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<?>> publicStats() {
        var stats = Map.of(
            "studentsEnrolled", "2500+",
            "yearsOfExcellence", "15+",
            "successRate", "92%",
            "expertFaculty", "50+"
        );
        return ResponseEntity.ok(ApiResponse.ok(stats));
    }
}
