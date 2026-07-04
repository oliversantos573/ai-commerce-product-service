# Persistence Layer

# Visão Geral

A camada de persistência é responsável por armazenar e recuperar dados do banco.

Seguindo os princípios da Arquitetura Hexagonal, o domínio não conhece JPA, Hibernate ou PostgreSQL.

Toda comunicação acontece através de Ports.

---

# Arquitetura

```
Application Service
        │
        ▼
ProductRepositoryPort
        │
        ▼
ProductPersistenceAdapter
        │
        ▼
Spring Data Repository
        │
        ▼
PostgreSQL
```

---

# Objetivos

A camada de persistência deve:

- Persistir entidades
- Buscar dados
- Atualizar registros
- Excluir registros
- Implementar paginação
- Implementar ordenação

Sem expor detalhes da infraestrutura para o domínio.

---

# ProductRepositoryPort

O domínio depende apenas da interface.

```java
ProductRepositoryPort
```

Operações disponíveis:

- save()
- findById()
- findBySku()
- existsBySku()
- findAll()
- update()
- delete()

---

# ProductPersistenceAdapter

Implementa o ProductRepositoryPort.

Responsabilidades:

- Converter Domain → Entity
- Converter Entity → Domain
- Chamar o Spring Data
- Esconder detalhes do banco

---

# SpringDataProductRepository

Repository do Spring Data.

Exemplo:

```java
extends JpaRepository<ProductEntity, UUID>
```

Responsável por:

- CRUD
- Paginação
- Ordenação
- Queries automáticas

---

# ProductEntity

Representa a tabela do banco.

Não contém regra de negócio.

Exemplo:

```java
@Entity
@Table(name="products")
```

Campos:

- id
- sku
- name
- description
- brandId
- categoryId
- status
- createdAt
- updatedAt

---

# ProductEntityMapper

Responsável pela conversão.

```
Domain
↓

Entity
```

e

```
Entity
↓

Domain
```

Essa separação evita que o domínio conheça JPA.

---

# PostgreSQL

Banco relacional utilizado.

Motivos da escolha:

- Open Source
- Excelente performance
- ACID
- Amplo suporte
- Muito utilizado no mercado

---

# Flyway

Toda alteração do banco ocorre através de migrations.

Nunca alteramos tabelas manualmente.

Exemplo:

```
V1__create_products_table.sql
```

Benefícios:

- Histórico
- Versionamento
- Reprodutibilidade
- Ambientes sincronizados

---

# Paginação

Implementada utilizando Spring Data.

Exemplo:

```
GET /products?page=0&size=10
```

Internamente:

```java
PageRequest.of(
    page,
    size,
    Sort.by(direction, sortBy)
)
```

---

# Ordenação

Campos suportados:

- createdAt
- updatedAt
- sku
- name

Direções:

- ASC
- DESC

---

# Fluxo de Persistência

```
Controller

↓

Use Case

↓

Repository Port

↓

Persistence Adapter

↓

Spring Data

↓

Hibernate

↓

PostgreSQL
```

---

# Benefícios

Esta arquitetura oferece:

- Independência do banco
- Facilidade para testes
- Baixo acoplamento
- Troca simples de tecnologia
- Código limpo
- Alta manutenibilidade

---

# Evoluções Futuras

Planejadas:

- Soft Delete
- Auditoria
- Specification Pattern
- QueryDSL
- Optimistic Lock
- Cache Redis
- Multi-Tenant
- Read Replica