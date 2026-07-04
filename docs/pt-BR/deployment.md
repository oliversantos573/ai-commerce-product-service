# Deployment Guide

# Visão Geral

O Product Service foi desenvolvido para ser executado em diferentes ambientes, desde desenvolvimento local até ambientes de produção na nuvem.

A arquitetura segue os princípios do Twelve-Factor App, permitindo facilidade de implantação, escalabilidade e manutenção.

---

# Ambientes

O projeto suporta os seguintes ambientes:

- Local
- Docker
- Docker Compose
- AWS
- Kubernetes (planejado)

---

# Requisitos

Para executar localmente é necessário:

- Java 21
- Maven 3.9+
- Docker
- Docker Compose
- PostgreSQL 16

---

# Executando Localmente

Clone o projeto.

```
git clone https://github.com/oliversantos573/ai-commerce-platform.git
```

Entre na pasta.

```
cd product-service
```

Suba o banco.

```
docker compose up -d
```

Compile.

```
mvn clean install
```

Execute.

```
mvn spring-boot:run
```

---

# Executando via IntelliJ IDEA

1. Abrir o projeto Maven.
2. Aguardar o download das dependências.
3. Iniciar o PostgreSQL utilizando Docker Compose.
4. Executar a classe:

```
ProductServiceApplication
```

O Flyway criará automaticamente as tabelas necessárias.

---

# Docker Compose

A infraestrutura local utiliza:

```
Docker

↓

PostgreSQL

↓

Product Service
```

O banco é persistido através de volumes Docker.

---

# Banco de Dados

Banco utilizado:

```
PostgreSQL 16
```

A criação das tabelas é realizada automaticamente pelo Flyway durante a inicialização da aplicação.

---

# Configuração

Principais configurações:

```
application.yml
```

Exemplo:

```
Datasource

Flyway

Swagger

JPA

Logging
```

---

# Variáveis de Ambiente

Em produção recomenda-se utilizar variáveis de ambiente.

Exemplo:

```
DB_HOST

DB_PORT

DB_NAME

DB_USER

DB_PASSWORD
```

Nunca armazenar credenciais diretamente no código-fonte.

---

# Build

Gerar o artefato:

```
mvn clean package
```

O resultado será:

```
target/product-service.jar
```

---

# Executando o JAR

```
java -jar product-service.jar
```

---

# Docker Image

Exemplo de build:

```
docker build -t product-service .
```

Executar:

```
docker run -p 8081:8081 product-service
```

---

# AWS (Planejado)

Arquitetura prevista:

```
Internet

↓

API Gateway

↓

Load Balancer

↓

ECS / Kubernetes

↓

Product Service

↓

PostgreSQL RDS
```

---

# Observabilidade

Planejado:

- Micrometer
- Prometheus
- Grafana
- OpenTelemetry
- Distributed Tracing

---

# Logs

Os logs seguirão padrão estruturado.

Objetivos:

- Auditoria
- Diagnóstico
- Observabilidade

---

# Health Check

O projeto utiliza Spring Boot Actuator.

Endpoints planejados:

```
/actuator/health

/actuator/info

/actuator/metrics
```

---

# Escalabilidade

O serviço foi projetado para execução Stateless.

Isso permite múltiplas instâncias simultâneas atrás de um Load Balancer.

---

# CI/CD

Pipeline planejado:

```
GitHub

↓

GitHub Actions

↓

Build

↓

Tests

↓

Docker Build

↓

Docker Registry

↓

Deploy AWS
```

---

# Segurança

Em produção deverão ser utilizados:

- HTTPS
- Secrets Manager
- Variáveis de ambiente
- JWT
- OAuth2
- API Gateway

---

# Roadmap

Próximas evoluções:

- Dockerfile otimizado
- Multi-stage Build
- GitHub Actions
- AWS ECS
- Kubernetes
- Helm Charts
- Terraform
- ArgoCD
- Blue/Green Deployment