# Security

# Visão Geral

A segurança do AI-Commerce foi projetada para seguir os padrões modernos utilizados em aplicações distribuídas e arquiteturas de microsserviços.

Embora a autenticação ainda não esteja implementada nesta primeira versão do Product Service, toda a arquitetura foi preparada para suportar uma solução robusta baseada em OAuth2 e JWT.

---

# Objetivos

A camada de segurança deverá garantir:

- Autenticação
- Autorização
- Integridade dos dados
- Comunicação segura
- APIs Stateless
- Controle de acesso baseado em papéis (RBAC)

---

# Arquitetura Planejada

```
Cliente

        │

        ▼

API Gateway

        │

        ▼

Keycloak

        │

        ▼

JWT Token

        │

        ▼

Microservices
```

---

# Tecnologias

A arquitetura utilizará:

- Spring Security 6
- OAuth2
- JWT
- Keycloak
- HTTPS
- BCrypt
- CORS
- CSRF (desabilitado para APIs REST)

---

# Fluxo de Autenticação

1. O usuário realiza login.

↓

2. O Keycloak autentica o usuário.

↓

3. Um Access Token JWT é emitido.

↓

4. O cliente envia o JWT em todas as requisições.

↓

5. O Product Service valida o token.

↓

6. A requisição é autorizada.

---

# JWT

O Access Token conterá informações como:

```
sub

email

name

roles

exp

iat
```

Exemplo:

```json
{
  "sub":"123",
  "email":"john@email.com",
  "roles":[
      "ADMIN"
  ]
}
```

---

# Roles

Inicialmente serão utilizados quatro perfis.

## ADMIN

Permissões

- Criar produtos
- Atualizar produtos
- Excluir produtos
- Consultar produtos

---

## SELLER

Permissões

- Criar produtos
- Atualizar produtos
- Consultar produtos

---

## CUSTOMER

Permissões

- Consultar produtos

---

## SYSTEM

Utilizado para comunicação entre microsserviços.

---

# Authorization

A autorização será baseada em Roles.

Exemplo:

```
ADMIN

↓

DELETE Product
```

```
SELLER

↓

UPDATE Product
```

```
CUSTOMER

↓

GET Product
```

---

# Spring Security

O Product Service utilizará:

```
SecurityFilterChain
```

para configuração da segurança.

A autenticação será Stateless.

Não haverá utilização de sessões HTTP.

---

# Password Encoding

Senhas serão armazenadas utilizando:

```
BCrypt
```

Nunca serão persistidas em texto puro.

---

# HTTPS

Todas as comunicações deverão utilizar HTTPS.

Objetivos:

- Criptografia
- Integridade
- Confidencialidade

---

# CORS

A API permitirá configuração de origens específicas.

Exemplo:

```
Frontend

↓

https://app.aicommerce.com
```

---

# CSRF

Por se tratar de uma API REST Stateless, o CSRF será desabilitado.

---

# Refresh Token

A arquitetura prevê suporte para:

- Access Token
- Refresh Token

permitindo renovação segura da autenticação.

---

# API Gateway

Todos os microsserviços ficarão protegidos atrás de um API Gateway.

Responsabilidades:

- Roteamento
- Rate Limiting
- Logging
- Autenticação
- Autorização
- Load Balancing

---

# Auditoria

Operações críticas poderão ser registradas.

Exemplos:

- Login
- Logout
- Exclusão
- Alteração de produto

---

# Segurança entre Microsserviços

A comunicação poderá utilizar:

- JWT
- OAuth2 Client Credentials
- Mutual TLS (futuro)

---

# Roadmap

Planejado para as próximas versões:

- OAuth2 Authorization Server
- Keycloak
- API Gateway
- Refresh Token
- MFA
- Rate Limiting
- Auditoria
- OpenTelemetry
- Observabilidade
- Secrets Manager