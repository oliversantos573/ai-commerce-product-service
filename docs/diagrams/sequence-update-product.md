# Update Product

```mermaid
sequenceDiagram

Client->>Controller: PUT /products/{id}

Controller->>UpdateProductService

UpdateProductService->>RepositoryPort: findById()

RepositoryPort->>PersistenceAdapter

PersistenceAdapter->>Database

Database-->>PersistenceAdapter

PersistenceAdapter-->>RepositoryPort

RepositoryPort-->>UpdateProductService

UpdateProductService->>Domain: update()

UpdateProductService->>RepositoryPort: save()

RepositoryPort->>PersistenceAdapter

PersistenceAdapter->>Database

Database-->>PersistenceAdapter

PersistenceAdapter-->>RepositoryPort

RepositoryPort-->>UpdateProductService

UpdateProductService-->>Controller

Controller-->>Client
```