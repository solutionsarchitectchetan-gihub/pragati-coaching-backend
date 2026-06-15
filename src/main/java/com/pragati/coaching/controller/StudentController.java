package com.pragati.coaching.controller;

import com.pragati.coaching.dto.*;
import com.pragati.coaching.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/students")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list(
            @RequestParam(defaultValue = "0")    int    page,
            @RequestParam(defaultValue = "10")   int    size,
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(required = false)      String search) {
        return ResponseEntity.ok(ApiResponse.ok(service.getAll(page, size, sort, search)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody StudentDto dto) {
        return ResponseEntity.ok(ApiResponse.ok("Student created", service.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id,
                                                  @Valid @RequestBody StudentDto dto) {
        return ResponseEntity.ok(ApiResponse.ok("Student updated", service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Student deleted", null));
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<?>> stats() {
        return ResponseEntity.ok(ApiResponse.ok(service.stats()));
    }
}
