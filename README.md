# 🏫 Pragati Coaching Center — Backend API

> Spring Boot 3.3 · Java 21 · JWT Authentication · H2 (Dev) / PostgreSQL (Prod)

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
  - [Local Run (No Docker)](#local-run-no-docker)
  - [Docker Dev](#docker---dev)
  - [Docker Prod](#docker---production)
- [Environment Variables](#-environment-variables)
- [API Endpoints](#-api-endpoints)
  - [Auth](#auth)
  - [Public](#public-no-auth-required)
  - [Admin — Students](#admin--students-requires-admin--manager-role)
  - [Admin — Roles](#admin--roles-requires-admin-role)
- [Data Models](#-data-models)
- [Security & JWT Flow](#-security--jwt-flow)
- [Seed Data](#-seed-data-auto-loaded-on-startup)
- [Database Console](#-h2-database-console-dev-only)
- [Load Testing](#-load-testing)

---

## 🎯 Project Overview

The **Pragati Coaching Center Backend** is a RESTful API service that powers:

- **Public website** — course listings, infrastructure info, stats
- **Admin panel** — full CRUD for students, roles, users
- **JWT-based authentication** — stateless, secure, role-based access control
- **Multi-environment support** — H2 in-memory (dev), PostgreSQL (prod)

| Environment | Database   | Port | Profile      |
|-------------|------------|------|--------------|
| Dev         | H2 In-Memory | 8080 | `dev`      |
| Prod        | PostgreSQL | 8080 | `prod`      |

---

## 🛠 Tech Stack

| Layer           | Technology                        | Version  |
|-----------------|-----------------------------------|----------|
| Language        | Java                              | 21       |
| Framework       | Spring Boot                       | 3.3.0    |
| Security        | Spring Security + JWT (jjwt)      | 0.12.3   |
| Persistence     | Spring Data JPA + Hibernate       | 3.3.0    |
| Database (Dev)  | H2 In-Memory                      | Latest   |
| Database (Prod) | PostgreSQL                        | 16       |
| Build Tool      | Gradle                            | 8.5      |
| Boilerplate     | Lombok                            | Latest   |
| Validation      | Jakarta Bean Validation           | 3.x      |
| Monitoring      | Spring Actuator                   | 3.3.0    |
| Container       | Docker (amazoncorretto:21-alpine) | —        |

---

## 🏗 Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                        React Frontend                               │
│                    http://localhost:3000                            │
└────────────────────────────┬────────────────────────────────────────┘
                             │ HTTP / REST (JSON)
                             ▼
┌─────────────────────────────────────────────────────────────────────┐
│               Spring Boot API  :8080/api                            │
│                                                                     │
│  ┌──────────┐  ┌──────────┐  ┌───────────┐  ┌──────────────────┐  │
│  │  Auth    │  │ Public   │  │  Admin    │  │  Admin           │  │
│  │Controller│  │Controller│  │ Students  │  │  Roles / Users   │  │
│  └────┬─────┘  └────┬─────┘  └─────┬─────┘  └────────┬─────────┘  │
│       │             │              │                  │             │
│  ┌────▼─────────────▼──────────────▼──────────────────▼──────────┐ │
│  │              Service Layer                                      │ │
│  │   JwtService · StudentService · RoleService                    │ │
│  └────────────────────────────┬───────────────────────────────────┘ │
│                               │                                     │
│  ┌────────────────────────────▼───────────────────────────────────┐ │
│  │              Repository Layer (Spring Data JPA)                 │ │
│  └────────────────────────────┬───────────────────────────────────┘ │
│                               │                                     │
│  ┌────────────────────────────▼───────────────────────────────────┐ │
│  │         H2 (dev)  /  PostgreSQL (prod)                          │ │
│  └─────────────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────────────┘
```

### Request Flow (JWT)
```
Client → JwtAuthFilter → SecurityFilterChain → Controller → Service → Repository → DB
              │
              └─ Extracts Bearer token
              └─ Validates signature + expiry
              └─ Sets SecurityContext
```

---

## 📁 Project Structure

```
pragati-coaching-backend/
│
├── src/main/java/com/pragati/coaching/
│   │
│   ├── CoachingApplication.java          ← Spring Boot entry point
│   │
│   ├── config/
│   │   └── SecurityConfig.java           ← CORS, JWT filter chain, BCrypt bean
│   │
│   ├── model/                            ← JPA Entities
│   │   ├── User.java                     ← System users (admin, teacher…)
│   │   ├── Role.java                     ← Dynamic roles (ADMIN, STUDENT…)
│   │   ├── Student.java                  ← Student records with enum status
│   │   └── Course.java                   ← Course catalogue
│   │
│   ├── repository/                       ← Spring Data JPA interfaces
│   │   ├── UserRepository.java
│   │   ├── RoleRepository.java
│   │   ├── StudentRepository.java        ← Full-text search query
│   │   └── CourseRepository.java
│   │
│   ├── service/
│   │   └── StudentService.java           ← CRUD + pagination + stats
│   │
│   ├── controller/
│   │   ├── AuthController.java           ← POST /auth/login, GET /auth/me
│   │   ├── StudentController.java        ← CRUD /admin/students/**
│   │   ├── RoleController.java           ← CRUD /admin/roles/**
│   │   └── PublicController.java         ← /public/** (no auth)
│   │
│   ├── dto/
│   │   ├── LoginRequest.java             ← { username, password }
│   │   ├── LoginResponse.java            ← { token, role, userId… }
│   │   ├── StudentDto.java               ← Create / update payload
│   │   └── ApiResponse.java              ← Wrapper: { success, message, data }
│   │
│   ├── security/
│   │   ├── JwtService.java               ← Generate / validate JWT tokens
│   │   ├── JwtAuthFilter.java            ← OncePerRequestFilter — reads Bearer header
│   │   └── UserDetailsServiceImpl.java   ← Loads user + ROLE_ authority
│   │
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java   ← @RestControllerAdvice
│   │   └── ResourceNotFoundException.java
│   │
│   └── seed/
│       └── DataSeeder.java               ← Loads demo data on startup
│
├── src/main/resources/
│   ├── application.properties            ← Common config (JWT, actuator, server)
│   ├── application-dev.properties        ← H2, DEBUG logging, h2-console
│   └── application-prod.properties       ← PostgreSQL, WARN logging
│
├── Dockerfile                            ← Multi-stage: builder + alpine runtime
├── docker-compose.yml                    ← Dev: H2 only, port 8080
├── docker-compose.prod.yml               ← Prod: PostgreSQL + backend
├── .env.dev                              ← Dev environment variables
├── .env.prod                             ← Prod environment variables (gitignored)
└── build.gradle                          ← Dependencies + bootJar config
```

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Docker Desktop
- Gradle (or use `./gradlew`)

---

### Local Run (No Docker)

```bash
cd pragati-coaching-backend

# Run with dev profile (H2 database)
./gradlew bootRun

# Or build JAR first then run
./gradlew bootJar
java -jar build/libs/pragati-coaching-backend.jar
```

App starts at → `http://localhost:8080/api`

---

### Docker — Dev

```bash
cd pragati-coaching-backend

# Build image and start container
docker compose up --build -d

# Check status
docker ps

# View logs
docker logs -f pragati-backend

# Stop
docker compose down
```

---

### Docker — Production

```bash
cd pragati-coaching-backend

# Start with PostgreSQL
docker compose -f docker-compose.yml -f docker-compose.prod.yml up --build -d

# PostgreSQL data is persisted in Docker volume: postgres_data
```

---

## 🔐 Environment Variables

### `.env.dev`
| Variable            | Default Value                                 | Description            |
|---------------------|-----------------------------------------------|------------------------|
| `SERVER_PORT`       | `8080`                                        | Application port       |
| `SPRING_PROFILES_ACTIVE` | `dev`                                  | Active Spring profile  |
| `JWT_SECRET`        | `pragati-dev-secret-key...`                   | HMAC signing key       |
| `JWT_EXPIRATION_MS` | `86400000`                                    | Token TTL (24 hours)   |

### `.env.prod`
| Variable            | Default Value                    | Description               |
|---------------------|----------------------------------|---------------------------|
| `DATABASE_URL`      | `jdbc:postgresql://postgres:5432/pragatidb` | PostgreSQL URL |
| `DATABASE_USER`     | `pragati`                        | DB username               |
| `DATABASE_PASSWORD` | `pragati123`                     | DB password               |
| `JWT_SECRET`        | **MUST be replaced from vault**  | Production signing key    |
| `JWT_EXPIRATION_MS` | `3600000`                        | Token TTL (1 hour prod)   |
| `ALLOWED_ORIGINS`   | `https://pragaticoaching.com`    | CORS allowed origins      |

---

## 📡 API Endpoints

> Base URL: `http://localhost:8080/api`
> All responses follow: `{ "success": bool, "message": "...", "data": {...} }`

---

### Auth

| Method | Endpoint         | Auth Required | Description              |
|--------|------------------|---------------|--------------------------|
| POST   | `/auth/login`    | ❌ Public     | Login → returns JWT token |
| GET    | `/auth/me`       | ✅ Any user   | Get current user info    |

**Login Request:**
```json
POST /auth/login
{
  "username": "admin",
  "password": "admin123"
}
```

**Login Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzM4...",
    "tokenType": "Bearer",
    "username": "admin",
    "fullName": "Admin User",
    "role": "ADMIN",
    "userId": 1
  }
}
```

---

### Public (No Auth Required)

| Method | Endpoint                   | Description                    |
|--------|----------------------------|--------------------------------|
| GET    | `/public/courses`          | List all active courses        |
| GET    | `/public/infrastructure`   | List all infrastructure items  |
| GET    | `/public/stats`            | Public stats (students, years) |

---

### Admin — Students (Requires ADMIN / MANAGER role)

| Method | Endpoint                      | Description                            |
|--------|-------------------------------|----------------------------------------|
| GET    | `/admin/students`             | List students (paginated + search)     |
| GET    | `/admin/students/{id}`        | Get single student by ID               |
| POST   | `/admin/students`             | Create new student                     |
| PUT    | `/admin/students/{id}`        | Update existing student                |
| DELETE | `/admin/students/{id}`        | Delete student                         |
| GET    | `/admin/students/stats`       | Student count stats by status          |

**Query Parameters for GET `/admin/students`:**
```
?page=0        → page number (default: 0)
?size=10       → page size (default: 10)
?sort=createdAt → sort field (default: createdAt)
?search=arjun  → search by name/email/roll/course
```

**Create Student Request:**
```json
POST /admin/students
Authorization: Bearer <token>
{
  "fullName":    "Riya Sharma",
  "email":       "riya@email.com",
  "phone":       "9876543299",
  "course":      "IIT-JEE Foundation",
  "batch":       "2024-A",
  "standard":    "Class 12",
  "totalFees":   45000,
  "feesPaid":    20000,
  "parentName":  "Ramesh Sharma",
  "parentPhone": "9876543298"
}
```

---

### Admin — Roles (Requires ADMIN role)

| Method | Endpoint              | Description                     |
|--------|-----------------------|---------------------------------|
| GET    | `/admin/roles`        | List all roles                  |
| POST   | `/admin/roles`        | Create new role (any name)      |
| PUT    | `/admin/roles/{id}`   | Update role description         |
| DELETE | `/admin/roles/{id}`   | Delete role                     |

**Create Role Request:**
```json
POST /admin/roles
Authorization: Bearer <token>
{
  "name": "COORDINATOR",
  "description": "Manages batch scheduling and parent communication"
}
```

---

## 🗄 Data Models

### Student
| Field        | Type           | Description                            |
|--------------|----------------|----------------------------------------|
| id           | Long           | Auto-generated primary key             |
| fullName     | String         | Student full name                      |
| email        | String (unique)| Student email                          |
| phone        | String         | Contact number                         |
| rollNumber   | String (unique)| Auto-generated: `PCC20240001`          |
| course       | String         | Enrolled course name                   |
| batch        | String         | Batch code e.g. `2024-A`               |
| standard     | String         | Class 10 / 12 / Dropper                |
| status       | Enum           | ACTIVE / INACTIVE / PASSED_OUT / DROPPED |
| joinDate     | LocalDate      | Date of enrollment                     |
| feesPaid     | Double         | Amount paid so far                     |
| totalFees    | Double         | Total course fee                       |
| parentName   | String         | Parent / guardian name                 |
| parentPhone  | String         | Parent contact                         |

### Role
| Field       | Type   | Description                          |
|-------------|--------|--------------------------------------|
| id          | Long   | Auto-generated primary key           |
| name        | String | Unique role name (UPPERCASE enforced)|
| description | String | What this role can do                |
| createdAt   | DateTime | Auto-set on creation               |

### User
| Field    | Type    | Description                            |
|----------|---------|----------------------------------------|
| id       | Long    | Auto-generated primary key             |
| username | String  | Unique login username                  |
| email    | String  | Unique email address                   |
| password | String  | BCrypt hashed password                 |
| fullName | String  | Display name                           |
| role     | Role    | FK → roles table                       |
| active   | boolean | Account enabled/disabled               |

---

## 🔒 Security & JWT Flow

```
1. Client sends:  POST /auth/login { username, password }
2. Server:        Validates credentials via AuthenticationManager
3. Server:        Generates JWT signed with HMAC-SHA384
4. Client stores: Token in localStorage
5. Client sends:  Authorization: Bearer <token> on every request
6. JwtAuthFilter: Extracts username from token
7. JwtAuthFilter: Loads UserDetails, validates signature + expiry
8. JwtAuthFilter: Sets SecurityContextHolder → request proceeds
```

**JWT Payload Example:**
```json
{
  "sub":   "admin",
  "roles": ["ROLE_ADMIN"],
  "iat":   1719820000,
  "exp":   1719906400
}
```

**Role-based Access:**
| Role    | Students | Roles | Users | Public |
|---------|----------|-------|-------|--------|
| ADMIN   | ✅ Full  | ✅ Full | ✅ Full | ✅  |
| MANAGER | ✅ Full  | ❌     | ❌     | ✅  |
| TEACHER | ❌       | ❌     | ❌     | ✅  |

---

## 🌱 Seed Data (Auto-loaded on Startup)

### Default Users

| Username  | Password     | Role    |
|-----------|--------------|---------|
| `admin`   | `admin123`   | ADMIN   |
| `teacher1`| `teacher123` | TEACHER |

### Default Roles
`ADMIN` · `TEACHER` · `STUDENT` · `MANAGER`

### Default Courses (5)
`IIT-JEE Foundation` · `NEET Medical` · `Class 10 Board Excellence` · `Class 12 PCM` · `Foundation (Class 8–10)`

### Default Students (7)
Pre-loaded sample students across different courses and statuses.

---

## 🗃 H2 Database Console (Dev only)

```
URL:      http://localhost:8080/api/h2-console
JDBC URL: jdbc:h2:mem:pragatidb
Username: sa
Password: (leave empty)
```

Useful SQL:
```sql
SELECT * FROM students;
SELECT * FROM roles;
SELECT * FROM users;
SELECT * FROM courses;
```

---

## 🔥 Load Testing

```bash
# Install k6
brew install k6

# Smoke test (1 user, 30s)
k6 run --vus 1 --duration 30s - <<'EOF'
import http from 'k6/http'
import { check, sleep } from 'k6'
export default function() {
  check(http.get('http://localhost:8080/api/public/courses'), {
    'status 200': r => r.status === 200
  })
  sleep(1)
}
EOF

# Apache Bench — 1000 requests, 50 concurrent
ab -n 1000 -c 50 http://localhost:8080/api/public/courses

# Watch container stats
docker stats pragati-backend
```

---

## 📊 Health Check

```bash
curl http://localhost:8080/api/actuator/health
# {"status":"UP","components":{"db":{"status":"UP"},...}}
```

---

## 🧪 Quick API Test (curl)

```bash
# 1. Login
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' | \
  python3 -c "import sys,json; print(json.load(sys.stdin)['data']['token'])")

# 2. List students
curl -s http://localhost:8080/api/admin/students \
  -H "Authorization: Bearer $TOKEN" | python3 -m json.tool

# 3. Create student
curl -s -X POST http://localhost:8080/api/admin/students \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Test Student","email":"test@email.com","phone":"9876543000","course":"NEET Medical"}' \
  | python3 -m json.tool

# 4. Create a new role
curl -s -X POST http://localhost:8080/api/admin/roles \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"COORDINATOR","description":"Manages batch scheduling"}' \
  | python3 -m json.tool
```

---

*Built with ❤️ for Pragati Coaching Center — Java 21 · Spring Boot 3.3 · JWT*
