-- ============================================================
-- V1 : Create schema  (Prod — PostgreSQL)
-- ============================================================

CREATE TABLE IF NOT EXISTS roles (
    id          BIGSERIAL    PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at  TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
    id         BIGSERIAL    PRIMARY KEY,
    username   VARCHAR(100) NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    full_name  VARCHAR(255) NOT NULL,
    role_id    BIGINT       NOT NULL REFERENCES roles(id),
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses (
    id          BIGSERIAL        PRIMARY KEY,
    name        VARCHAR(255)     NOT NULL,
    description TEXT,
    duration    VARCHAR(100),
    subjects    VARCHAR(500),
    fees        DOUBLE PRECISION,
    eligibility VARCHAR(255),
    schedule    VARCHAR(255),
    instructor  VARCHAR(255),
    active      BOOLEAN          NOT NULL DEFAULT TRUE,
    image_url   VARCHAR(500),
    created_at  TIMESTAMP
);

CREATE TABLE IF NOT EXISTS students (
    id           BIGSERIAL        PRIMARY KEY,
    full_name    VARCHAR(255)     NOT NULL,
    email        VARCHAR(255)     NOT NULL UNIQUE,
    phone        VARCHAR(20)      NOT NULL,
    roll_number  VARCHAR(50)      NOT NULL UNIQUE,
    course       VARCHAR(255)     NOT NULL,
    batch        VARCHAR(100),
    standard     VARCHAR(100),
    address      VARCHAR(500),
    status       VARCHAR(20)      DEFAULT 'ACTIVE',
    join_date    DATE,
    fees_paid    DOUBLE PRECISION,
    total_fees   DOUBLE PRECISION,
    parent_name  VARCHAR(255),
    parent_phone VARCHAR(20),
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP
);
