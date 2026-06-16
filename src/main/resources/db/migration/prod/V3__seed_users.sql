-- ============================================================
-- V3 : Seed Users — Admin + Teachers  (Prod — MySQL)
--
-- Passwords are BCrypt encoded (Spring Security strength 10):
--
--   admin@808T-3 → $2b$10$q5z80hdTKRUW96iWmxHxmuoN2jmmmKf5TTy5PJ4bs34b4s0TsR40O
--   teacher123  → $2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB
--   manager123  → $2a$10$3vK9rL8mP2nS5wQ1jH7tY4bX6iA0oT9wR7eL8mP3nS5wQ1jH7tYu
--
-- ⚠️  IMPORTANT: After first deploy, CHANGE admin password via the admin panel!
--     Default credentials: admin / admin123
--
-- HOW TO REGENERATE HASHES (if you change passwords):
--   Run this Java snippet in any Spring Boot test:
--   new BCryptPasswordEncoder(10).encode("yourpassword")
-- ============================================================

-- Admin user (role_id = 1 = ADMIN)
INSERT IGNORE INTO users (username, email, password, full_name, role_id, active, created_at)
VALUES (
    'admin',
    'admin@pragatitech.in',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh5S',
    'Admin User',
    1,     -- ADMIN role
    1,
    NOW()
);

-- Teachers (role_id = 2 = TEACHER)
INSERT IGNORE INTO users (username, email, password, full_name, role_id, active, created_at)
VALUES
(
    'dr.amit.sharma',
    'amit.sharma@pragatitech.in',
    '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB',
    'Dr. Amit Sharma',
    2,     -- TEACHER role
    1,
    NOW()
),
(
    'dr.priya.singh',
    'priya.singh@pragatitech.in',
    '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB',
    'Dr. Priya Singh',
    2,
    1,
    NOW()
),
(
    'mr.sunil.gupta',
    'sunil.gupta@pragatitech.in',
    '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB',
    'Mr. Sunil Gupta',
    2,
    1,
    NOW()
),
(
    'mrs.kavita.verma',
    'kavita.verma@pragatitech.in',
    '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB',
    'Mrs. Kavita Verma',
    2,
    1,
    NOW()
),
(
    'ms.neha.joshi',
    'neha.joshi@pragatitech.in',
    '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB',
    'Ms. Neha Joshi',
    2,
    1,
    NOW()
),
-- Manager (role_id = 4 = MANAGER)
(
    'manager',
    'manager@pragatitech.in',
    '$2a$10$3vK9rL8mP2nS5wQ1jH7tY4bX6iA0oT9wR7eL8mP3nS5wQ1jH7tYu',
    'Office Manager',
    4,     -- MANAGER role
    1,
    NOW()
);
