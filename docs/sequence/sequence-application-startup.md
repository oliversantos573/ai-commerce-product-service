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

Enterprise Product Microservice developed with **Java 21** following modern software engineering practices and enterprise architecture principles.

The **Product Service** is part of the **AI-Commerce Platform**, a cloud-native ecosystem designed to simulate a real production environment using:

- Domain-Driven Design (DDD)
- Hexagonal Architecture
- Clean Architecture principles
- Event-Driven Architecture
- Automated Testing
- Continuous Integration
- Cloud-ready infrastructure

---

# Overview

The Product Service is responsible for managing the product catalog domain.

The service provides capabilities for:

- Product creation
- Product updates
- Product removal
- Product retrieval
- Product listing
- Business rule validation
- Domain event publishing

---

# Features

Current implemented features:

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
- ✅ Global Exception Handling
- ✅ PostgreSQL Persistence
- ✅ Flyway Database Migration

---

# Architecture

The Product Service follows **Hexagonal Architecture (Ports and Adapters)**.

High-level flow:

```text
                 Client

                   │

          REST API Adapter

                   │

          Application Layer

          Use Cases / Commands

                   │

             Domain Core

     Entities / Value Objects / Rules

                   │

              Domain Ports

                   │

        Infrastructure Adapters

                   │

              PostgreSQL