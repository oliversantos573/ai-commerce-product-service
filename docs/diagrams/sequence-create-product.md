# Create Product

```mermaid
sequenceDiagram

Client->>Controller: POST /products

Controller->>CreateProductService: execute()

CreateProductService->>RepositoryPort: existsBySku()

RepositoryPort->>PersistenceAdapter

PersistenceAdapter->>PostgreSQL

PostgreSQL-->>PersistenceAdapter

PersistenceAdapter-->>RepositoryPort

RepositoryPort-->>CreateProductService

CreateProductService->>RepositoryPort: save()

RepositoryPort->>PersistenceAdapter

PersistenceAdapter->>PostgreSQL

PostgreSQL-->>PersistenceAdapter

PersistenceAdapter-->>RepositoryPort

RepositoryPort-->>CreateProductService

CreateProductService-->>Controller

Controller-->>Client
```