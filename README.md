# 🏗️ Dynamic Category Management API

This is a **Spring Boot REST API** that allows creating and retrieving items with **category-based validation**. 
The allowed categories are dynamically **configured per environment** and can be updated without restarting the application.

---

## 🚀 Features
✔ Dynamic category configuration based on **Spring Profiles** (`dev`, `qa`).  
✔ Reads allowed categories from **Spring properties files** per environment.  
✔ Implements **abstraction in the service layer**.  
✔ Includes **proper error handling and exception management**.  
✔ **JWT Authentication & Spring Security** for securing APIs.  
✔ **Optional**: Can be configured to read categories from an **external file**.

---

## ⚙️ Technology Stack
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security & JWT**
- **Spring Data JPA**
- **H2 Database (for development)**
- **Maven**
- **Lombok**

---

## 📂 Project Structure
```
com.example.dcim
│── config        # Security & JWT Configurations
│── controller    # REST controllers
│── dto           # Data Transfer Objects (DTOs)
│── exception     # Custom exceptions & handlers
│── entity        # Entity classes
│── repository    # JPA Repositories
│── service
│   ├── ItemService.java           # Service Interface
│   ├── ItemServiceImpl.java       # Service Implementation
└── DcimApplication.java  # Main Spring Boot entry point
```

---

## ⚙️ Configuration
The application reads **allowed categories dynamically** from Spring Boot's properties files.  

### 🔹 Default Configuration (`application.properties`)
```properties
# Default active profile
spring.profiles.active=dev

# Allowed categories (Will be overridden by environment-specific files)
app.allowed-categories=A,B
```

### 🔹 Environment-Specific Configurations
📄 `application-dev.properties`
```properties
app.allowed-categories=A,B
```
📄 `application-qa.properties`
```properties
app.allowed-categories=A,B,C
```

---

## 🔐 Security Setup (JWT Authentication)
- APIs are **protected using JWT (JSON Web Token)**.
- Users must **authenticate via `/auth/login`** to receive a token.
- Send the token in the `Authorization` header as:  
  ```
  Authorization: Bearer <your-token>
  ```
- API security is managed using **Spring Security 6.1+**.

### 🔑 Authentication API
| Method | Endpoint       | Description           | Auth Required |
|--------|--------------|-------------------|--------------|
| `POST` | `/auth/login`  | Get JWT Token      | ❌ No        |

**Request Body:**
```json
{
  "username": "admin",
  "password": "password"
}
```

**Curl Command:**
```sh
curl -X POST "http://localhost:8080/auth/login"      -H "Content-Type: application/json"      -d '{
           "username": "admin",
           "password": "password"
         }'
```

**Response:**
```json
{
  "token": "your-jwt-token"
}
```

---

## 🚀 Running the Application

### 1️⃣ Run with Maven
```bash
mvn clean spring-boot:run
```

### 2️⃣ Run with a Specific Profile
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=qa
```

### 3️⃣ Run as a JAR
```bash
mvn clean package
java -jar target/dcim-api.jar --spring.profiles.active=prod
```

---

## 📝 API Endpoints

### 🔹 Create an Item
| Method | Endpoint      | Description             | Auth Required |
|--------|-------------|---------------------|--------------|
| `POST` | `/items`     | Create a new item       | ✅ Yes        |

**Request Body:**
```json
{
  "name": "Laptop",
  "category": "A"
}
```

**Curl Command:**
```sh
curl -X POST "http://localhost:8080/items"      -H "Content-Type: application/json"      -H "Authorization: Bearer your-jwt-token"      -d '{
           "name": "Laptop",
           "category": "A"
         }'
```

**Response:**
```json
{
  "id": 1,
  "name": "Laptop",
  "category": "A",
  "message": "Item saved successfully"
}
```

---

## ⚠️ Error Handling
| Exception               | HTTP Status | Description |
|------------------------|------------|-------------|
| `InvalidCategoryException` | `400 Bad Request` | Category is not allowed |
| `NoResourceFoundException` | `404 Not Found` | Endpoint does not exist |
| `UnauthorizedException` | `401 Unauthorized` | JWT Token is missing or invalid |

---

## ✨ Future Enhancements
✅ **Read categories from an external file dynamically**  
✅ **Role-based authorization (ADMIN, USER)**  
✅ **Database integration for storing users**  
✅ **Swagger API Documentation**  

---

## 📄 License
This project is **MIT Licensed**. You are free to use and modify it.

---

🚀 **Happy Coding!** 😃
