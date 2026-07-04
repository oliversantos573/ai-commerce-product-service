# C2 - Container Diagram

```mermaid
flowchart TB

API["REST API"]

Application["Application Layer"]

Domain["Domain Layer"]

Persistence["Persistence Adapter"]

Repository["Spring Data JPA"]

Database[("PostgreSQL")]

Swagger["Swagger"]

API --> Application

Application --> Domain

Application --> Persistence

Persistence --> Repository

Repository --> Database

Swagger --> API
```

## Containers

### REST API

Spring MVC Controllers.

### Application

Use Cases.

### Domain

Business Rules.

### Persistence

Outbound Adapter.

### PostgreSQL

Persistent storage.