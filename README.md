# AI-Commerce | Product Service

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blue)
![DDD](https://img.shields.io/badge/DDD-Domain--Driven--Design-red)
![Database](https://img.shields.io/badge/PostgreSQL-16-blue)
![Build](https://img.shields.io/badge/Build-Maven-red)
![Coverage](https://img.shields.io/badge/JaCoCo-97%25-brightgreen)
![Tests](https://img.shields.io/badge/Tests-JUnit5%20%7C%20Mockito-success)
![CI](https://img.shields.io/badge/GitHub_Actions-Passing-success)
![Quality](https://img.shields.io/badge/SonarCloud-Code_Quality-blue)

</p>

---

# AI-Commerce Platform

Enterprise Product Microservice developed with **Java 21**, following modern software engineering practices and enterprise architecture patterns.

This project is part of the **AI-Commerce Platform**, an ecosystem of cloud-native microservices built to simulate a real production environment using Domain-Driven Design, Hexagonal Architecture, Event-Driven Architecture and AWS-ready infrastructure.

---

# Objectives

- Build enterprise-grade Java microservices
- Apply Domain-Driven Design (DDD)
- Apply Hexagonal Architecture
- Follow SOLID Principles
- Create Rich Domain Models
- Implement Clean Architecture
- Achieve high automated test coverage
- Apply CI/CD
- Prepare the application for Kubernetes and AWS
- Integrate Artificial Intelligence services

---

# Current Module

## Product Service

Responsible for managing the product catalog.

Current features:

- ✅ Create Product
- ✅ Update Product
- ✅ Delete Product
- ✅ Find Product by Id
- ✅ List Products
- ✅ Pagination
- ✅ Bean Validation
- ✅ Rich Domain Model
- ✅ Value Objects
- ✅ Domain Events
- ✅ Global Exception Handler

---

# Architecture

```
                Client

                   │

        REST Controller (Inbound)

                   │

        Application Service Layer

                   │

             Use Cases / Commands

                   │

             Domain (Business Rules)

                   │

             Outbound Ports

                   │

        Persistence Adapter (JPA)

                   │

              PostgreSQL
```

The Domain Layer has **no dependency** on Spring Framework.

---

# Hexagonal Architecture

```
Adapters

↓

Application

↓

Domain

↓

Ports

↓

Infrastructure
```

---

# Technologies

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- Spring Security
- Bean Validation
- PostgreSQL
- Flyway
- Maven
- Docker
- Docker Compose
- Swagger / OpenAPI
- Lombok
- JUnit 5
- Mockito
- Spring Boot Test
- Testcontainers
- JaCoCo
- SonarCloud
- GitHub Actions

---

# Project Structure

```
src

├── adapters
│   ├── inbound
│   └── outbound
│
├── application
│   ├── command
│   ├── dto
│   ├── mapper
│   ├── query
│   └── service
│
├── domain
│   ├── event
│   ├── exception
│   ├── factory
│   ├── model
│   ├── ports
│   ├── shared
│   └── valueobject
│
├── infrastructure
│
└── config
```

---

# REST API

| Endpoint | Description |
|-----------|-------------|
| POST /products | Create Product |
| GET /products/{id} | Find Product |
| GET /products | List Products |
| PUT /products/{id} | Update Product |
| DELETE /products/{id} | Delete Product |

---

# API Documentation

Swagger UI

```
http://localhost:8081/swagger
```

OpenAPI

```
http://localhost:8081/api-docs
```

---

# Database

PostgreSQL

```
Host: localhost

Port: 5432

Database: productdb

Username: postgres

Password: postgres
```

---

# Flyway

Database migrations are versioned using Flyway.

Current migration

```
V1__create_products_table.sql
```

---

# Docker

Start database

```bash
docker compose up -d
```

Stop

```bash
docker compose down
```

---

# Running the Project

Clone repository

```bash
git clone https://github.com/oliversantos573/ai-commerce-product-service.git
```

Enter project

```bash
cd ai-commerce-product-service
```

Compile

```bash
mvn clean compile
```

Run

```bash
mvn spring-boot:run
```

---

# Testing

The project contains automated tests covering multiple layers of the application.

## Unit Tests

Frameworks:

- JUnit 5
- Mockito

Covered layers:

- Domain
- Application Services
- Mappers
- Value Objects
- Business Rules
- Domain Events

Execute

```bash
mvn test
```

---

# Integration Tests

Integration tests validate the interaction between:

- REST Controllers
- Spring Context
- Persistence Layer
- PostgreSQL
- Repository Adapters

Frameworks

- Spring Boot Test
- Testcontainers
- PostgreSQL Container

Execute

```bash
mvn verify
```

---

# Testcontainers

The project uses **Testcontainers** to execute integration tests against a real PostgreSQL container instead of an in-memory database.

Benefits

- Isolated environment
- Real database behavior
- Reliable integration testing
- Production-like execution

---

# Code Coverage

Coverage is generated automatically using **JaCoCo**.

Current coverage

```
97%
```

Generate report

```bash
mvn clean verify
```

Open

```
target/site/jacoco/index.html
```

---

# Code Quality

Static analysis is performed using **SonarCloud**.

Metrics analyzed:

- Bugs
- Vulnerabilities
- Code Smells
- Duplications
- Coverage
- Reliability
- Maintainability
- Security Hotspots

Run manually

```bash
mvn sonar:sonar
```

---

# Continuous Integration

GitHub Actions automatically executes:

- Checkout
- Java 21 Setup
- Build
- Unit Tests
- Integration Tests
- JaCoCo Report
- SonarCloud Analysis
- Package

Pipeline

```
Push

↓

Compile

↓

Unit Tests

↓

Integration Tests

↓

JaCoCo

↓

SonarCloud

↓

Package
```

---

# Quality Metrics

| Metric | Status |
|---------|--------|
| Java 21 | ✅ |
| Hexagonal Architecture | ✅ |
| DDD | ✅ |
| SOLID | ✅ |
| Unit Tests | ✅ |
| Integration Tests | ✅ |
| Mockito | ✅ |
| Spring Boot Test | ✅ |
| Testcontainers | ✅ |
| JaCoCo | ✅ |
| SonarCloud | ✅ |
| GitHub Actions | ✅ |
| Flyway | ✅ |
| Swagger | ✅ |

---

# Current Status

Completed

- ✅ Product CRUD
- ✅ Rich Domain Model
- ✅ Value Objects
- ✅ Domain Events
- ✅ Exception Handling
- ✅ PostgreSQL
- ✅ Flyway
- ✅ Docker
- ✅ Swagger
- ✅ Unit Tests
- ✅ Integration Tests
- ✅ Mockito
- ✅ Spring Boot Test
- ✅ Testcontainers
- ✅ JaCoCo
- ✅ SonarCloud
- ✅ GitHub Actions

---

# Roadmap

- [ ] Category Module
- [ ] Brand Module
- [ ] Inventory Module
- [ ] Pricing Module
- [ ] Order Module
- [ ] Payment Module
- [ ] Kafka Integration
- [ ] Redis Cache
- [ ] Kubernetes
- [ ] AWS ECS
- [ ] AWS EKS
- [ ] Terraform
- [ ] OpenTelemetry
- [ ] Prometheus
- [ ] Grafana
- [ ] Resilience4J
- [ ] AI Recommendation Engine

---

# Documentation

```
docs/

├── en
├── pt-BR
└── es
```

---

# Author

**Oliver Santos**

Java Backend Engineer

Specializing in:

- Java
- Spring Boot
- DDD
- Hexagonal Architecture
- AWS
- Artificial Intelligence

GitHub

```
https://github.com/oliversantos573
```

---

# License

This project is intended for educational purposes and to demonstrate enterprise software engineering practices.

---

<p align="center">

⭐ If you found this project useful, consider giving it a Star.

</p>