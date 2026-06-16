-- ============================================================
-- V6 : Update admin password
-- ============================================================

UPDATE users
SET password = '$2b$10$q5z80hdTKRUW96iWmxHxmuoN2jmmmKf5TTy5PJ4bs34b4s0TsR40O'
WHERE username = 'admin';
