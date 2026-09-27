# 🚀 Authentication System (Spring Boot + JWT + RBAC)

A production-ready backend authentication system built using **Spring Boot**, implementing **JWT-based authentication** and **Role-Based Access Control (RBAC)**.

Designed with clean architecture, secure practices, and real-world API workflows — aligned with **product-based company standards**.

---
## 🏗️ System Architecture

![Architecture](./images/Authentications%20system%20architecture.png)


---
## 🔥 Key Features

- ✅ User Registration & Login  
- 🔐 JWT Token Generation & Validation  
- 🛡️ Role-Based Access Control (USER / ADMIN)  
- 🔑 Protected APIs using Spring Security  
- 📦 MySQL Database Integration  
- 📄 Swagger API Documentation  
- ⚡ Stateless Authentication (No session storage)

---

## 🏗️ Tech Stack

- **Backend:** Spring Boot, Spring Security  
- **Authentication:** JWT (JJWT)  
- **Database:** MySQL  
- **ORM:** Hibernate (JPA)  
- **API Docs:** Swagger (OpenAPI)  
- **Build Tool:** Maven  

---

## 📌 API Endpoints

### 🔐 Auth APIs

| Method | Endpoint | Description |
|-------|--------|------------|
| POST | `/api/v1/auth/register` | Register new user |
| POST | `/api/v1/auth/login` | Login & get JWT |

---

### 👤 User APIs

| Method | Endpoint | Access |
|-------|--------|--------|
| GET | `/api/v1/users/me` | Authenticated User |
| GET | `/api/v1/users` | ADMIN Only |
| GET | `/api/v1/users/{id}` | ADMIN Only |
| DELETE | `/api/v1/users/{id}` | ADMIN Only |

---

## 🧪 API Testing (Swagger Evidence)

👉 Swagger UI:  
http://localhost:8080/swagger-ui/index.html

---

### 🔹 User Flow

- 🔗 Register Request  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/User%20Registration%20API%20-%20request_.jpeg  

- 🔗 Register Success  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/User%20Registration%20API-Success%20response_.jpeg  

- 🔗 Login Request  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/User%20Login%20-%20request_.jpeg  

- 🔗 Login Response (JWT Generated)  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/User%20Login%20-%20JWT%20Token%20Generated%20respose_.jpeg  

- 🔗 Swagger Authorization (JWT)  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/Swagger%20Authorization%20with%20JWT_.jpeg  

- 🔗 Protected API - Current User  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/Protected%20API%20-%20Get%20Current%20Logged-in%20User.jpeg  

---

### 🔹 Role-Based Access

- 🔗 Forbidden Access (USER trying ADMIN API)  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/Role-Based%20Access%20-%20403%20Forbidden%20for%20user.jpeg  

- 🔗 Admin Login Request  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/Admin%20Login%20-%20request_.jpeg  

- 🔗 Admin Login Response (JWT)  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/Admin%20Login%20-%20JWT%20Token%20Generated%20response_.jpeg  

- 🔗 Admin Access - Fetch All Users  
  https://github.com/RutujaKavilkar/Authentication-System/blob/main/images/Admin%20Access%20-%20Fetch%20All%20Users%20(Authorized)_.jpeg  

---

## 🔐 Security Flow (How It Works)

1. User logs in → receives JWT  
2. JWT is sent in header:
   ```
   Authorization: Bearer <token>
   ```
3. `JwtAuthenticationFilter` intercepts request  
4. Token validated using `JwtService`  
5. Spring Security sets authentication  
6. Role-based authorization applied  

---

## 🗄️ Database Design

- `users`  
- `roles`  
- `user_roles` (Many-to-Many mapping)

Supports multiple roles per user:
```
ROLE_USER
ROLE_ADMIN
```

---

## ⚙️ Setup & Run

```bash
git clone https://github.com/RutujaKavilkar/Authentication-System.git
cd Authentication-System
```

### Configure MySQL in `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

---

## 🧠 Highlights

- “I implemented stateless authentication using JWT.”
- “Used Spring Security filters to validate tokens.”
- “Designed role-based access using a user_roles mapping table.”
- “Handled 403 authorization scenarios for restricted APIs.”
- “Ensured secure password storage using BCrypt.”

---

## 🚀 Future Enhancements

- Refresh Token Mechanism  
- OAuth2 / Google Login  
- Email Verification  
- Rate Limiting  
- Docker Deployment  

---

## 👩‍💻 Author

**Rutuja Kavilkar**  
- GitHub: https://github.com/RutujaKavilkar  
- LinkedIn: https://linkedin.com/in/rutuja-kavilkar  

---

This project demonstrates **real-world backend authentication design**, focusing on **security, scalability, and clean architecture** — aligned with expectations of top product-based companies.
