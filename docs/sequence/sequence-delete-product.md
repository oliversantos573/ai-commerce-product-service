# Sequence Diagram — Delete Product

> **AI-Commerce Platform**
>
> **Service:** Product Service  
> **Document:** Sequence Diagram - Delete Product  
> **Version:** 1.0  
> **Status:** Active

---

# Purpose

This document describes the execution flow of the **Delete Product** use case.

The Product Service adopts a **Soft Delete** strategy. Instead of physically removing records from the database, the Product Aggregate transitions its lifecycle state to **Deleted**, preserving historical information and ensuring business consistency.

The sequence follows the architectural principles of Domain-Driven Design (DDD), Hexagonal Architecture and Clean Architecture.

---

# Endpoint

| Method | Endpoint |
|---------|----------|
| DELETE | `/products/{id}` |

---

# Actors

| Actor | Responsibility |
|--------|----------------|
| Client | Sends the delete request |
| REST Controller | Receives and validates the HTTP request |
| DeleteProductUseCase | Coordinates the business workflow |
| Product Aggregate | Applies business rules and performs the soft delete |
| ProductRepositoryPort | Repository abstraction |
| Persistence Adapter | Implements persistence using Spring Data JPA |
| PostgreSQL | Stores product data |

---

# Preconditions

The following conditions must be satisfied before execution.

- The product identifier is valid.
- The product exists.
- The product has not already been deleted.
- The database is available.

---

# Sequence Diagram

```mermaid
sequenceDiagram

actor Client

participant Controller as REST Controller
participant UseCase as DeleteProductUseCase
participant Repository as ProductRepositoryPort
participant Aggregate as Product Aggregate
participant Adapter as Persistence Adapter
participant DB as PostgreSQL

Client->>Controller: DELETE /products/{id}

activate Controller

Note over Controller: Validate HTTP Request

Controller->>UseCase: execute(productId)

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

UseCase->>Aggregate: delete()

Note over Aggregate: Apply Soft Delete Rules

alt Product already deleted

Aggregate-->>UseCase: BusinessException

UseCase-->>Controller: Validation Error

Controller-->>Client: HTTP 409 Conflict

else Soft Delete Applied

critical Persist Aggregate

UseCase->>Repository: save(product)

Repository->>Adapter: save()

Adapter->>DB: UPDATE Product

DB-->>Adapter: Product Updated

Adapter-->>Repository: Updated Product

Repository-->>UseCase: Success

end

UseCase-->>Controller: No Content

Controller-->>Client: HTTP 204 No Content

end

deactivate UseCase
deactivate Controller
```

---

# Main Flow

| Step | Description |
|------|-------------|
| 1 | The client sends a **DELETE /products/{id}** request. |
| 2 | The REST Controller validates the HTTP request. |
| 3 | The Delete Product Use Case starts the business workflow. |
| 4 | The Product Aggregate is retrieved from the repository. |
| 5 | The aggregate applies the **Soft Delete** operation. |
| 6 | The updated aggregate is persisted through the Repository Port. |
| 7 | PostgreSQL updates the product state. |
| 8 | The client receives **HTTP 204 No Content**. |

---

# Alternative Flows

## Product Not Found

If the requested product does not exist:

- The repository cannot locate the aggregate.
- The use case throws **ProductNotFoundException**.
- The controller returns **HTTP 404 Not Found**.

---

## Product Already Deleted

If the product has already been deleted:

- The Product Aggregate rejects the operation.
- A business exception is thrown.
- The controller returns **HTTP 409 Conflict**.

---

## Infrastructure Failure

If the persistence layer is unavailable:

- The Persistence Adapter cannot update the aggregate.
- The request fails.
- The client receives **HTTP 500 Internal Server Error**.

---

# Business Rules Applied

The Product Aggregate enforces the following business rules.

- The product must exist.
- Products are deleted using **Soft Delete**.
- Deleted products cannot be deleted again.
- Aggregate consistency is validated before persistence.
- Infrastructure cannot bypass domain rules.

---

# Postconditions

After successful execution:

- The Product Aggregate remains consistent.
- The product status is updated to **Deleted**.
- Historical information is preserved.
- The client receives **HTTP 204 No Content**.

---

# Architecture Notes

### Domain-Driven Design

The Product Aggregate encapsulates the delete behavior and enforces all business rules.

---

### Hexagonal Architecture

Persistence is accessed exclusively through the ProductRepositoryPort.

The Domain Layer remains independent of Spring Framework and JPA.

---

### Dependency Inversion

The Application Layer communicates with persistence through abstractions.

---

### Transaction Boundary

The complete delete operation is executed within a single application transaction.

---

### Soft Delete Strategy

Products are never physically removed from the database.

The aggregate transitions to a **Deleted** state, preserving historical information for auditing, reporting and future integrations.

---

# Future Evolution

Future versions may publish a **ProductDeleted** domain event after successful persistence.

```text
Delete Product
        │
        ▼
Soft Delete Aggregate
        │
        ▼
Persist Aggregate
        │
        ▼
Publish ProductDeleted Event
        │
        ▼
Kafka
        │
        ├── Inventory Service
        ├── Search Service
        ├── Recommendation Service
        ├── Notification Service
        └── Audit Service
```

The event publication will be implemented through an **EventPublisherPort**, preserving the principles of Hexagonal Architecture.

---

# Related Documents

- C3 — Component Diagram
- Domain Model
- Hexagonal Architecture
- System Architecture
- ADR-001 — Hexagonal Architecture
- ADR-002 — Domain-Driven Design
- ADR-006 — Soft Delete