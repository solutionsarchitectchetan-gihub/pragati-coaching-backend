-- ============================================================
-- V2 : Seed Roles  (Prod — PostgreSQL)
-- ============================================================

INSERT INTO roles (name, description, created_at) VALUES
    ('ADMIN',      'Full access — manage everything including roles and users',   NOW()),
    ('TEACHER',    'Manage courses, view and grade students',                      NOW()),
    ('STUDENT',    'View own profile, courses and results',                        NOW()),
    ('MANAGER',    'Manage student admissions, fees and attendance',               NOW()),
    ('COUNSELLOR', 'Student counselling and enquiry management',                   NOW())
ON CONFLICT (name) DO NOTHING;
