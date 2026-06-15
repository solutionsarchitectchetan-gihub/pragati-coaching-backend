package com.pragati.coaching.controller;

import com.pragati.coaching.dto.ApiResponse;
import com.pragati.coaching.exception.ResourceNotFoundException;
import com.pragati.coaching.model.Role;
import com.pragati.coaching.repository.RoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        return ResponseEntity.ok(ApiResponse.ok(repo.findAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody Role role) {
        if (repo.existsByName(role.getName().toUpperCase()))
            throw new IllegalArgumentException("Role already exists: " + role.getName());
        role.setName(role.getName().toUpperCase());
        return ResponseEntity.ok(ApiResponse.ok("Role created", repo.save(role)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id,
                                                  @RequestBody Role body) {
        Role role = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + id));
        role.setDescription(body.getDescription());
        return ResponseEntity.ok(ApiResponse.ok("Role updated", repo.save(role)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Role not found: " + id);
        repo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Role deleted", null));
    }
}
