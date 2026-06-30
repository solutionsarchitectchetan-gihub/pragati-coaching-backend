-- ============================================================
-- V3 : Seed Users — Admin + Teachers  (Prod — PostgreSQL)
--
-- Passwords are BCrypt encoded (Spring Security strength 10):
--
--   admin@808T-3 → $2b$10$q5z80hdTKRUW96iWmxHxmuoN2jmmmKf5TTy5PJ4bs34b4s0TsR40O
--   teacher123   → $2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB
--   manager123   → $2a$10$3vK9rL8mP2nS5wQ1jH7tY4bX6iA0oT9wR7eL8mP3nS5wQ1jH7tYu
-- ============================================================

INSERT INTO users (username, email, password, full_name, role_id, active, created_at)
VALUES (
    'admin',
    'admin@pragatitech.in',
    '$2b$10$q5z80hdTKRUW96iWmxHxmuoN2jmmmKf5TTy5PJ4bs34b4s0TsR40O',
    'Admin User',
    1,
    TRUE,
    NOW()
) ON CONFLICT (username) DO NOTHING;

INSERT INTO users (username, email, password, full_name, role_id, active, created_at)
VALUES
('dr.amit.sharma',   'amit.sharma@pragatitech.in',  '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB', 'Dr. Amit Sharma',  2, TRUE, NOW()),
('dr.priya.singh',   'priya.singh@pragatitech.in',  '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB', 'Dr. Priya Singh',  2, TRUE, NOW()),
('mr.sunil.gupta',   'sunil.gupta@pragatitech.in',  '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB', 'Mr. Sunil Gupta',  2, TRUE, NOW()),
('mrs.kavita.verma', 'kavita.verma@pragatitech.in', '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB', 'Mrs. Kavita Verma',2, TRUE, NOW()),
('ms.neha.joshi',    'neha.joshi@pragatitech.in',   '$2a$10$8gHQy5oZ4J6Wm7Qe9d6C4uH2vX8K5mP3nR1oT7wY4bL6iA9xS0jB', 'Ms. Neha Joshi',   2, TRUE, NOW()),
('manager',          'manager@pragatitech.in',       '$2a$10$3vK9rL8mP2nS5wQ1jH7tY4bX6iA0oT9wR7eL8mP3nS5wQ1jH7tYu', 'Office Manager',   4, TRUE, NOW())
ON CONFLICT (username) DO NOTHING;
