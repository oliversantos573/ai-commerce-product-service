# C3 - Product Service Components

```mermaid
flowchart LR

Controller

CreateService

UpdateService

DeleteService

GetService

ListService

RepositoryPort

PersistenceAdapter

SpringRepository

Database[(PostgreSQL)]

Controller --> CreateService

Controller --> UpdateService

Controller --> DeleteService

Controller --> GetService

Controller --> ListService

CreateService --> RepositoryPort

UpdateService --> RepositoryPort

DeleteService --> RepositoryPort

GetService --> RepositoryPort

ListService --> RepositoryPort

RepositoryPort --> PersistenceAdapter

PersistenceAdapter --> SpringRepository

SpringRepository --> Database
```

## Components

### Controller

REST Endpoints.

### Services

Application Use Cases.

### Repository Port

Application abstraction.

### Persistence Adapter

Infrastructure implementation.

### Spring Data Repository

JPA Repository.

### PostgreSQL

Database.