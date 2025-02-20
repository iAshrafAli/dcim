# 🏗️ Dynamic Category Management API

This is a **Spring Boot REST API** that allows creating and retrieving items with **category-based validation**. The allowed categories are dynamically **configured per environment** and can be updated without restarting the application.

---

## 🚀 **Features**
✔ Dynamic category configuration based on **Spring Profiles** (`dev`, `qa`, `prod`).  
✔ Reads allowed categories from **Spring properties files** per environment.  
✔ Implements **abstraction in the service layer**.  
✔ Includes **proper error handling and exception management**.  
✔ **Optional**: Can be configured to read categories from an **external file**.

---

## ⚙️ **Technology Stack**
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database (for development)**
- **PostgreSQL / MySQL (for production)**
- **Maven**
- **Lombok**
- **Swagger (API Documentation)**

---

## 📂 **Project Structure**
```
com.example.dcim
│── controller    # REST controllers
│── dto           # Data Transfer Objects (DTOs)
│── exception     # Custom exceptions & handlers
│── model         # Entity classes
│── repository    # JPA Repositories
│── service
│   ├── ItemService.java           # Service Interface
│   ├── ItemServiceImpl.java       # Service Implementation
└── DcimApplication.java  # Main Spring Boot entry point
```

---

## ⚙️ **Configuration**
The application reads **allowed categories dynamically** from Spring Boot's properties files.  

### 🔹 **Default Configuration (`application.properties`)**
```properties
# Default active profile
spring.profiles.active=dev

# Allowed categories (Will be overridden by environment-specific files)
app.allowed-categories=A,B
```

### 🔹 **Environment-Specific Configurations**
📄 **`application-dev.properties`**
```properties
app.allowed-categories=A,B
```
📄 **`application-qa.properties`**
```properties
app.allowed-categories=A,B,C
```
📄 **`application-prod.properties`**
```properties
app.allowed-categories=A,B,C
```

### **📌 Optional: Read Configuration from an External File**
- The application **can be configured** to read allowed categories from an **external configuration file**.  
- To enable this, provide an **external properties file** and specify its location when running the application:  

```bash
java -jar myapp.jar --spring.config.location=/config/application-external.properties
```

- This approach allows **modifying categories dynamically without restarting the application**.

---

## 🚀 **Running the Application**

### **1️⃣ Run with Maven**
```bash
mvn clean spring-boot:run
```

### **2️⃣ Run with a Specific Profile**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=qa
```

### **3️⃣ Run as a JAR**
```bash
mvn clean package
java -jar target/dcim-api.jar --spring.profiles.active=prod
```

---

## 📝 **API Endpoints**
### 🔹 **Create an Item**
```http
POST /items
```
**Request Body:**
```json
{
  "name": "Laptop",
  "category": "A"
}
```
**Response:**
```json
{
  "id": 1,
  "name": "Laptop",
  "category": "A"
}
```

### 🔹 **Get All Items**
```http
GET /items
```

---

## ⚠️ **Error Handling**
| Exception               | HTTP Status | Description |
|------------------------|------------|-------------|
| `InvalidCategoryException` | `400 Bad Request` | Category is not allowed |
| `NoResourceFoundException` | `404 Not Found` | Endpoint does not exist |

---

## 📜 **Swagger API Documentation**
The API is documented using **Swagger**.  
After starting the application, open:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🛠️ **Building & Deployment**
### 🔹 **Docker Support**
To run in a **Docker container**, build the image:
```bash
docker build -t dcim-api .
docker run -p 8080:8080 dcim-api
```

### 🔹 **Deploy to Production**
1. Set up a **database (PostgreSQL / MySQL)** and update `application-prod.properties`.
2. Use a **cloud service** (AWS, Azure, GCP) or **Docker** to deploy.

---

## ✨ **Contributing**
Pull requests are welcome! Please follow coding best practices and submit issues for bugs or enhancements.

---

## 📄 **License**
This project is **MIT Licensed**. You are free to use and modify it.

---

🚀 **Happy Coding!** 😃
