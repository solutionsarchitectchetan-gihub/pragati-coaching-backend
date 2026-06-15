-- ============================================================
-- V1 : Create schema  (Prod — MySQL compatible)
-- ============================================================

CREATE TABLE IF NOT EXISTS roles (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50)  NOT NULL,
    description VARCHAR(255),
    created_at  DATETIME(6),
    PRIMARY KEY (id),
    UNIQUE KEY uk_roles_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS users (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    username   VARCHAR(100) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    full_name  VARCHAR(255) NOT NULL,
    role_id    BIGINT       NOT NULL,
    active     TINYINT(1)   NOT NULL DEFAULT 1,
    created_at DATETIME(6),
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_username (username),
    UNIQUE KEY uk_users_email    (email),
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS courses (
    id          BIGINT        NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255)  NOT NULL,
    description TEXT,
    duration    VARCHAR(100),
    subjects    VARCHAR(500),
    fees        DOUBLE,
    eligibility VARCHAR(255),
    schedule    VARCHAR(255),
    instructor  VARCHAR(255),
    active      TINYINT(1)    NOT NULL DEFAULT 1,
    image_url   VARCHAR(500),
    created_at  DATETIME(6),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS students (
    id           BIGINT        NOT NULL AUTO_INCREMENT,
    full_name    VARCHAR(255)  NOT NULL,
    email        VARCHAR(255)  NOT NULL,
    phone        VARCHAR(20)   NOT NULL,
    roll_number  VARCHAR(50)   NOT NULL,
    course       VARCHAR(255)  NOT NULL,
    batch        VARCHAR(100),
    standard     VARCHAR(100),
    address      VARCHAR(500),
    status       VARCHAR(20)   DEFAULT 'ACTIVE',
    join_date    DATE,
    fees_paid    DOUBLE,
    total_fees   DOUBLE,
    parent_name  VARCHAR(255),
    parent_phone VARCHAR(20),
    created_at   DATETIME(6),
    updated_at   DATETIME(6),
    PRIMARY KEY (id),
    UNIQUE KEY uk_students_email (email),
    UNIQUE KEY uk_students_roll  (roll_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
