-- ============================================================
-- V2 : Seed Roles  (Dev)
-- ============================================================

INSERT INTO roles (name, description, created_at) VALUES
    ('ADMIN',      'Full access — manage everything including roles and users',   CURRENT_TIMESTAMP),
    ('TEACHER',    'Manage courses, view and grade students',                      CURRENT_TIMESTAMP),
    ('STUDENT',    'View own profile, courses and results',                        CURRENT_TIMESTAMP),
    ('MANAGER',    'Manage student admissions, fees and attendance',               CURRENT_TIMESTAMP),
    ('COUNSELLOR', 'Student counselling and enquiry management',                   CURRENT_TIMESTAMP);
