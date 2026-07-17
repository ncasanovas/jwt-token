# JWT Token

API REST de autenticación con JWT usando Spring Boot, Spring Security y MySQL.

## Tecnologías

- **Java 17**
- **Spring Boot 4.1.0** (Web, Security, Data JPA)
- **MySQL 8** con Hibernate
- **jjwt 0.13.0** — generación y validación de tokens JWT
- **Lombok** — reducción de código repetitivo
- **dotenv-java** — variables de entorno

## Requisitos previos

- JDK 17+
- Maven 3.9+
- MySQL 8+

## Configuración

Crear un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
DB_URL=jdbc:mysql://localhost:3306/jwt_token
DB_USERNAME=root
DB_PASSWORD=tu_contraseña
JWT_SECRET_KEY=tu_clave_secreta_jwt
```

> El proyecto ya incluye un archivo `.env` de ejemplo ignorado por Git (ver `.gitignore`).

## Ejecución

### Con Maven

```bash
mvn spring-boot:run
```

### Con JAR

```bash
mvn clean package -DskipTests
java -jar target/jwt-token-0.0.1-SNAPSHOT.jar
```

La aplicación arranca en `http://localhost:8080`.

## Endpoints

### Autenticación (`/auth`)

| Método | Ruta | Descripción | Body / Headers | Respuesta |
|--------|------|-------------|----------------|-----------|
| POST | `/auth/register` | Registrar un nuevo usuario | `{ "email": "...", "password": "...", "name": "..." }` | `{ "access_token": "...", "refresh_token": "..." }` |
| POST | `/auth/login` | Iniciar sesión | `{ "email": "...", "password": "..." }` | `{ "access_token": "...", "refresh_token": "..." }` |
| POST | `/auth/refresh` | Refrescar token | `Authorization: Bearer <refresh_token>` | `{ "access_token": "...", "refresh_token": "..." }` |
| POST | `/auth/logout` | Cerrar sesión (invalida el token) | `Authorization: Bearer <access_token>` | `200 OK` |

### Usuarios (`/users`)

| Método | Ruta | Descripción | Respuesta |
|--------|------|-------------|-----------|
| GET | `/users` | Listar todos los usuarios | `[{ "name": "...", "email": "..." }]` |

## Estructura del proyecto

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
