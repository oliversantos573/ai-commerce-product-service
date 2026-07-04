# Delete Product

```mermaid
sequenceDiagram

Client->>Controller: DELETE /products/{id}

Controller->>DeleteProductService

DeleteProductService->>RepositoryPort: findById()

RepositoryPort->>PersistenceAdapter

PersistenceAdapter->>Database

Database-->>PersistenceAdapter

PersistenceAdapter-->>RepositoryPort

RepositoryPort-->>DeleteProductService

DeleteProductService->>Domain: delete()

DeleteProductService->>RepositoryPort: save()

RepositoryPort->>PersistenceAdapter

PersistenceAdapter->>Database

Database-->>PersistenceAdapter

PersistenceAdapter-->>RepositoryPort

RepositoryPort-->>DeleteProductService

DeleteProductService-->>Controller

Controller-->>Client
```