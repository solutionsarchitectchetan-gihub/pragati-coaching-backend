package com.pragati.coaching.seed;

import com.pragati.coaching.model.*;
import com.pragati.coaching.model.Student.StudentStatus;
import com.pragati.coaching.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Runs ONLY in prod profile — after Flyway migrations.
 *
 * Flyway handles: schema + roles + courses + sample students
 * This seeder handles: users with correctly BCrypt-encoded passwords
 * (BCrypt cannot be pre-encoded in SQL as the hash changes every run)
 *
 * It is IDEMPOTENT — skips if user already exists.
 */
@Slf4j
@Profile("prod")
@Component
@Order(1)
@RequiredArgsConstructor
public class ProdDataSeeder implements CommandLineRunner {

    private final RoleRepository    roleRepo;
    private final UserRepository    userRepo;
    private final PasswordEncoder   passwordEncoder;

    @Override
    public void run(String... args) {
        seedProdUsers();
    }

    private void seedProdUsers() {
        // Map of username → { password, fullName, roleName }
        Map<String, String[]> users = Map.of(
            "admin",           new String[]{"admin123",   "Admin User",       "ADMIN"},
            "dr.amit.sharma",  new String[]{"teacher123", "Dr. Amit Sharma",  "TEACHER"},
            "dr.priya.singh",  new String[]{"teacher123", "Dr. Priya Singh",  "TEACHER"},
            "mr.sunil.gupta",  new String[]{"teacher123", "Mr. Sunil Gupta",  "TEACHER"},
            "mrs.kavita.verma",new String[]{"teacher123", "Mrs. Kavita Verma","TEACHER"},
            "ms.neha.joshi",   new String[]{"teacher123", "Ms. Neha Joshi",   "TEACHER"},
            "manager",         new String[]{"manager123", "Office Manager",   "MANAGER"}
        );

        users.forEach((username, data) -> {
            if (userRepo.existsByUsername(username)) {
                log.debug("User '{}' already exists — skipping", username);
                return;
            }
            var role = roleRepo.findByName(data[2])
                    .orElseThrow(() -> new IllegalStateException(
                            "Role not found: " + data[2] + " — ensure Flyway V2 ran successfully"));

            var user = User.builder()
                    .username(username)
                    .email(username.replace(".", "_") + "@pragatitech.in")
                    .password(passwordEncoder.encode(data[0]))   // BCrypt at runtime
                    .fullName(data[1])
                    .role(role)
                    .active(true)
                    .build();

            userRepo.save(user);
            log.info("✅ Prod user created: {} (role={})", username, data[2]);
        });

        log.info("======================================================");
        log.info("  Pragati Coaching Center — Production started");
        log.info("  Admin login: admin / admin123");
        log.info("  ⚠️  Change admin password after first login!");
        log.info("======================================================");
    }
}
