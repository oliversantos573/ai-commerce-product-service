# AI-Commerce | Product Service

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-brightgreen)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blue)
![DDD](https://img.shields.io/badge/DDD-Domain_Driven_Design-red)
![Build](https://img.shields.io/badge/Build-Maven-blue)
![Database](https://img.shields.io/badge/PostgreSQL-16-blue)
![Flyway](https://img.shields.io/badge/Flyway-Versioned_Migrations-red)
![Status](https://img.shields.io/badge/Status-In_Development-yellow)

</p>

---

# AI-Commerce

Enterprise E-Commerce Platform built with modern Java.

The purpose of this project is to build a production-ready e-commerce backend applying Software Engineering best practices, Clean Architecture, Domain-Driven Design (DDD), Hexagonal Architecture, AWS Cloud patterns, Event-Driven Architecture and Artificial Intelligence integrations.

This repository is part of a larger ecosystem where each business capability is implemented as an independent microservice.

---

# Project Goals

- Learn Enterprise Java Development
- Apply Hexagonal Architecture
- Apply Domain Driven Design
- Build Production Ready APIs
- Follow SOLID Principles
- Event Driven Architecture
- Cloud Native Design
- AWS Ready
- AI Ready
- High Test Coverage

---

# Current Module

```
Product Service
```

Responsible for product management.

Current features:

- Create Product
- Find Product by Id
- List Products (Pagination)
- Update Product
- Delete Product

---

# Architecture

```
                  Client

                     │

          REST Controller (Inbound)

                     │

              Use Case (Application)

                     │

              Inbound Port

                     │

                  Domain

                     │

              Outbound Port

                     │

        Persistence Adapter (JPA)

                     │

              PostgreSQL
```

---

# Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven
- Docker
- Docker Compose
- Swagger OpenAPI
- Bean Validation
- Lombok
- Hexagonal Architecture
- Domain Driven Design

---

# Project Structure

```
src

 ├── adapters
 │
 ├── application
 │
 ├── domain
 │
 ├── infrastructure
 │
 └── config
```

---

# Hexagonal Architecture

```
Inbound Adapter

↓

Inbound Port

↓

Application Service

↓

Domain

↓

Outbound Port

↓

Outbound Adapter

↓

Database
```

The domain layer has no dependency on frameworks.

---

# Current CRUD

| Endpoint | Status |
|-----------|--------|
| POST /products | ✅ |
| GET /products/{id} | ✅ |
| GET /products | ✅ |
| PUT /products/{id} | ✅ |
| DELETE /products/{id} | ✅ |

---

# API Documentation

After running the application:

```
http://localhost:8081/swagger
```

OpenAPI JSON

```
http://localhost:8081/api-docs
```

---

# Running the Project

Clone

```bash
git clone https://github.com/SEU-USUARIO/product-service.git
```

Docker

```bash
docker compose up -d
```

Run

```bash
mvn spring-boot:run
```

---

# Database

PostgreSQL

```
localhost:5432
```

Database

```
productdb
```

Username

```
postgres
```

Password

```
postgres
```

---

# Flyway

Database migrations are versioned using Flyway.

Current migration

```
V1__create_products_table.sql
```

---

# Roadmap

## Phase 1

- [x] Product CRUD

## Phase 2

- [ ] Category CRUD

## Phase 3

- [ ] Brand CRUD

## Phase 4

- [ ] Inventory

## Phase 5

- [ ] Pricing

## Phase 6

- [ ] Orders

## Phase 7

- [ ] Payments

## Phase 8

- [ ] Notifications

## Phase 9

- [ ] Kafka Integration

## Phase 10

- [ ] Redis Cache

## Phase 11

- [ ] AWS Deployment

## Phase 12

- [ ] AI Integration

---

# Future Improvements

- JWT Authentication
- OAuth2
- Keycloak
- OpenTelemetry
- Prometheus
- Grafana
- Resilience4J
- Testcontainers
- Kubernetes
- ECS
- SQS
- SNS
- EventBridge

---

# Documentation

Detailed documentation is available in:

```
docs/

├── en
├── pt-BR
└── es
```

---

# Author

Oliver Santos

Java Backend Engineer

Enterprise Java • Spring Boot • AWS • AI

---

# License

This project is for educational purposes.