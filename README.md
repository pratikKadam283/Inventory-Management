# 📦 Inventory Management System API

## 📝 Project Overview
This project is a **backend API** built with **Spring Boot** and **MySQL** to manage warehouse inventory.  
It allows you to **track products**, **update stock**, and **monitor low-stock items** efficiently.  

This API is designed with **MVC architecture**, clean code principles, and beginner-friendly structure, making it easy to understand and extend.

---

## 🚀 Features
- **Full CRUD Operations**: Create, Read, Update, and Delete products.
- **Stock Management**:  
  - Increase stock quantity  
  - Decrease stock quantity (cannot go below zero) ✅
- **Low Stock Alerts**: Each product has a `lowStockThreshold`; API can list products below this threshold.
- **Validation & Error Handling**: Invalid stock operations return **400 Bad Request**.
- **Unit Tests**: Covers stock addition, subtraction, and edge cases.

---

## 🛠️ Tech Stack
- **Java 17** (LTS)
- **Spring Boot 3.x** (MVC architecture)
- **MySQL 8.x** (Database)
- **Maven 3.8+** (Build & Dependency Management)
- **JUnit 5** (Unit Testing)
- **Lombok** (For cleaner code)

---

## ⚙️ Setup & Run Locally

### 1️⃣ Prerequisites
- Install **JDK 17**
- Install **Maven 3.8+**
- Install **MySQL 8.x**
- Use **IntelliJ IDEA** (or any Java IDE)

---

### 2️⃣ Clone the Repository

git clone https://github.com/pratikKadam283/Inventory-Management.git

cd inventory-management

   
3️⃣ Create MySQL database:
CREATE DATABASE inventorydb;

4️⃣ Update src/main/resources/application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/inventorydb?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


5️⃣ Import Project in IntelliJ

1. Open IntelliJ → Import Project → Select Maven.
2. Wait for dependencies to download.
3. Ensure your project structure shows src/main/java and src/test/java.
   
6️⃣ Run the API

API will start at:
http://localhost:8080/products

7️⃣ Available Endpoints
| Method | Endpoint                      | Description                             |
| ------ | ----------------------------- | --------------------------------------- |
| GET    | `/api/products`               | List all products                       |
| GET    | `/api/products/{id}`          | Get product by ID                       |
| POST   | `/api/products`               | Create a new product                    |
| PUT    | `/api/products/{id}`          | Update a product                        |
| DELETE | `/api/products/{id}`          | Delete a product                        |
| POST   | `/api/products/{id}/increase` | Increase stock                          |
| POST   | `/api/products/{id}/decrease` | Decrease stock                          |
| GET    | `/api/products/low-stock`     | List products below low-stock threshold |

8️⃣ Running Tests
Run all unit tests using Maven:
mvn test

✅ Tests cover:

Stock increase and decrease logic
Edge cases (e.g., trying to remove more stock than available)
Validation to prevent negative stock

💡 Assumptions & Design Choices
- Stock cannot go below zero — enforced in service layer and returns 400 Bad Request if violated.
- Each product has its own lowStockThreshold; default is 10 if not specified.
- MVC architecture is used: Controller → Service → Repository → Model.
- Lombok is used for getters/setters to keep code clean and readable.
- The API is beginner-friendly, with explicit validations, clear error messages, and simple structure for learning purposes.
