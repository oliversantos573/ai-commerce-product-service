# Testing Strategy

# Visão Geral

O AI-Commerce adota uma estratégia de testes em múltiplas camadas para garantir qualidade, confiabilidade e segurança durante a evolução da aplicação.

Cada camada possui uma responsabilidade específica, permitindo identificar problemas rapidamente e reduzir o risco de regressões.

---

# Objetivos

A estratégia de testes busca garantir:

- Correção das regras de negócio
- Estabilidade da aplicação
- Baixo acoplamento
- Facilidade de manutenção
- Confiança durante refatorações
- Integração contínua (CI)

---

# Pirâmide de Testes

```
                E2E Tests
                   ▲
            Integration Tests
                   ▲
              Unit Tests
```

A maior parte dos testes será composta por testes unitários, seguidos por testes de integração e, por fim, testes end-to-end.

---

# Unit Tests

Os testes unitários validam o comportamento de classes isoladas.

Principais alvos:

- Domain Services
- Use Cases
- Value Objects
- Mappers
- Validators

Dependências externas devem ser simuladas utilizando mocks.

---

# Integration Tests

Os testes de integração verificam a comunicação entre componentes da aplicação.

Exemplos:

- Persistência com PostgreSQL
- Spring Data JPA
- Flyway
- Controllers
- Serialização JSON

Sempre que possível, utilizar banco de dados temporário para garantir isolamento.

---

# End-to-End Tests

Os testes E2E validam o comportamento completo da API.

Fluxos esperados:

- Criar produto
- Buscar produto
- Listar produtos
- Atualizar produto
- Excluir produto

Esses testes simulam o uso real da aplicação.

---

# Ferramentas

As principais bibliotecas planejadas são:

- JUnit 5
- Mockito
- AssertJ
- Spring Boot Test
- Testcontainers
- MockMvc

---

# Cobertura de Testes

O objetivo é atingir alta cobertura nas regras de negócio.

Prioridade:

- Domain Layer
- Application Layer

A cobertura percentual não será utilizada como único indicador de qualidade.

Testes relevantes são mais importantes do que quantidade.

---

# Testcontainers

Os testes de integração utilizarão containers descartáveis.

Exemplo:

```
Application Test

↓

Testcontainers

↓

PostgreSQL

↓

Flyway

↓

Repository
```

Benefícios:

- Ambiente isolado
- Banco real
- Execução reproduzível
- Independência da máquina do desenvolvedor

---

# MockMvc

Os testes dos controllers utilizarão MockMvc.

Exemplo de validações:

- HTTP Status
- Headers
- JSON Response
- Validações de entrada
- Tratamento de erros

---

# Testes de Validação

Também serão implementados testes para verificar:

- Campos obrigatórios
- Tamanho máximo
- Formatos inválidos
- Regras de negócio
- Exceções personalizadas

---

# Cenários Positivos

Exemplos:

- Criar produto válido
- Buscar produto existente
- Atualizar produto existente
- Excluir produto existente
- Listar produtos com paginação

---

# Cenários Negativos

Exemplos:

- Produto inexistente
- SKU duplicado
- Dados inválidos
- UUID inválido
- Requisição malformada

---

# Automação

Todos os testes serão executados automaticamente durante o processo de build.

Fluxo esperado:

```
Build

↓

Unit Tests

↓

Integration Tests

↓

Package

↓

Deploy
```

A aplicação somente poderá ser publicada caso todos os testes sejam aprovados.

---

# Qualidade de Código

Além dos testes, o projeto poderá utilizar ferramentas como:

- SonarQube
- JaCoCo
- SpotBugs
- Checkstyle

Essas ferramentas auxiliam na identificação de problemas de qualidade, cobertura e padronização.

---

# Roadmap

Planejamento das próximas etapas:

- Testes unitários completos do domínio
- Testes dos Use Cases
- Testes dos Controllers
- Testes de integração com PostgreSQL
- Testcontainers
- Pipeline de CI executando toda a suíte de testes
- Relatórios de cobertura automatizados