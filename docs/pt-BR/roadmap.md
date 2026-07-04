# Product Roadmap

## Visão Geral

O AI-Commerce é uma plataforma de e-commerce construída utilizando Java 21, Spring Boot, DDD, Clean Architecture, Arquitetura Hexagonal e princípios de Cloud Native.

Este roadmap apresenta a evolução planejada da plataforma, desde um único microsserviço até um ecossistema completo baseado em eventos.

---

# Status Atual

## Product Service

Status:

✅ Concluído

Funcionalidades implementadas:

- Cadastro de Produtos
- Consulta por ID
- Listagem paginada
- Atualização
- Exclusão lógica
- Validação
- Tratamento global de exceções
- Flyway
- Swagger
- Arquitetura Hexagonal
- DDD
- Clean Architecture

---

# Fase 1

## Qualidade

Objetivo:

Garantir confiabilidade da aplicação.

Planejamento:

- Testes Unitários
- Testes de Integração
- Testcontainers
- Cobertura com JaCoCo
- SonarQube

Status:

🟡 Em Planejamento

---

# Fase 2

## Segurança

Objetivo:

Preparar a API para produção.

Planejamento:

- Spring Security
- JWT
- OAuth2
- Roles
- Permissions
- API Key

Status:

🟡 Planejado

---

# Fase 3

## Observabilidade

Planejamento:

- Actuator
- Prometheus
- Grafana
- Micrometer
- OpenTelemetry
- Distributed Tracing

Status:

🟡 Planejado

---

# Fase 4

## Infraestrutura

Planejamento:

- Docker
- Docker Compose
- GitHub Actions
- AWS ECS
- Kubernetes
- Helm
- Terraform

Status:

🟡 Planejado

---

# Fase 5

## Event Driven Architecture

Objetivo:

Transformar a plataforma em uma arquitetura baseada em eventos.

Tecnologias previstas:

- Kafka
- RabbitMQ
- Outbox Pattern
- Saga Pattern
- Event Sourcing (estudo)

Status:

🔵 Futuro

---

# Próximos Microsserviços

## Catalog Service

Responsabilidades:

- Categorias
- Marcas
- Atributos
- Imagens

---

## Inventory Service

Responsabilidades:

- Estoque
- Reserva
- Movimentações

---

## Customer Service

Responsabilidades:

- Clientes
- Endereços
- Documentos

---

## Cart Service

Responsabilidades:

- Carrinho
- Itens
- Cupons

---

## Order Service

Responsabilidades:

- Pedidos
- Checkout
- Histórico

---

## Payment Service

Responsabilidades:

- Pagamentos
- Gateway
- Estornos

---

## Shipping Service

Responsabilidades:

- Fretes
- Transportadoras
- Rastreamento

---

## Notification Service

Responsabilidades:

- Email
- SMS
- Push Notification

---

## Identity Service

Responsabilidades:

- Login
- OAuth2
- JWT
- Usuários

---

# Inteligência Artificial

Planejamento:

- AI Product Description
- Recommendation Engine
- Semantic Search
- AI Customer Assistant
- Demand Forecast
- Fraud Detection

---

# Arquitetura Final

```
                API Gateway
                      │
      ┌───────────────┼────────────────┐
      │               │                │
 Catalog        Product         Inventory
      │               │                │
 Customer       Order          Payment
      │               │                │
 Shipping      Notification    Identity
                      │
                    Kafka
                      │
                Analytics / AI
```

---

# Objetivos Técnicos

Durante a evolução do projeto serão utilizados:

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker
- Kubernetes
- AWS
- Kafka
- OpenTelemetry
- GitHub Actions
- Terraform
- Grafana
- Prometheus

---

# Objetivo Final

Construir uma plataforma moderna de e-commerce que sirva como referência para estudos de:

- DDD
- Arquitetura Hexagonal
- Clean Architecture
- Microsserviços
- Cloud Native
- AWS
- Inteligência Artificial
- Engenharia de Software