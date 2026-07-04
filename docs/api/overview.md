# API REST

# Visão Geral

O Product Service expõe uma API REST para gerenciamento de produtos da plataforma AI-Commerce.

Todos os endpoints seguem princípios RESTful utilizando JSON como formato de comunicação.

---

# Base URL

```
http://localhost:8081/api/v1/products
```

---

# Endpoints

## Criar Produto

POST

```
/api/v1/products
```

### Request

```json
{
  "sku":"CAR-001",
  "name":"Notebook Gamer",
  "description":"Notebook RTX",
  "brandId":"uuid",
  "categoryId":"uuid"
}
```

### Response

HTTP

```
201 Created
```

```json
{
  "id":"uuid"
}
```

---

## Buscar Produto

GET

```
/api/v1/products/{id}
```

### Response

```json
{
  "id":"uuid",
  "sku":"CAR-001",
  "name":"Notebook",
  "description":"RTX",
  "status":"DRAFT",
  "createdAt":"...",
  "updatedAt":"..."
}
```

---

## Listar Produtos

GET

```
/api/v1/products
```

### Query Params

|Campo|Descrição|
|------|---------|
|page|Página|
|size|Quantidade|
|sortBy|Campo de ordenação|
|direction|ASC ou DESC|

Exemplo

```
?page=0
&size=10
&sortBy=createdAt
&direction=DESC
```

### Response

Retorna um objeto Page do Spring Data contendo:

- content
- totalElements
- totalPages
- size
- number
- first
- last

---

## Atualizar Produto

PUT

```
/api/v1/products/{id}
```

### Request

```json
{
  "name":"Notebook Atualizado",
  "description":"Nova descrição"
}
```

### Response

```
200 OK
```

---

## Excluir Produto

DELETE

```
/api/v1/products/{id}
```

### Response

```
204 No Content
```

---

# Códigos HTTP

|Código|Descrição|
|------|----------|
|200|OK|
|201|Created|
|204|No Content|
|400|Bad Request|
|404|Not Found|
|409|Conflict|
|500|Internal Server Error|

---

# Validações

Os campos obrigatórios são validados utilizando Bean Validation.

Exemplos

- SKU obrigatório
- Nome obrigatório
- Descrição obrigatória
- BrandId obrigatório
- CategoryId obrigatório

---

# Swagger

A documentação OpenAPI está disponível em

```
http://localhost:8081/swagger
```

JSON

```
http://localhost:8081/api-docs
```

---

# Versionamento

A API utiliza versionamento por URI.

```
/api/v1/
```

Novas versões serão disponibilizadas sem quebrar compatibilidade com clientes existentes.

---

# Convenções

Todos os endpoints seguem os princípios REST:

- Recursos nomeados no plural
- Utilização correta dos verbos HTTP
- Respostas padronizadas
- Uso de códigos HTTP apropriados
- JSON UTF-8