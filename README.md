# JWT Token

REST API for JWT-based authentication using Spring Boot, Spring Security, and MySQL.

## Technologies

- **Java 17**
- **Spring Boot 4.1.0** (Web, Security, Data JPA)
- **MySQL 8** with Hibernate
- **jjwt 0.13.0** — JWT token generation and validation
- **Lombok** — boilerplate reduction
- **dotenv-java** — environment variables

## Prerequisites

- JDK 17+
- Maven 3.9+
- MySQL 8+

## Setup

Create a `.env` file in the project root with the following variables:

```env
DB_URL=jdbc:mysql://localhost:3306/jwt_token
DB_USERNAME=root
DB_PASSWORD=your_password
JWT_SECRET_KEY=your_jwt_secret_key
```

> The project already includes an example `.env` file ignored by Git (see `.gitignore`).

## Running

### With Maven

```bash
mvn spring-boot:run
```

### With JAR

```bash
mvn clean package -DskipTests
java -jar target/jwt-token-0.0.1-SNAPSHOT.jar
```

The application starts at `http://localhost:8080`.

## Endpoints

### Authentication (`/auth`)

| Method | Path | Description | Body / Headers | Response |
|--------|------|-------------|----------------|----------|
| POST | `/auth/register` | Register a new user | `{ "email": "...", "password": "...", "name": "..." }` | `{ "access_token": "...", "refresh_token": "..." }` |
| POST | `/auth/login` | Log in | `{ "email": "...", "password": "..." }` | `{ "access_token": "...", "refresh_token": "..." }` |
| POST | `/auth/refresh` | Refresh token | `Authorization: Bearer <refresh_token>` | `{ "access_token": "...", "refresh_token": "..." }` |

### Users (`/users`)

| Method | Path | Description | Response |
|--------|------|-------------|----------|
| GET | `/users` | List all users | `[{ "name": "...", "email": "..." }]` |

## Project Structure

```
jwt-token/
├── src/main/java/com/ncasanovas/jwt_token/
│   ├── config/
│   │   ├── AppConfig.java
│   │   ├── DotenvConfig.java
│   │   ├── JwtAuthFilter.java
│   │   └── SecurityConfig.java
│   ├── controller/
│   │   ├── AuthController.java
│   │   └── UserController.java
│   ├── model/
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   ├── Token.java
│   │   ├── TokenResponse.java
│   │   ├── User.java
│   │   └── UserResponse.java
│   ├── repository/
│   │   ├── TokenRepository.java
│   │   └── UserRepository.java
│   └── service/
│       ├── AuthService.java
│       └── JwtService.java
├── src/main/resources/application.yml
├── src/test/
├── .env
├── pom.xml
└── README.md
```
