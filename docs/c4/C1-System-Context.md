# C1 - System Context

```mermaid
flowchart LR

Developer["Developer"]

Admin["Administrator"]

Customer["Customer"]

ProductService["Product Service"]

PostgreSQL[("PostgreSQL")]

GitHub["GitHub"]

Swagger["Swagger UI"]

Developer --> Swagger
Developer --> GitHub

Admin --> ProductService

Customer --> ProductService

ProductService --> PostgreSQL
```

## Description

The Product Service is responsible for managing products within the AI-Commerce platform.

External users interact through REST APIs.

Persistence is handled by PostgreSQL.

Source code is managed using GitHub.