# Product Service

---

# Overview

The Product Service is responsible for managing the product catalog within the AI-Commerce platform.

It follows a clean separation of responsibilities using Hexagonal Architecture (Ports & Adapters) combined with Domain Driven Design (DDD).

The service exposes REST APIs while keeping the domain completely independent from frameworks.

---

# Responsibilities

The Product Service is responsible for:

- Product registration
- Product updates
- Product retrieval
- Product deletion
- Product listing with pagination
- SKU uniqueness validation
- Product lifecycle management

Future responsibilities include:

- Product images
- Product specifications
- Categories
- Brands
- Search
- Inventory integration

---

# Architecture

```
               REST API
                   │
                   ▼
         Inbound Adapters
                   │
                   ▼
            Application
      (Use Cases / Services)
                   │
        Inbound / Outbound Ports
                   │
                   ▼
              Domain
          Business Rules
                   │
                   ▼
        Persistence Adapter
                   │
                   ▼
             PostgreSQL
```

---

# Layers

## Inbound Adapters

Responsible for exposing REST endpoints.

Examples:

- ProductController
- Request DTOs
- Response DTOs
- Request Mapper

Responsibilities:

- Receive HTTP requests
- Validate inputs
- Convert DTOs into Commands
- Call Use Cases

---

## Application Layer

Contains the application use cases.

Current services:

- CreateProductService
- GetProductByIdService
- ListProductsService
- UpdateProductService
- DeleteProductService

Responsibilities:

- Coordinate business flow
- Call domain model
- Communicate with Ports

---

## Domain Layer

Contains the business rules.

Examples:

- Product
- ProductStatus
- ProductId
- Sku
- ProductName

The domain contains **no Spring dependencies**.

---

## Outbound Adapters

Responsible for persistence.

Current adapter:

ProductPersistenceAdapter

Responsibilities:

- Save products
- Query products
- Update products
- Delete products

---

# Implemented Endpoints

## Create Product

POST

```
/api/v1/products
```

Creates a new product.

Validation includes:

- Unique SKU
- Required fields
- Business rules

---

## Get Product

GET

```
/api/v1/products/{id}
```

Returns a single product.

---

## List Products

GET

```
/api/v1/products
```

Supports:

- Pagination
- Sorting
- Ascending/Descending order

Example:

```
?page=0
&size=10
&sortBy=createdAt
&direction=DESC
```

---

## Update Product

PUT

```
/api/v1/products/{id}
```

Updates product information.

---

## Delete Product

DELETE

```
/api/v1/products/{id}
```

Removes the product.

Returns:

```
204 No Content
```

---

# Current Domain Model

```
Product

├── ProductId
├── Sku
├── Name
├── Description
├── BrandId
├── CategoryId
├── Status
├── CreatedAt
└── UpdatedAt
```

---

# Value Objects

Current Value Objects:

- ProductId
- Sku
- ProductName
- ProductDescription

Benefits:

- Encapsulation
- Validation
- Immutable objects
- Rich domain model

---

# Design Principles

The project follows:

- SOLID
- Clean Architecture
- Hexagonal Architecture
- Domain Driven Design
- Clean Code

---

# Dependency Rule

```
Adapters
     │
Application
     │
Domain
```

Dependencies always point inward.

The domain never depends on Spring.

---

# Persistence

Database:

PostgreSQL

ORM:

Spring Data JPA

Migration:

Flyway

---

# Pagination

Implemented using:

Spring Data Page

Supports:

- page
- size
- sortBy
- direction

Example:

```
GET /products?page=0&size=20
```

---

# Validation

Uses Jakarta Validation.

Examples:

- @NotBlank
- @NotNull

Business validations are performed inside the domain layer.

---

# Exception Handling

Centralized using:

GlobalExceptionHandler

Provides standardized responses.

Example:

```
404

{
    "status":404,
    "message":"Product not found"
}
```

---

# Project Structure

```
adapters

    inbound

        controller

        mapper

        request

        response

    outbound

        persistence

application

    command

    query

    dto

    service

    port

domain

    model

    valueobject

    exception

    enums
```

---

# Current Features

| Feature | Status |
|----------|--------|
| Create Product | ✅ |
| Get Product | ✅ |
| List Products | ✅ |
| Update Product | ✅ |
| Delete Product | ✅ |
| Pagination | ✅ |
| Flyway | ✅ |
| Validation | ✅ |
| Swagger | ✅ |

---

# Future Improvements

- Product Images
- Product Specifications
- Categories CRUD
- Brands CRUD
- Search API
- Elasticsearch
- Redis Cache
- Kafka Events
- AWS S3
- OpenTelemetry
- Prometheus
- Grafana

---

# Testing Strategy

Planned:

- Unit Tests
- Integration Tests
- Testcontainers
- MockMvc
- JUnit 5
- Mockito

---

# Author

AI-Commerce

Enterprise Java Study Project

Built with Java 21, Spring Boot, Hexagonal Architecture and Domain Driven Design.