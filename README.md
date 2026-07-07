# Auth App Backend

A Spring Boot backend built to learn and practice **Authentication & Authorization** concepts, including JWT-based auth, role management, and OAuth-style provider support.

> **Status:** Work in progress / learning project. Core user & role management is in place; JWT issuing/validation and Spring Security configuration are still being built out (see [Roadmap](#roadmap)).

## Tech Stack

- **Java 21**
- **Spring Boot 4** (Web, Data JPA, Security, Validation)
- **MySQL** (via `mysql-connector-j`)
- **Lombok** – boilerplate reduction
- **ModelMapper** – entity ↔ DTO mapping
- **springdoc-openapi (Swagger UI)** – API documentation
- **Maven** – build tool

## Project Structure

```
src/main/java/in/ara/auth/auth_app_backend/
├── config/          # App-level configuration (ModelMapper bean, etc.)
├── controllers/      # REST controllers (Auth, User)
├── dto/               # Data Transfer Objects
├── entities/          # JPA entities (User, Role, Provider enum)
├── exceptions/        # Global exception handling
├── helpers/           # Helper/utility classes
├── repositories/      # Spring Data JPA repositories
└── services/          # Service interfaces + implementations
```

## Features

- ✅ User CRUD (create, read, update, delete, fetch by email/id)
- ✅ Role entity with many-to-many mapping to users
- ✅ Provider enum for local & OAuth-style logins (`LOCAL`, `GOOGLE`, `GITHUB`, `FACEBOOK`)
- ✅ Global exception handling
- ✅ Swagger/OpenAPI docs
- 🚧 JWT token generation & validation
- 🚧 Spring Security configuration (auth filters, password hashing, protected routes)
- 🚧 Login endpoint

## Prerequisites

- JDK 21+
- Maven (or use the included `mvnw` wrapper)
- MySQL running locally (or update the datasource config to point elsewhere)

## Configuration

The app uses Spring profiles: `dev`, `qa`, `prod` (set in `application.yaml`, active profile defaults to `dev`).

Database connection and other environment-specific values live in `application-dev.yaml`, `application-qa.yaml`, and `application-prod.yaml`. **Do not commit real credentials to these files** — see [What Not to Push](#what-not-to-push) below.

Before running locally, create/update `src/main/resources/application-dev.yaml` with your own DB credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/my_auth_app
    username: your_db_username
    password: your_db_password
```

## Running the App

```bash
# using the Maven wrapper
./mvnw spring-boot:run

# or with Maven installed globally
mvn spring-boot:run
```

The app starts on the port defined in the active profile's `application-<profile>.yaml` (dev defaults to `8090`).

Swagger UI (once running): `http://localhost:8090/swagger-ui.html`

## API Endpoints (current)

| Method | Endpoint                        | Description             |
|--------|----------------------------------|--------------------------|
| POST   | `/api/v1/auth/register`         | Register a new user      |
| POST   | `/api/v1/users`                 | Create a user             |
| GET    | `/api/v1/users`                 | Get all users             |
| GET    | `/api/v1/users/{userId}`        | Get user by ID            |
| GET    | `/api/v1/users/email/{emailId}` | Get user by email         |
| PUT    | `/api/v1/users/{userId}`        | Update user                |
| DELETE | `/api/v1/users/{userId}`        | Delete user                |

## Roadmap

- [ ] Implement password hashing (BCrypt) on registration
- [ ] Add login endpoint issuing JWT access/refresh tokens
- [ ] Add Spring Security filter chain to validate JWTs on protected routes
- [ ] Role-based authorization (`@PreAuthorize`, method security)
- [ ] OAuth2 login (Google/GitHub) using the `Provider` field
- [ ] Unit & integration tests for auth flows

## What Not to Push

This is a learning/practice repo, but it's still good habit to keep secrets and machine-specific files out of version control. The `.gitignore` in this repo already excludes:

- **`target/`** – compiled build output
- **`.idea/`, `*.iml`** – IntelliJ project files
- **`.vscode/`** – VS Code settings
- **`.env`, `.env.*`** – environment variable files
- **`application-local.yaml`, `application-secrets.yaml`** – any local/secret-only config you create
- **`*.log`, `logs/`** – log files
- OS junk files (`.DS_Store`, `Thumbs.db`)

In addition, please **manually double-check before every commit** that you are not pushing:

- Real database usernames/passwords (the current `application-dev.yaml` has `root/root`, which is fine for a local practice DB, but **replace with a placeholder or move to environment variables before pushing publicly**)
- JWT signing secrets/keys (once implemented)
- Any OAuth client IDs/secrets (Google, GitHub, Facebook) once OAuth login is added
- API keys for any third-party service
- `.jar`/`.class` build artifacts

**Recommended:** move sensitive values (DB password, JWT secret, OAuth client secret) into environment variables and reference them in the YAML with `${ENV_VAR_NAME}`, e.g.:

```yaml
spring:
  datasource:
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

## License

Personal learning project — no license specified yet.
