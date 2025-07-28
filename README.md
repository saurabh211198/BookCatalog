
# 📘 Book Catalog API – Spring Boot Project

This project is a simple RESTful Book Catalog API built using Spring Boot. It covers the complete flow of development from CRUD operations to caching, logging, and security.

---

## ✅ Features

- CRUD operations for `Book` entity
- Spring Data JPA with H2 database
- Swagger UI documentation
- Spring Security with Basic Auth
- Caching with `@Cacheable`
- Logging using SLF4J (Logback)

---

## 📂 Project Structure

```
BookCatalog
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.BookCatalog
│   │   │       ├── config
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller
│   │   │       │   └── BookController.java
│   │   │       ├── entity
│   │   │       │   └── Book.java
│   │   │       ├── repository
│   │   │       │   └── BookRepository.java
│   │   │       ├── service
│   │   │       │   └── UserDetailsServiceImpl.java
│   │   │       └── BookCatalogApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── queries.sql
│   └── test
├── README.md
├── .gitignore
├── BookCatalog.iml
```

---

## 🔐 Security

- Basic authentication is enabled using hardcoded username and password.
- Credentials:
- if Admin Logins
    - **Username:** `admin`
    - **Password:** `admin123`
- if User Logins
  - **Username:** `user`
  - **Password:** `user123`

---

## 🔁 API Endpoints

| Method | Endpoint      | Description         |
|--------|---------------|---------------------|
| GET    | `/books`      | Get all books       |
| GET    | `/books/{id}` | Get book by ID      |
| POST   | `/books`      | Create new book     |
| PUT    | `/books/{id}` | Update book         |
| DELETE | `/books/{id}` | Delete book         |
| PATCH  | `/books/{id}` | Parially Update book|

---

## 🔍 Swagger

- Access at: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 💾 H2 Database

- Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: *(leave blank)*

---

## 🚀 Run the App

1. Clone the repo
2. Open in IntelliJ or any IDE
3. Run `BookCatalogApplication.java`
4. Access Swagger UI or use Postman to test endpoints

---

## 💡 Caching Behavior

- `@Cacheable` is used on `GET /books`
- First call is **slow** (simulated DB delay)
- Next calls are **fast** (from cache)

---

## 📋 Logs

Logs are generated using SLF4J. Check console logs for:
- Endpoint access
- Cache hits/misses
