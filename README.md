# hms-backend
Hospital Management System – Backend

The Hospital Management System (HMS) Backend is a RESTful API built using Spring Boot that powers the core business logic of the hospital management platform. It handles authentication, authorization, role-based access control, and all hospital-related operations such as managing users, doctors, patients, appointments, prescriptions, and billing.

The backend is designed following layered architecture and best practices to ensure scalability, security, and maintainability.

Backend Overview

The backend is developed using Spring Boot with Spring Security and JWT-based authentication. It exposes REST APIs consumed by the React frontend.

Key responsibilities of the backend include:

User authentication & authorization

Role-based access control (Admin, Doctor, Receptionist, Patient)

Business logic implementation

Database interaction using Spring Data JPA

Secure communication with the frontend

Technologies Used

Java 21

Spring Boot

Spring Security

JWT (JSON Web Tokens)

Spring Data JPA (Hibernate)

MySQL

Maven

RESTful APIs

Key Features

JWT-based authentication and authorization

Role-based access control (RBAC)

Secure REST APIs

Centralized exception handling

Clean layered architecture

Database persistence using JPA & Hibernate

Roles & Responsibilities
Admin

Manages users and system configurations

Adds and manages doctors

Views and manages all patients and appointments

Manages blood donor information

Has full system access

Receptionist

Registers new patients

Books and cancels appointments

Views doctor availability and schedules

Assists patients with administrative tasks

Doctor

Views assigned patients

Manages appointments and availability

Updates prescriptions and medical notes

Patient

Registers and manages profile

Books and views appointments

Views medical history and prescriptions

Authentication & Authorization Flow

User logs in via /auth/login

Backend validates credentials

JWT token is generated and returned

Frontend sends JWT in Authorization header

JwtFilter validates token for each request

Access is granted based on user role

Project Structure
src/main/java
└── com.backend
    │
    ├── BackendApplication.java
    │
    ├── config/                     # Application & global configs
    │   ├── CorsConfig.java
    │   └── SwaggerConfig.java
    │
    ├── security/                   # Spring Security & JWT
    │   ├── SecurityConfig.java
    │   ├── JwtAuthenticationFilter.java
    │   ├── JwtUtil.java
    │   └── CustomUserDetailsService.java
    │
    ├── controller/                 # REST Controllers
    │   ├── auth/
    │   │   └── AuthController.java
    │   ├── admin/
    │   │   └── AdminController.java
    │   ├── doctor/
    │   │   └── DoctorController.java
    │   ├── patient/
    │   │   └── PatientController.java
    │   └── receptionist/
    │       └── ReceptionistController.java
    │
    ├── service/                    # Business logic interfaces
    │   ├── AuthService.java
    │   ├── AdminService.java
    │   ├── DoctorService.java
    │   ├── PatientService.java
    │   └── ReceptionistService.java
    │   ├── AuthServiceImpl.java
    │   ├── AdminServiceImpl.java
    │   ├── DoctorServiceImpl.java
    │   ├── PatientServiceImpl.java
    │   └── ReceptionistServiceImpl.java
    │
    ├── repository/                 # Spring Data JPA repositories
    │   ├── UserRepository.java
    │   ├── DoctorRepository.java
    │   ├── PatientRepository.java
    │   ├── AppointmentRepository.java
    │   ├── PrescriptionRepository.java
    │   └── BillingRepository.java
    │
    ├── entity/                     # JPA Entities
    │   ├── User.java
    │   ├── Role.java
    │   ├── Doctor.java
    │   ├── Patient.java
    │   ├── Appointment.java
    │   ├── Prescription.java
    │   └── Bill.java
    │
    ├── dto/                        # Request/Response DTOs
    │   │   ├── LoginRequest.java
    │   │   ├── RegisterRequest.java
    │   │   └── AppointmentRequest.java
    │       ├── AuthResponse.java
    │       ├── AppointmentResponse.java
    │       └── PrescriptionResponse.java
    │
    ├── exception/                  # Custom exceptions
    │   ├── ResourceNotFoundException.java
    │   
    │   
    │
    ├── exception_handler/          # Global exception handling
    │   └── GlobalExceptionHandler.java
    │
    └── util/                       # Utility & constants
        ├── Constants.java
        └── DateUtil.java


Installation & Setup
Clone the repository
git clone https://github.com/caresync-hms/hms-backend.git
cd hms-backend

Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/hms_db
spring.datasource.username=your_username
spring.datasource.password=your_password

Build & Run
mvn clean install
mvn spring-boot:run

API Base URL
http://localhost:8080/api

Frontend Repository

🔗 https://github.com/caresync-hms/hms-frontend
