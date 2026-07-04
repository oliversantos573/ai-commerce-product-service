# Arquitetura

# Visão Geral

O Product Service é um microsserviço responsável pelo gerenciamento de produtos da plataforma AI-Commerce.

O projeto foi desenvolvido utilizando uma combinação de princípios modernos de Engenharia de Software para produzir uma aplicação escalável, desacoplada e preparada para ambientes de produção.

Os principais pilares arquiteturais são:

- Hexagonal Architecture
- Domain Driven Design
- SOLID
- Clean Code
- Clean Architecture
- Ports and Adapters
- Dependency Inversion
- Rich Domain Model

---

# Objetivos

A arquitetura foi projetada para:

- Baixo acoplamento
- Alta coesão
- Fácil manutenção
- Fácil evolução
- Independência de frameworks
- Testabilidade
- Escalabilidade

---

# Arquitetura Hexagonal

```
                   External World

          REST API
              │
              │
      Inbound Adapter
              │
              │
        Inbound Port
              │
              │
     Application Service
              │
              │
           Domain
              │
              │
       Outbound Port
              │
              │
    Persistence Adapter
              │
              │
        PostgreSQL
```

---

# Camadas

## Domain

É o núcleo da aplicação.

Não conhece:

- Spring
- Hibernate
- JPA
- PostgreSQL

Toda regra de negócio pertence aqui.

Exemplos:

- Product
- ProductStatus
- ProductId
- Sku

---

## Application

Responsável por orquestrar os casos de uso.

Não implementa regra de negócio.

Funções:

- Executar casos de uso
- Validar fluxo
- Chamar portas
- Converter respostas

Exemplo:

CreateProductService

---

## Inbound

Recebe requisições externas.

Exemplo:

REST Controller

Responsabilidades:

- Receber JSON
- Validar Request
- Converter Request → Command
- Invocar Use Case

---

## Outbound

Implementa acesso a recursos externos.

Exemplos:

- Banco de dados
- Kafka
- Redis
- APIs externas

No Product Service:

ProductPersistenceAdapter

---

# Ports

As portas representam contratos.

## Inbound Ports

Definem casos de uso.

Exemplo:

CreateProductUseCase

---

## Outbound Ports

Definem dependências externas.

Exemplo:

ProductRepositoryPort

---

# Adapters

Os adapters implementam as portas.

Inbound

- REST Controller

Outbound

- Persistence Adapter

---

# Fluxo de uma Requisição

Cliente

↓

Controller

↓

Mapper

↓

Command

↓

Use Case

↓

Application Service

↓

Repository Port

↓

Persistence Adapter

↓

Spring Data

↓

PostgreSQL

---

# Estrutura de Pastas

```
adapters/

application/

domain/

config/
```

---

# Benefícios

Esta arquitetura oferece:

✔ Baixo acoplamento

✔ Alta coesão

✔ Fácil manutenção

✔ Testes independentes

✔ Escalabilidade

✔ Independência de frameworks

✔ Fácil migração de banco

✔ Fácil integração com mensageria

✔ Preparada para microsserviços

---

# Próximos passos

A arquitetura foi preparada para receber:

- Kafka
- Redis
- AWS SQS
- SNS
- EventBridge
- OpenSearch
- Elasticsearch
- OpenTelemetry
- Prometheus
- Grafana
- Kubernetes