# Domain Model

# Visão Geral

O domínio representa o núcleo da aplicação.

Toda regra de negócio deve existir nesta camada.

O domínio não possui dependências de frameworks como:

- Spring Boot
- Hibernate
- JPA
- PostgreSQL
- Jackson

Essa independência garante alta testabilidade, baixo acoplamento e facilidade de evolução.

---

# Estrutura

```
domain
│
├── model
├── valueobject
├── enums
├── exception
└── service
```

---

# Aggregate Root

O Aggregate Root do Product Service é:

```
Product
```

Toda alteração relacionada ao produto deve passar por essa entidade.

---

# Entidade Product

Responsabilidades:

- Representar um produto
- Garantir consistência do domínio
- Controlar mudanças de estado
- Proteger invariantes

Campos principais:

- ProductId
- Sku
- Name
- Description
- BrandId
- CategoryId
- ProductStatus
- CreatedAt
- UpdatedAt

---

# Value Objects

O projeto utiliza Value Objects para encapsular conceitos do domínio.

## ProductId

Representa o identificador do produto.

```
UUID
```

---

## Sku

Representa o código único do produto.

Responsabilidades:

- Nunca ser nulo
- Nunca ser vazio
- Ser único no sistema

---

## BrandId

Identificador da marca.

---

## CategoryId

Identificador da categoria.

---

# Enum ProductStatus

Estados possíveis:

```
DRAFT

ACTIVE

INACTIVE

DISCONTINUED
```

Cada estado representa uma fase do ciclo de vida do produto.

---

# Invariantes

O domínio garante:

✔ SKU obrigatório

✔ Nome obrigatório

✔ Descrição obrigatória

✔ Marca obrigatória

✔ Categoria obrigatória

✔ Produto possui identificador único

✔ Datas de criação e atualização válidas

---

# Casos de Uso

Atualmente o domínio suporta:

- Criar Produto
- Buscar Produto
- Listar Produtos
- Atualizar Produto
- Excluir Produto

---

# Fluxo do Domínio

```
Controller

↓

Command

↓

Application Service

↓

Product

↓

Repository Port

↓

Persistence Adapter
```

---

# Regras de Negócio

As regras pertencem ao domínio.

Exemplos:

- Produto não pode possuir SKU duplicado.
- Um produto deve possuir uma marca.
- Um produto deve possuir uma categoria.
- Datas são controladas pelo domínio.
- O status inicial é DRAFT.

---

# Princípios Utilizados

O domínio segue os princípios de Domain-Driven Design (DDD).

### Entidades

Objetos com identidade.

Exemplo:

```
Product
```

---

### Value Objects

Objetos imutáveis definidos por valor.

Exemplos:

- ProductId
- Sku
- BrandId
- CategoryId

---

### Ubiquitous Language

Todo o código utiliza a linguagem do negócio.

Exemplos:

- Product
- Brand
- Category
- Status
- SKU

---

### Rich Domain Model

A lógica de negócio pertence às entidades do domínio, evitando um modelo anêmico.

---

# Benefícios

Esta abordagem proporciona:

- Baixo acoplamento
- Alta coesão
- Testes simplificados
- Independência de frameworks
- Evolução segura
- Código mais expressivo
- Facilidade para manutenção

---

# Evoluções Futuras

O domínio foi preparado para suportar:

- Product Images
- Product Variations
- Product Attributes
- Product Inventory
- Product Price History
- Product Events
- Product Audit
- Domain Events
- Event Sourcing (opcional)