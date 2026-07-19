# Sequence Diagram — Update Product

> **AI-Commerce Platform**
>
> **Service:** Product Service  
> **Document:** Sequence Diagram - Update Product  
> **Version:** 1.0  
> **Status:** Active

---

# Purpose

This document describes the execution flow of the **Update Product** use case.

The Product Service retrieves an existing Product Aggregate, validates business rules, applies the requested modifications and persists the updated aggregate.

The workflow follows the principles of Domain-Driven Design (DDD), Hexagonal Architecture and Clean Architecture while ensuring aggregate consistency and transactional integrity.

---

# Endpoint

| Method | Endpoint |
|---------|----------|
| PUT | `/products/{id}` |

---

# Actors

| Actor | Responsibility |
|--------|----------------|
| Client | Sends the update request |
| REST Controller | Receives and validates the HTTP request |
| UpdateProductUseCase | Coordinates the business workflow |
| Product Aggregate | Applies business rules and updates its state |
| ProductRepositoryPort | Repository abstraction |
| Persistence Adapter | Implements persistence using Spring Data JPA |
| PostgreSQL | Stores product information |

---

# Preconditions

The following conditions must be satisfied before execution.

- The product identifier is valid.
- The request payload is valid.
- The product exists.
- The database is available.

---

# Sequence Diagram

```mermaid
sequenceDiagram

actor Client

participant Controller as REST Controller
participant UseCase as UpdateProductUseCase
participant Repository as ProductRepositoryPort
participant Aggregate as Product Aggregate
participant Adapter as Persistence Adapter
participant DB as PostgreSQL

Client->>Controller: PUT /products/{id}

activate Controller

Note over Controller: Validate HTTP Request

Controller->>UseCase: execute(UpdateProductCommand)

activate UseCase

UseCase->>Repository: findById(productId)

Repository->>Adapter: findById()

Adapter->>DB: SELECT Product

DB-->>Adapter: Product

Adapter-->>Repository: Product Aggregate

Repository-->>UseCase: Product Aggregate

alt Product not found

UseCase-->>Controller: ProductNotFoundException

Controller-->>Client: HTTP 404 Not Found

else Product found

UseCase->>Aggregate: update(command)

Note over Aggregate: Validate Business Invariants

alt Business validation failed

Aggregate-->>UseCase: BusinessException

UseCase-->>Controller: Validation Error

Controller-->>Client: HTTP 422 Unprocessable Entity

else Aggregate updated

critical Persist Aggregate

UseCase->>Repository: save(product)

Repository->>Adapter: save()

Adapter->>DB: UPDATE Product

DB-->>Adapter: Product Updated

Adapter-->>Repository: Updated Product

Repository-->>UseCase: Success

end

UseCase-->>Controller: ProductResponse

Controller-->>Client: HTTP 200 OK

end

deactivate UseCase
deactivate Controller
```

---

# Main Flow

| Step | Description |
|------|-------------|
| 1 | The client sends a **PUT /products/{id}** request. |
| 2 | The REST Controller validates the request payload and path parameter. |
| 3 | The Update Product Use Case starts the business workflow. |
| 4 | The Product Aggregate is retrieved from the repository. |
| 5 | The aggregate validates business rules and applies the requested changes. |
| 6 | The updated aggregate is persisted through the Repository Port. |
| 7 | PostgreSQL stores the updated product. |
| 8 | The client receives **HTTP 200 OK** with the updated resource. |

---

# Alternative Flows

## Product Not Found

If the specified product does not exist:

- The repository returns no aggregate.
- The use case throws **ProductNotFoundException**.
- The controller returns **HTTP 404 Not Found**.

---

## Business Validation Failure

If any business invariant is violated:

- The Product Aggregate rejects the update.
- A business exception is thrown.
- The controller returns **HTTP 422 Unprocessable Entity**.

---

## Invalid Request

If the request payload is invalid:

- Bean Validation rejects the request.
- The use case is not executed.
- The client receives **HTTP 400 Bad Request**.

---

## Infrastructure Failure

If the persistence layer is unavailable:

- The Persistence Adapter cannot persist the aggregate.
- The request fails.
- The client receives **HTTP 500 Internal Server Error**.

---

# Business Rules Applied

The Product Aggregate enforces the following business rules.

- The product must exist.
- Product identifiers are immutable.
- SKU uniqueness is preserved according to business rules.
- Deleted products cannot be updated.
- Aggregate consistency is validated before persistence.
- Business invariants are enforced by the aggregate.

---

# Postconditions

After successful execution:

- The Product Aggregate reflects the updated business state.
- The aggregate has been successfully persisted.
- Business invariants have been preserved.
- The client receives **HTTP 200 OK** with the updated representation.

---

# Architecture Notes

### Domain-Driven Design

The Product Aggregate owns all business rules related to product updates.

---

### Hexagonal Architecture

Persistence is accessed exclusively through the ProductRepositoryPort.

The Domain Layer remains completely independent from Spring Boot, JPA and PostgreSQL.

---

### Dependency Inversion

The Application Layer depends only on abstractions defined by the domain.

---

### Transaction Boundary

The complete update operation is executed within a single application transaction, guaranteeing aggregate consistency.

---

### Validation Strategy

Validation is performed in multiple stages:

- HTTP request validation.
- Bean Validation.
- Business validation inside the Product Aggregate.

This layered validation strategy keeps responsibilities properly separated.

---

# Future Evolution

Future versions may publish a **ProductUpdated** domain event after successful persistence.

```text
Update Product
        │
        ▼
Update Product Aggregate
        │
        ▼
Persist Aggregate
        │
        ▼
Publish ProductUpdated Event
        │
        ▼
Kafka
        │
        ├── Inventory Service
        ├── Search Service
        ├── Recommendation Service
        ├── Notification Service
        ├── Pricing Service
        └── Audit Service
```

The event publication will be implemented through an **EventPublisherPort**, preserving the principles of Hexagonal Architecture and enabling asynchronous integration with other platform services.

---

# Related Documents

- C3 — Component Diagram
- Domain Model
- Hexagonal Architecture
- System Architecture
- Sequence Diagram — Request Lifecycle
- ADR-001 — Hexagonal Architecture
- ADR-002 — Domain-Driven Design
- ADR-003 — Repository Pattern