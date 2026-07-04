# Hexagonal Architecture

```mermaid
flowchart LR

Client --> Controller

Controller --> InPort

InPort --> Service

Service --> Domain

Service --> OutPort

OutPort --> PersistenceAdapter

PersistenceAdapter --> PostgreSQL

Controller -.-> Swagger

PersistenceAdapter -.-> SpringData
```